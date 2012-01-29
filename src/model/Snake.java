package model;

import math.FRect;
import math.FVect;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import resources.ResourceManager;
import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;

public class Snake extends GameObject
{
	/// NESTING
	private static enum State { IDLE, EATING, STUNNED }
	
	/// CONSTANTS
	private static double ACCELERATION = 0.02;
	private static double FRICTION = 0.26;
	private static double MAX_SPEED = 0.16;
	private static float ABS_ANIM_SPEED = 0.3f;
	private static int STUN_DURATION = 90; // 3 seconds
	private static int EAT_DURATION = 30; // 1 seconds
	
	/// ATTRIBUTES
	private FVect center;
	private float angle = (float) (Math.random()*Math.PI*2);
	private FVect head_position;
	private float speed = 0;
	private float radius; 
	private int state_timer = -1;
	private ColourCode colour;
	private Image tail;
	private Image[] head;
	// animation
	private State state = State.IDLE;
	private float current_frame = 1;
	private float anim_speed = 0;
	
	/// METHODS
	
	// creation
	public Snake(FVect init_center, float init_radius, 
			ColourCode init_colour)
	{
		// Create position at origin 
		super(new FVect(0, 0), new FVect(48,48));
		head_position = (FVect)position.clone();
		
		// Initialise variables
		center = init_center;
		radius = init_radius;
		colour = init_colour;
		switch(ControlManager.getInstance().getDevice())
		{
			case XBOX_TURNTABLE :
			case PS3_TURNTABLE :
			{
				ACCELERATION = 0.02;
				FRICTION = 0.26;
				MAX_SPEED = 0.16;
				STUN_DURATION = 90;
				break;
			}
			case KEYBOARD :
			default :
			{
				ACCELERATION = 0.02;
				FRICTION = 0.26;
				MAX_SPEED = 0.16;
				STUN_DURATION = 90;
				break;
			}
		}
		
		// convert polar to cartesian coordinates
		calculateCoordinates();
		
		// cache images
		ResourceManager rm = ResourceManager.getInstance();
		head = new Image[3];
		switch(colour)
		{
			case RED:
				tail = rm.getImage("snake_red");
				for(int i = 0; i < 3; i++) 
					head[i] = rm.getImage("head_red"+i);
				break;
			case BLUE:
				tail = rm.getImage("snake_blue");
				for(int i = 0; i < 3; i++) 
					head[i] = rm.getImage("head_blue"+i);
				break;
			case GREEN:
				tail = rm.getImage("snake_green");
				for(int i = 0; i < 3; i++) 
					head[i] = rm.getImage("head_green"+i);
				break;
			default:
				break;
		}
	}
	
	// modification
	public void setAngle(float new_angle)
	{
		// Reset angle and convert polar to cartesian coordinates
		angle = new_angle;
		// Set aesthetic angle
		float deg_angle = (float)(angle*180/Math.PI);
		tail.setRotation(deg_angle + 15);
		for(int i = 0; i<3; i++) 
			head[i].setRotation(deg_angle + 135);
		// Move body
		calculateCoordinates();
	}
	
	public void addAngle(float addition)
	{
		// Reset angle and convert polar to cartesian coordinates
		setAngle(angle + addition);
	}
	
	// interface
	public void draw(Graphics g)
	{	
		// Draw snake body
		tail.drawCentered(center.x, center.y);
		// Draw snake tail
		head[(int)current_frame].drawCentered(head_position.x, head_position.y);
	}
	
	public IDynamic.Rtn update()
	{
		super.update();
		
		// Decrement state
		if(state_timer > 0)
			state_timer--;
		else if(state_timer == 0)
			setState(State.IDLE, -1);
			
		
		// Stay still if stunned
		if(state == State.STUNNED)
			speed *= (1 - FRICTION/2);
		else
		{
			// Do animation
			alternateAnimDirection();
			
			// Accelerate based on input
			int input_sign = ControlManager.getInstance().getSnakeDelta(colour);
			speed += ACCELERATION*input_sign;
			
			// Apply friction if not moving in direction of input
			if (Math.signum(speed) != input_sign)
				speed *= (1-FRICTION);
			
			// Cap speed to maximum
			if(Math.abs(speed) > MAX_SPEED)
				speed = (float)(Math.signum(speed)*MAX_SPEED);
		}
		// Advance angle by polar speed
		addAngle(speed);
		
		// Nothing to report
		return IDynamic.Rtn.CONTINUE;
	}


	public void treatEvent(ObjectEvent e)
	{
		// If stunned, marbles aren't damaged
		if(state == State.STUNNED)
		{
			speed = 0;
			return;
		}
		
		// Otherwise destroy marbles that collide
		switch(e.getType())
		{
			case COLLISION:
				CollisionEvent ce = (CollisionEvent)e;
				GameObject other = ce.getOther();
				if(other.getClass() == Marble.class)
				{
					// kill other
					other.die();
					// Wrong colours => stun snake
					if(((Marble)other).getColour() != colour)
					{
						level.addBonus(Level.Bonus.BLOCK);
						// Stun animation
						setState(State.STUNNED, STUN_DURATION);
					}
					// Right colours => bonus
					else
					{
						level.addBonus(Level.Bonus.EAT);
						// Eat animation
						setState(State.EATING, EAT_DURATION);
					}
				}
				break;
		}
	}
	
	
	/// SUBROUTINES
	
	private void calculateCoordinates()
	{
		float offset = (float)(Math.PI/14);
		// Cache the position in cartesian coordinates
		// this prevents trigonometry calculations each time we render!
		position.x = center.x + (float)(Math.cos(angle)*radius);
		position.y = center.y + (float)(Math.sin(angle)*radius);
		head_position.x = center.x + (float)(Math.cos(angle+offset)*(radius-8));
		head_position.y = center.y + (float)(Math.sin(angle+offset)*(radius-8));
		positionUpdated();
	}
	
	private void setState(State new_state, int duration)
	{
		state_timer = duration;
		
		switch(new_state)
		{
			case IDLE:
				state_timer = -1;	// turn off
				break;
				
			case EATING:
				anim_speed = ABS_ANIM_SPEED;
				break;
				
			case STUNNED:
				current_frame = 0;
				anim_speed = 0;
				break;
				
			default:
				break;
		}
		
		state = new_state;
	}
	
	private void alternateAnimDirection()
	{
		// Non-animated need not apply
		if(anim_speed == 0)
			return;
		
		// Stop in IDLE on correct anim
		if(state == State.IDLE && ((int)current_frame) == 1)
		{
			current_frame = 1;
			anim_speed = 0;
			return;
		}
		
		// Advance animation
		current_frame += anim_speed;
		
		// Overflow backwards => move forwards
		if(current_frame < 0)
		{
			current_frame = 0;
			anim_speed = ABS_ANIM_SPEED;
			
		}
		
		// Overflow forwards => move backwards
		else if(current_frame > 3)
		{
			current_frame = 2;
			anim_speed = -ABS_ANIM_SPEED;
		}
		
	}
	
}

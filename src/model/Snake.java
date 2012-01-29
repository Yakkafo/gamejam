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
	/// CONSTANTS
	private static double ACCELERATION = 0.02;
	private static double FRICTION = 0.26;
	private static double MAX_SPEED = 0.16;
	private static int STUN_DURATION = 90; // 3 seconds
	
	/// ATTRIBUTES
	private FVect center;
	private float angle = (float) (Math.random()*Math.PI*2);
	private float speed = 0;
	private float radius; 
	private float current_frame = 0;
	private int stunned = 0;
	private ColourCode colour;
	private Image tail;
	private Image[] head;
	
	/// METHODS
	
	// creation
	public Snake(FVect init_center, float init_radius, 
			ColourCode init_colour)
	{
		// Create position at origin 
		super(new FVect(0, 0), new FVect(48,48));
		
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
			head[i].setRotation(deg_angle + 145);
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
		tail.drawCentered(center.x, center.y);
		head[0].drawCentered(position.x, position.y);
		
		if(stunned > 0)
			g.drawString("***STUNNED***", position.x, position.y);
		
		drawHitbox(g);
	}
	
	public IDynamic.Rtn update()
	{
		super.update();
		
		// Stay still if stunned
		if(stunned > 0)
		{
			speed *= (1-FRICTION);
			stunned--;
		}
		else
		{
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
		if(stunned > 0)
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
						stunned = STUN_DURATION;
					}
					// Right colours => bonus
					else
						level.addBonus(Level.Bonus.EAT);
				}
				break;
		}
	}
	
	
	/// SUBROUTINES
	
	private void calculateCoordinates()
	{
		// Cache the position in cartesian coordinates
		// this prevents trigonometry calculations each time we render!
		position.x = center.x + (int)(Math.cos(angle)*radius);
		position.y = center.y + (int)(Math.sin(angle)*radius);
		positionUpdated();
	}
	
}

package model;

import math.dVect;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;

import resources.ResourceManager;
import system.ControlManager;
import utility.IDynamic;

public class Snake extends GameObject
{
	/// CONSTANTS
	private static final double ACCELERATION = 0.02;
	private static final double FRICTION = 0.26;
	private static final double MAX_SPEED = 0.16;
	private static final int STUN_DURATION = 90; // 3 seconds
	
	/// ATTRIBUTES
	private dVect center;
	private double angle = Math.random()*Math.PI*2;
	private double speed = 0;
	private double radius; 
	private int stunned = 0;
	private GameObject.Colour colour;
	private Image image;
	
	/// METHODS
	
	// creation
	public Snake(dVect init_center, double init_radius, 
			GameObject.Colour init_colour)
	{
		// Create position at origin 
		super(new dVect(0, 0), new dVect(48,48));
		
		// Initialise variables
		center = init_center;
		radius = init_radius;
		colour = init_colour;
		
		// convert polar to cartesian coordinates
		calculateCoordinates();
		
		// cache image
		switch(colour)
		{
			default:
				image = ResourceManager.getInstance().getImage("snake_green");
				break;
		}
	}
	
	// modification
	public void setAngle(double new_angle)
	{
		// Reset angle and convert polar to cartesian coordinates
		angle = new_angle;
		float angle_deg = (float)(angle*180/Math.PI);
		image.setRotation(angle_deg);
		calculateCoordinates();
	}
	
	public void addAngle(double addition)
	{
		// Reset angle and convert polar to cartesian coordinates
		setAngle(angle + addition);
	}
	
	// interface
	public void draw(Graphics g)
	{
		drawHitbox(g);
		
		if(colour == GameObject.Colour.GREEN)
		{
			g.drawString(image.getRotation() + " from "+angle, 200, 32);
			image.drawCentered((float)center.x, (float)center.y);
		}
		
		if(stunned > 0)
			g.drawString("***STUNNED***", (float)position.x, (float)position.y);
		else
			g.drawString(colour.toString(), (float)position.x, (float)position.y);
		// Draw the head (animated)
		//((Animation)getHeadIm()).getCurrentFrame().drawCentered((float)position.x, (float)position.y);
		// Draw the body (static, rotated)
		//((Image)getBodyIm()).setRotation((float)angle);
		//((Image)getBodyIm()).drawCentered((float)center.x, (float)center.y);
		//g.drawOval((float)(center.x-radius), (float)(center.y-radius), (float)radius*2, (float)radius*2);
	}
	
	public IDynamic.Rtn update()
	{
		super.update();
		
		// Stay still if stunned
		if(stunned > 0)
		{
			stunned--;
			return IDynamic.Rtn.CONTINUE;
		}
		
		// Accelerate based on input
		int input_sign = ControlManager.getInstance().getSnakeDelta(colour);
		speed += ACCELERATION*input_sign;
		
		// Apply friction if not moving in direction of input
		if (Math.signum(speed) != input_sign)
			speed *= (1-FRICTION);
		
		// Cap speed to maximum
		if(Math.abs(speed) > MAX_SPEED)
			speed = Math.signum(speed)*MAX_SPEED;
		
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
	
	// overriden
	
	protected Renderable getBodyIm()
	{
		/// FIXME
		return null;
	}
	
	protected Renderable getHeadIm()
	{
		/// FIXME --- should be abstract, different for each snake
		return ResourceManager.getInstance().getAnimation("snake");
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

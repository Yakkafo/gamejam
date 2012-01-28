package model;

import math.dVect;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;

import resources.ResourceManager;
import system.ControlManager;

public abstract class SnakeHead extends GameObject
{
	/// CONSTANTS
	private static final double TURN_RATE = 0.1;
	
	/// ATTRIBUTES
	private dVect center;
	private double angle = 0.0;
	private double radius; 
	private final int snake_number;
	
	/// METHODS
	
	// creation
	public SnakeHead(dVect init_center, double init_radius, 
			int init_snake_number)
	{
		// Create position at origin 
		super(new dVect(0, 0));
		
		// Initialise variables
		center = init_center;
		radius = init_radius;
		snake_number = init_snake_number;
		
		// convert polar to cartesian coordinates
		updatePosition();
	}
	
	// modification
	public void setAngle(double new_angle)
	{
		// Reset angle and convert polar to cartesian coordinates
		angle = new_angle;
		updatePosition();
	}
	
	public void addAngle(double addition)
	{
		// Reset angle and convert polar to cartesian coordinates
		angle += addition;
		updatePosition();
	}
	
	// interface
	public void draw(Graphics g)
	{
		// Draw the head (animated)
		//((Animation)getHeadIm()).getCurrentFrame().drawCentered((float)position.x, (float)position.y);
		g.drawRect((float)position.x-16, (float)position.y-16, 32,32);
		// Draw the body (static, rotated)
		//((Image)getBodyIm()).setRotation((float)angle);
		//((Image)getBodyIm()).drawCentered((float)center.x, (float)center.y);
		g.drawOval((float)(center.x-radius), (float)(center.y-radius), (float)radius*2, (float)radius*2);
	}
	
	public void update()
	{
		// Reposition the snake's head around circle
		addAngle(TURN_RATE*ControlManager.getInstance()
				.getSnakeDelta(snake_number));
	}
	
	// overriden
	
	protected abstract Renderable getBodyIm();
	
	protected Renderable getHeadIm()
	{
		/// FIXME --- should be abstract, different for each snake
		return ResourceManager.getInstance().getAnimation("snake");
	}
	
	/// SUBROUTINES
	
	private void updatePosition()
	{
		// Cache the position in cartesian coordinates
		// this prevents trigonometry calculations each time we render!
		position.x = center.x + (int)(Math.cos(angle)*radius);
		position.y = center.y + (int)(Math.sin(angle)*radius);
	}
	
}

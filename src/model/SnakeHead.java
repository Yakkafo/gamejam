package model;

import math.dVect;

import org.newdawn.slick.Graphics;

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
	public SnakeHead(dVect init_center, double init_radius, int init_snake_number)
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
		ResourceManager.getInstance().getAnimation("snake")
			.draw((float)position.x, (float)position.y);
		
		ResourceManager.getInstance().getImage("snake_body")
	}
	
	public void update()
	{
		// Reposition the snake's head around circle
		addAngle(TURN_RATE*ControlManager.getInstance()
				.getSnakeDelta(snake_number));
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

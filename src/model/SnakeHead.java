package model;

import math.dVect;

import org.newdawn.slick.Graphics;

public abstract class SnakeHead extends GameObject
{
	/// ATTRIBUTES
	private dVect center;
	private double angle = 0.0;
	private double radius; 
	
	/// METHODS
	
	// creation
	public SnakeHead(dVect init_center, double init_radius)
	{
		// Create position at origin 
		super(new dVect(0, 0));
		
		// Initialise variables
		center = init_center;
		radius = init_radius;
		
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
		g.fillRect((int)position.x, (int)position.y, 32, 32);
	}
	
	public void update()
	{
		// TODO Auto-generated method stub
		
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

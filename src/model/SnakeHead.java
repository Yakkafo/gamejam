package model;

import java.awt.Point;
import java.awt.geom.Point2D;

import org.newdawn.slick.Graphics;

import utility.IVisible;

public class SnakeHead implements IVisible
{
	/// ATTRIBUTES
	private Point position;
	private Point center;
	private double angle;
	private double radius; 
	
	/// METHODS
	
	// creation
	public SnakeHead(Point init_center, double init_radius)
	{
		// Initialise variables
		center = init_center;
		radius = init_radius;
		
		// Create position and convert polar to cartesian coordinates
		position = new Point();
		updatePosition();
	}
	
	// modification
	public void setAngle(double new_angle)
	{
		// Reset angle and convert polar to cartesian coordinates
		angle = new_angle;
		updatePosition();
	}
	
	// interface
	public void draw(Graphics g)
	{
		g.drawString("SNAKE HEAD", position.x, position.y);
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

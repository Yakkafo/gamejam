package model;

import org.newdawn.slick.Renderable;

import resources.ResourceManager;

import math.dVect;

public class OuterSnake extends SnakeHead
{
	/// CONSTANTS
	private static final double RADIUS = 256;
	
	/// METHODS
	
	// creation
	public OuterSnake(dVect init_center)
	{
		super(init_center, RADIUS, 2);
	}

	// interface
	@Override
	protected Renderable getBodyIm()
	{
		return ResourceManager.getInstance().getImage("snake_body_out");
	}
}

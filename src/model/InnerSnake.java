package model;

import org.newdawn.slick.Renderable;

import resources.ResourceManager;

import math.dVect;

public class InnerSnake extends SnakeHead
{
	/// CONSTANTS
	private static final double RADIUS = 128;
	
	/// METHODS
	
	// creation
	public InnerSnake(dVect init_center)
	{
		super(init_center, RADIUS, 0);
	}

	// interface
	@Override
	protected Renderable getBodyIm()
	{
		return ResourceManager.getInstance().getImage("snake_body_in");
	}
}

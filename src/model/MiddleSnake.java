package model;

import org.newdawn.slick.Renderable;

import resources.ResourceManager;

import math.dVect;

public class MiddleSnake extends SnakeHead
{
	/// CONSTANTS
	private static final double RADIUS = 192;
	
	/// METHODS
	
	// creation
	public MiddleSnake(dVect init_center)
	{
		super(init_center, RADIUS, 1);
	}

	// interface
	@Override
	protected Renderable getBodyIm()
	{
		return ResourceManager.getInstance().getImage("snake_body_mid");
	}

}

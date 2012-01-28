package model;

import math.dVect;

public class CenterSnake extends SnakeHead
{
	/// CONSTANTS
	private static final double RADIUS = 128;
	
	/// METHODS
	
	// creation
	public CenterSnake(dVect init_center)
	{
		super(init_center, RADIUS);
	}
}

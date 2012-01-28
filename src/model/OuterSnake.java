package model;

import math.dVect;

public class OuterSnake extends SnakeHead
{
	/// CONSTANTS
	private static final double RADIUS = 256;
	
	/// METHODS
	
	// creation
	public OuterSnake(dVect init_center)
	{
		super(init_center, RADIUS);
	}
}

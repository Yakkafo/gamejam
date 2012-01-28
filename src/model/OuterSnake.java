package model;

import java.awt.Point;

public class OuterSnake extends SnakeHead
{
	/// CONSTANTS
	private static final double RADIUS = 256;
	
	/// METHODS
	
	// creation
	public OuterSnake(Point init_center)
	{
		super(init_center, RADIUS);
	}
}

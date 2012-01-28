package model;

import java.awt.Point;

public class MiddleSnake extends SnakeHead
{
	/// CONSTANTS
	private static final double RADIUS = 192;
	
	/// METHODS
	
	// creation
	public MiddleSnake(Point init_center)
	{
		super(init_center, RADIUS);
	}

}

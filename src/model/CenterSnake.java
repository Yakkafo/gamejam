package model;

import java.awt.Point;

public class CenterSnake extends SnakeHead
{
	/// CONSTANTS
	private static final double RADIUS = 128;
	
	/// METHODS
	
	// creation
	public CenterSnake(Point init_center)
	{
		super(init_center, RADIUS);
	}
}
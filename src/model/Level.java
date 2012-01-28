package model;

import java.util.ArrayList;

import math.dVect;

import org.newdawn.slick.Graphics;

import utility.IDynamic;
import utility.IVisible;

public class Level implements IDynamic, IVisible
{
	/// ATTRIBUTES
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private dVect middle;
	
	/// METHODS
	public Level(dVect init_middle)
	{
		// Create snakes centered on middle
		middle = init_middle;
		objects.add(new CenterSnake(middle));
		objects.add(new MiddleSnake(middle));
		objects.add(new OuterSnake(middle));
		objects.add(new Marble(new dVect(0,0), middle));
	}
	
	// implementation

	@Override
	public void update()
	{
		// update all objects
		for(GameObject o : objects)
			o.update();
	}

	@Override
	public void draw(Graphics g)
	{
		// draw all objects
		for(GameObject o : objects)
			o.draw(g);
	}
}

package model;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import utility.IDynamic;
import utility.IVisible;

public class Level implements IDynamic, IVisible
{
	/// ATTRIBUTES
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	/// METHODS
	Level()
	{
		
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

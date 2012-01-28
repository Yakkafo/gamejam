package model;

import java.util.ArrayList;

import math.dVect;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import system.ControlManager;
import utility.IDynamic;
import utility.IVisible;

public class Level implements IDynamic, IVisible
{
	/// CONSTANTS
	private static final int INNER_RADIUS = 128;
	private static final int MIDDLE_RADIUS = 196;
	private static final int OUTER_RADIUS = 256;
	
	/// ATTRIBUTES
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private dVect middle;
	
	/// METHODS
	public Level(dVect init_middle)
	{
		// Create snakes centered on middle
		middle = init_middle;
		objects.add(new Snake(middle, INNER_RADIUS, GameObject.Colour.BLUE));
		objects.add(new Snake(middle, MIDDLE_RADIUS, GameObject.Colour.RED));
		objects.add(new Snake(middle, OUTER_RADIUS, GameObject.Colour.GREEN));
		objects.add(new Marble(new dVect(0,0), middle));
		objects.add(new Midgard(middle));
	}
	
	// implementation

	@Override
	public IDynamic.Rtn update()
	{
		// for each GameObject
        for(int i = 0; i < objects.size(); i++)
        {
            GameObject o = objects.get(i);

            // generate collision events between actors
            for(int j = i+1; j < objects.size(); j++)
                CollisionEvent.generate(o, objects.get(j));

            // update object
            o.update();
        }
		
		return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public void draw(Graphics g)
	{
		// draw all objects
		for(GameObject o : objects)
			o.draw(g);
	}
}

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
	/// ATTRIBUTES
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private dVect middle;
	
	/// METHODS
	public Level(dVect init_middle)
	{
		// Create snakes centered on middle
		middle = init_middle;
		objects.add(new InnerSnake(middle));
		objects.add(new MiddleSnake(middle));
		objects.add(new OuterSnake(middle));
		objects.add(new Marble(new dVect(0,0), middle));
		objects.add(new Midgard(middle));
	}
	
	// implementation

	@Override
	public void update()
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
		
		// CONTROLLER TESTS
	}

	@Override
	public void draw(Graphics g)
	{
		// draw all objects
		for(GameObject o : objects)
			o.draw(g);
	}
}

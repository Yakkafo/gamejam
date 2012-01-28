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
	private static final int START_MARBLE_PERIOD = 90;
	
	/// CLASS VARIABLES
	private static Level instance;
	
	/// ATTRIBUTES
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private dVect size;
	private dVect centre;
	private int marble_timer = START_MARBLE_PERIOD;
	
	/// METHODS
	public Level(dVect init_size)
	{
		// Initialise variables
		size = init_size;
		centre = ((dVect)size.clone()).scale(0.5);
		
		// Create snakes centered on middle, protecting home-base
		objects.add(new Snake(centre, INNER_RADIUS, GameObject.Colour.BLUE));
		objects.add(new Snake(centre, MIDDLE_RADIUS, GameObject.Colour.RED));
		objects.add(new Snake(centre, OUTER_RADIUS, GameObject.Colour.GREEN));
		objects.add(new Midgard(centre));
	}
	
	// implementation

	@Override
	public IDynamic.Rtn update()
	{
		// create new marbles or decrement timer
		if(marble_timer == 0)
		{
			createMarble();
			marble_timer = START_MARBLE_PERIOD; /// FIXME
		}
		else
			marble_timer--;
		
		// for each GameObject
        for(int i = 0; i < objects.size(); i++)
        {
            GameObject o = objects.get(i);

            // generate collision events between actors
            for(int j = i+1; j < objects.size(); j++)
                CollisionEvent.generate(o, objects.get(j));

            // update object
            IDynamic.Rtn update_rtn = o.update();
            // react according to result of update
            switch(update_rtn)
            {
            	case CONTINUE:
            		break;	// do nothing
            		
            	case KILLME:
            		objects.remove(i); i--;
            }
        }
		
		return IDynamic.Rtn.CONTINUE;
	}

	private void createMarble()
	{
		// Flip two coins
		int coin1 = Math.round((float)Math.random());
		int coin2 = Math.round((float)Math.random());
		
		// Create marble in a corner based on results
		dVect random_corner = new dVect(coin1*size.x, coin2*size.y);
		objects.add(new Marble(random_corner, centre));
	}

	@Override
	public void draw(Graphics g)
	{
		// draw all objects
		for(GameObject o : objects)
			o.draw(g);
	}
}

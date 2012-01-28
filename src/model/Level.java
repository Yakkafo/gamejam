package model;

import java.util.ArrayList;

import math.dVect;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import resources.ResourceManager;
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
	private static final int SCORE_BLOCK_MARBLE = 5;
	private static final int SCORE_EAT_MARBLE = 10;
	private static final int SCORE_HEAL_MARBLE = 15;
	
	// NESTED DECLARATIONS
	public static enum Bonus
	{
		BLOCK, EAT, HEAL
	}
	
	/// ATTRIBUTES
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private dVect size;
	private dVect centre;
	private int marble_period;
	private int marble_timer;
	private int score;
	
	/// METHODS
	
	// creation
	public Level(dVect init_size)
	{
		// Initialise variables
		size = init_size;
		centre = ((dVect)size.clone()).scale(0.5);
		
		// Start first instance!
		restart();
	}
	
	public void restart()
	{
		// Clear any previous objects, reset score and difficult
		objects.clear();
		score = 0;
		marble_period = START_MARBLE_PERIOD;
		marble_timer = marble_period;
		
		// Create snakes centered on middle, protecting home-base
		addObject(new Snake(centre, INNER_RADIUS, GameObject.Colour.BLUE));
		addObject(new Snake(centre, MIDDLE_RADIUS, GameObject.Colour.RED));
		addObject(new Snake(centre, OUTER_RADIUS, GameObject.Colour.GREEN));
		addObject(new Midgard(centre));
	}
	
	// access
	
	public int getScore()
	{
		return score;
	}
	

	public void addBonus(Bonus bonus)
	{
		switch(bonus)
		{
			case BLOCK:
				score += SCORE_BLOCK_MARBLE;
				break;
			case EAT:
				score += SCORE_EAT_MARBLE;
				break;
			case HEAL:
				score += SCORE_HEAL_MARBLE;
				break;
			default:
				break;
		}
		
	}

	// implementation

	@Override
	public IDynamic.Rtn update()
	{
		// create new marbles or decrement timer
		if(marble_timer == 0)
		{
			createMarble();
			marble_timer = marble_period; /// FIXME
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

	@Override
	public void draw(Graphics g)
	{
		// draw background
		ResourceManager.getInstance().getImage("wheel_back")
		.drawCentered((float)centre.x, (float)centre.y);
		
		// draw all objects
		for(GameObject o : objects)
			o.draw(g);
		
		// draw score
		g.drawString("Score : " + score, 32, 32);
	}
	
	/// SUBROUTINES
	
	private void addObject(GameObject new_object)
	{
		objects.add(new_object);
		new_object.setLevel(this);
	}

	private void createMarble()
	{
		// Flip two coins
		int coin1 = Math.round((float)Math.random());
		int coin2 = Math.round((float)Math.random());
		
		// Create marble in a corner based on results
		dVect random_corner = new dVect(coin1*size.x, coin2*size.y);
		addObject(new Marble(random_corner, centre));
	}
}

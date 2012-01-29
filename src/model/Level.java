package model;

import java.util.ArrayList;

import math.FRect;
import math.FVect;
import navigation.GameOver;
import navigation.Scene;

import org.newdawn.slick.Graphics;

import resources.ResourceManager;
import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;
import utility.IVisible;

public class Level extends Scene
{
	/// CONSTANTS
	private static final int INNER_RADIUS = 128;
	private static final int MIDDLE_RADIUS = 196;
	private static final int OUTER_RADIUS = 256;
	private static final int BASE_MARBLE_PERIOD = 90;
	private static final int MIN_MARBLE_PERIOD = 45;
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
	private int marble_timer;
	private int score;
	private FRect background_dest;
	private Scene nextScene;
	
	/// METHODS
	
	// creation
	public Level()
	{
		// Initialise variables
		background_dest = new FRect(centre.x-size.y/2.8f, centre.y-size.y/2.8f,
									size.y/1.4f, size.y/1.4f);
		
		// Start first instance!
		restart();
		
		
	}
	
	public void restart()
	{
		// Clear any previous objects, reset score and difficult
		objects.clear();
		score = 0;
		marble_timer = BASE_MARBLE_PERIOD+MIN_MARBLE_PERIOD;
		
		// Create snakes centered on middle, protecting home-base
		addObject(new Midgard(centre));
		addObject(new Snake(centre, OUTER_RADIUS, ColourCode.GREEN));
		addObject(new Snake(centre, MIDDLE_RADIUS, ColourCode.RED));
		addObject(new Snake(centre, INNER_RADIUS, ColourCode.BLUE));
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
		// check for exit events
		if(ControlManager.getInstance().isExitKey())
		{
			nextScene = new GameOver(score);
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		// create new marbles or decrement timer
		if(marble_timer == 0)
		{
			createMarble();
			// Every 10 points, remove 1 frame (nb - 30 fps) from the delay
			// between marbles
			marble_timer = MIN_MARBLE_PERIOD 
							+ Math.max(0, BASE_MARBLE_PERIOD - score/10);
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
            		
            	case DELETE_OBJECT:
            		objects.remove(i); i--;
            		break;
            		
            	case CHANGE_SCENE:
            		nextScene = new GameOver(score);
            		return IDynamic.Rtn.CHANGE_SCENE;
            }
        }
		
		return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public void draw(Graphics g)
	{
		ResourceManager rm = ResourceManager.getInstance();
		
		// draw background
		rm.getImage("wheel_back").draw(background_dest.x, background_dest.y, 
				background_dest.width, background_dest.height);
		
		// draw all objects
		for(GameObject o : objects)
			o.draw(g);
		
		// draw score
		g.setFont(rm.getFont());
		g.drawString("Score : " + score, 32, 32);
	}
	
	/// SUBROUTINES
	
	private void addObject(GameObject new_object)
	{
		// add and connect object to its container
		objects.add(new_object);
		new_object.setLevel(this);
	}

	private void createMarble()
	{
		// Flip two coins
		int coin1 = Math.round((float)Math.random());
		int coin2 = Math.round((float)Math.random());
		
		// Create marble in a corner based on results
		FVect random_corner = new FVect(coin1*size.x, coin2*size.y);
		addObject(new Marble(random_corner, centre));
	}

	@Override
	public Scene getNextScene()
	{
		return nextScene;
	}
}

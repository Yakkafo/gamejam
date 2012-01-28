package system;
 
import java.util.ArrayList;

import math.dVect;
import math.iVect;
import model.InnerSnake;
import model.Level;
import model.Marble;
import model.MiddleSnake;
import model.OuterSnake;
import model.SnakeHead;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class Game extends BasicGame 
{
	/// CONSTANTS
	private static final int MAX_FPS = 30;
	
	/// ATTRIBUTES
	private iVect size;
	private int frames_since_update = 0;
	private Level level;
	
	/// METHODS
	
	// creation
	public Game()
	{
		// Window title
		super("Snack Spin GGJ game");
	}

	// framework
	@Override
	public void init(GameContainer container) throws SlickException
	{
		// Start control manager
		ControlManager.createInstance(container.getInput());
		
		// Get the true size of the window (may not be what was requested)
		size = new iVect(container.getWidth(), container.getHeight());
		
		// Snakes are centred on middle of screen
		dVect middle = new dVect(size.x/2, size.y/2);
		level = new Level(middle);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException
	{
		
		// Regulate framerate
		frames_since_update += delta;
		if(frames_since_update < 1000/MAX_FPS)
			return;
		frames_since_update = 0;
		
		// Update all the level objects
		level.update();
		
		//Tests ;
        
       
		
	}
	

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException
	{
		
		// Draw all the level objects
		level.draw(g);
	}
}

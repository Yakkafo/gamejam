package system;
 
import java.util.ArrayList;

import math.dVect;
import math.iVect;
import model.Level;
import model.Marble;
import model.Snake;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;
 
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
		dVect middle = new dVect(size.x, size.y);
		level = new Level(middle);
		
		// TODO : Make these tests as methods
		///Tests ;
		//Check controllers : index
		ArrayList<Controller> controllers = new ArrayList<Controller>();

		for (int i = 0; i < Controllers.getControllerCount(); i++) 
		{
			Controller controller = Controllers.getController(i);

			if ((controller.getButtonCount() >= 3) && (controller.getButtonCount() < 20))
			{
				controllers.add(controller);
			}
		}
		Log.info("Found "+controllers.size()+" controllers");
		for (int i=0;i<controllers.size();i++)
			Log.info(((Controller) controllers.get(i)).getName()+" : "+((Controller) controllers.get(i)).getIndex());
		
		//Check if turntable is connected :
		for (int i=0;i<controllers.size();i++)
			if(((Controller) controllers.get(i)).getName().compareTo("Guitar Hero5 for PlayStation (R) 3") == 0) 
				System.out.println("Turntable connected.");
		
		//Get the number of axis
		//System.out.println("Number of axis : "+container.getInput().getAxisCount(2)); //4
		
		
		
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
		
		// Forget events that were received this step
		ControlManager.getInstance().forgetEvents();
		
		// TODO : Make these tests as methods
		/// TESTS
		//Check if a button has been pressed
		for(int i = 0; i < 20; i ++)
				if (container.getInput().isControlPressed(i, /*get turntable id*/ 2))
					System.out.println("Key pressed ! #"+i);
		
		/*
		 * BLUE : 4
		 * RED : 6
		 * GREEN : 5
		 * POWER : 7
		 * 
		 */
		
//		//Check if the turntable get down
//		if(container.getInput().isControllerDown(/*id*/ 2))
//			System.out.println("DOWN");
//		
//		//Check if the turntable get up
//		if(container.getInput().isControllerUp(/*id*/ 2))
//			System.out.println("UP");
//				
//		//Check if the turntable get down
//		if(container.getInput().isControllerDown(/*id*/ 2))
//			System.out.println("RIGHT");
//						
//		//Check if the turntable get up
//		if(container.getInput().isControllerUp(/*id*/ 2))
//			System.out.println("LEFT");

		//System.out.println("Axis 1 : "+container.getInput().getAxisValue(2, 0));

		
	}
	

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException
	{
		
		// Draw all the level objects
		level.draw(g);
	}
	
	
	public void mouseWheelMoved(int delta) 
	{
		ControlManager.getInstance().wheelEvent(delta);
	}
}

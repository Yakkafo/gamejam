package system;
 
import java.util.ArrayList;

import math.FVect;
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
		// Display setup
		container.setSmoothDeltas(true);
        container.setVSync(true);
        container.setTargetFrameRate(MAX_FPS);
        //container.setIcon(ref);
        container.setShowFPS(false);
        
		// Start control manager
		ControlManager.createInstance(container.getInput());
		
		// Get the true size of the window (may not be what was requested)
		size = new iVect(container.getWidth(), container.getHeight());
		
		// Snakes are centred on middle of screen
		FVect middle = new FVect(size.x, size.y);
		level = new Level(middle);		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException
	{
		// Update all the level objects
		level.update();
		
		// Forget events that were received this step
		ControlManager.getInstance().forgetEvents();
		if(ControlManager.getInstance().getDevice() == ControlManager.Device.PS3Turntable)
			for(int i = 0; i < 20; i ++)
				if (container.getInput().isControlPressed(i, ControlManager.getInstance().getDeviceIndex()))
					System.out.println("Key pressed ! #"+i);

	}
	
	/**
	 * Check if the wheel has moved.
	 * @param container
	 * @param delta
	 */
	public void scratchCheck(GameContainer container, int delta){
		if(container.getInput().isControllerDown(ControlManager.getInstance().getDeviceIndex()))
		{
			
		}
		if(container.getInput().isControllerUp(ControlManager.getInstance().getDeviceIndex()))
		{
			
		}

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
	
	public void controllerUpPressed(int delta)
	{
		ControlManager.getInstance().wheelEvent(delta);
	}
	
	public void controllerDownPressed(int delta)
	{
		ControlManager.getInstance().wheelEvent(delta);
	}
}

package system;

import math.FVect;
import math.IVect;
import navigation.Scene;
import navigation.Title;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import utility.IDynamic;
 
public class Game extends BasicGame 
{
	/// CONSTANTS
	private static final int MAX_FPS = 30;
	
	/// ATTRIBUTES
	private IVect size;
	private Scene current_scene;
	
	/// METHODS
	
	// creation
	public Game()
	{
		// Window title
		super("Quetzalcoatl");
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
		size = new IVect(container.getWidth(), container.getHeight());
		Scene.init(size.FVect());
		current_scene = new Title();	
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException
	{
		// Update all the scene currently being displayed
		IDynamic.Rtn update_rtn = current_scene.update();
		switch(update_rtn)
		{
			case CHANGE_SCENE:
				current_scene = current_scene.getNextScene();
				break;
			default:
				break;
		}
		
		// Forget events that were received this step
		ControlManager.getInstance().forgetEvents();
		if(ControlManager.getInstance().getDevice() == ControlManager.Device.PS3_TURNTABLE)
			for(int i = 0; i < 20; i ++)
				if (container.getInput().isControlPressed(i, ControlManager.getInstance().getDeviceIndex()))
					System.out.println("Key pressed ! #"+i);

	}
	
	/**
	 * Check if the wheel has moved.
	 * @param container
	 * @param delta
	 */
	public void scratchCheck(GameContainer container, int delta)
	{
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
		
		// Draw the contents of the scene
		current_scene.draw(g);
	}
	
	public void keyPressed(int key, char c)
	{
		System.out.println("PRESSED " + key + " char " + c);
		ControlManager.getInstance().anyKeyEvent();
	}
	
	public void mouseWheelMoved(int delta) 
	{
		ControlManager.getInstance().wheelEvent(delta);
	}
}

package system;

import model.GameObject;

import org.lwjgl.input.Controllers;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class ControlManager
{
	// / CLASS NAMESPACE VARIABLES
	private static ControlManager instance = null;

	// / CLASS NAMESPACE FUNCTIONS
	public static void createInstance(Input init_input)
	{
		instance = new ControlManager(init_input);
	}
	
	public static ControlManager getInstance()
	{
		if(instance == null)
			System.out.println("ControlManager has not neen initialised!");
		return instance;
	}

	// / ATTRIBUTES
	private Input input;

	/// METHODS
	
	// creation
	public ControlManager(Input init_input)
	{
		// Initialise variables
		input = init_input;
		
		//Controllers test
		try
		{
			input.initControllers();
			
			/// FIXME
			System.out.println("Controller count : "+input.getControllerCount());
			

		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// query
	public int getSnakeDelta(GameObject.Colour snake_colour)
	{
		
		switch(snake_colour)
		{
			case BLUE:
				return (input.isKeyDown(Input.KEY_W)) ? arrowToDelta() : 0;
			case RED:
				return (input.isKeyDown(Input.KEY_X)) ? arrowToDelta() : 0;
			case GREEN:
				return (input.isKeyDown(Input.KEY_C)) ? arrowToDelta() : 0;
			default:
				return 0;

		}
	}
	
	private int arrowToDelta()
	{
		if(input.isKeyDown(Input.KEY_UP))
			return -1;
		else if(input.isKeyDown(Input.KEY_DOWN))
			return 1;
		else
			return 0;
	}
	
	

}

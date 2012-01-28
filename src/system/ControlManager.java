package system;

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
	public int getSnakeDelta(int snake_number)
	{
		/// FIXME -- should be based on input
		return (3-snake_number);
	}
	
	
	

}

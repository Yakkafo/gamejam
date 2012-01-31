package system;

import java.util.ArrayList;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ControlManager implements ControllerListener
{
	// / CLASS NAMESPACE VARIABLES
	private static ControlManager instance = null;

	// / CONSTANTS
	@SuppressWarnings("unused")
	private final static int AXISINDEX = 4;
	final static int GREENBUTTON = 5;
	final static int REDBUTTON = 6;
	final static int BLUEBUTTON = 4;
	final static int POWERBUTTON = 7;

	// / CLASS NAMESPACE FUNCTIONS
	public static void createInstance(Input init_input)
	{
		instance = new ControlManager(init_input);
	}

	public static ControlManager getInstance()
	{
		if (instance == null) System.out
				.println("ControlManager has not neen initialised!");
		return instance;
	}

	// / ATTRIBUTES
	public enum Device
	{
		PS3_TURNTABLE, XBOX_TURNTABLE, KEYBOARD;
	}

	private Device device = Device.KEYBOARD;
	private Input input;
	private Input inputController; // For controllers only

	// ------- input state --------
	@SuppressWarnings("unused")
	private int wheel_direction = 0;
	private boolean any_key = false;
	private boolean blueDown = false;
	private boolean redDown = false;
	private boolean greenDown = false;
	// ------- -------

	private int deviceIndex = -1;
	@SuppressWarnings("unused")
	private String deviceName;
	private ArrayList<Controller> controllers;

	// / METHODS

	// creation
	public ControlManager(Input init_input)
	{
		// Initialize variables
		input = init_input;
		input.addControllerListener(this);
		inputController = init_input;
		input.disableKeyRepeat();
		controllers = new ArrayList<Controller>();
		checkDevice();
		// inputController.addControllerListener(this);

		// Controllers test
		try
		{
			input.initControllers();
			// TODO : do it better
			// Check if turntable is connected :
			for (int i = 0; i < controllers.size(); i++)
				if (device == Device.PS3_TURNTABLE
						|| device == Device.XBOX_TURNTABLE) System.out
						.println("Turntable connected.");

			// Get the number of axis
			// System.out.println("Number of axis : "+container.getInput().getAxisCount(2));
			// //4
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// query

	public boolean isAnyKey()
	{
		return any_key;
	}

	public boolean isExitKey()
	{
		switch (device)
		{
			case KEYBOARD:
				return input.isKeyPressed(Input.KEY_ESCAPE);
			default:
				return false;

				// / TODO
		}
	}

	public void clearInputQueue()
	{
		switch (device)
		{
			case KEYBOARD:
				input.clearKeyPressedRecord();
				break;
			case PS3_TURNTABLE:
			case XBOX_TURNTABLE:
				input.clearControlPressedRecord();
				break;
			default:
				break;
		}
	}

	public boolean isColourKey(ColourCode key_colour, boolean new_only)
	{
		if (device == Device.KEYBOARD)
		{
			switch (key_colour)
			{
				// / NB -- QWERTY vs AZERTY
				case GREEN:
					return (new_only) ? input.isKeyPressed(Input.KEY_D) : input
							.isKeyDown(Input.KEY_D);
				case RED:
					return (new_only) ? input.isKeyPressed(Input.KEY_F) : input
							.isKeyDown(Input.KEY_F);
				case BLUE:
					return (new_only) ? input.isKeyPressed(Input.KEY_G) : input
							.isKeyDown(Input.KEY_G);
			} // switch(key_colour)
		}
		if (device == Device.PS3_TURNTABLE || device == Device.XBOX_TURNTABLE)
		{
			switch (key_colour)
			{
				case BLUE:
					return blueDown;
				case RED:
					return redDown;
				case GREEN:
					return greenDown;
			} // switch(key_colour)
		}
		return false;
	} // switch(device)

	public int getSnakeDelta(ColourCode snake_colour)
	{

		// if snake colour key is not pressed the snake is not controlled!
		if (!isColourKey(snake_colour, false)) // input, new and only ;)
		return 0;
		// return wheel_direction;
		switch (device)
		{
			case XBOX_TURNTABLE:
			case PS3_TURNTABLE:
				if (input.getAxisValue(2, 0) < 0) return -1;
				else if (input.getAxisValue(2, 0) > 0) return 1;
				else return 0;
			case KEYBOARD:
			default:
				return -1;
				/*if (input.isKeyDown(Input.KEY_UP)
						|| input.isKeyDown(Input.KEY_LEFT)) return -1;
				else if (input.isKeyDown(Input.KEY_DOWN)
						|| input.isKeyDown(Input.KEY_RIGHT)) return 1;
				else return 0;*/
		}
	}

	/******** EVENTS *******/

	public void wheelEvent(int delta)
	{
		// Event generated by Game instance
		wheel_direction = (int) Math.signum(delta);
	}

	public void anyKeyEvent()
	{
		any_key = true;
	}

	public void forgetEvents()
	{
		// Forget the mouse wheel direction the update after it occurs
		wheel_direction = 0;
		any_key = false;
	}

	/**************** < ********/

	// /////////////////////////
	/*** DEVICE MANAGEMENT ***/
	// /////////////////////////

	// Check if the player play with a turntable or a keyboard
	private void checkDevice()
	{
		String tamp;
		controllers = new ArrayList<Controller>();
		for (int i = 0; i < Controllers.getControllerCount(); i++)
		{
			Controller controller = Controllers.getController(i);
			if ((controller.getButtonCount() >= 3)
					&& (controller.getButtonCount() < 20))
			{
				controllers.add(controller);
			}
		}
		for (int i = 0; i < controllers.size(); i++)
		{
			tamp = ((Controller) controllers.get(i)).getName();
			if (tamp.compareTo("Guitar Hero5 for PlayStation (R) 3") == 0)
			{
				deviceName = "Guitar Hero5 for PlayStation (R) 3";
				deviceIndex = ((Controller) controllers.get(i)).getIndex();
				device = Device.PS3_TURNTABLE;
			}
			// TODO : find the good string for the xbox device
			// if(tamp.compareTo("Guitar Hero5 for Xbox360 ??? (R) 3") == 0)
			// {
			// deviceName = "Guitar Hero5 for Xbox360 ??? (R) 3";
			// deviceIndex = ((Controller) controllers.get(i)).getIndex();
			// device = Device.XBOXTurntable;
			// }

		}
	}

	public int getDeviceIndex()
	{
		return deviceIndex;
	}

	public Device getDevice()
	{
		return device;
	}

	public Input getInputController()
	{
		return inputController;
	}

	@Override
	public void controllerButtonPressed(int controller, int button)
	{
		// TODO Auto-generated method stub

		switch (button)
		{
			case 2:
				System.out.println("Green is pressed !" + button);
				greenDown = true;
				break;
			case 1:
				blueDown = true;
				break;
			case 3:
				redDown = true;
				break;
			default:
				System.out.println("Huh ?");
				break;
		}
	}

	@Override
	public void controllerButtonReleased(int controller, int button)
	{
		// TODO Auto-generated method stub
		switch (button)
		{
			case 2:
				System.out.println("Green is released !");
				greenDown = false;
				break;
			case 1:
				blueDown = false;
				break;
			case 3:
				redDown = false;
				break;
		}

	}

	@Override
	public void controllerDownPressed(int controller)
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownReleased(int controller)
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftPressed(int controller)
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftReleased(int controller)
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightPressed(int controller)
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightReleased(int controller)
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpPressed(int controller)
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpReleased(int controller)
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void inputEnded()
	{
	// TODO Auto-generated method stub

	}

	@Override
	public void inputStarted()
	{
	// TODO Auto-generated method stub

	}

	@Override
	public boolean isAcceptingInput()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input input)
	{
	// TODO Auto-generated method stub

	}

	public void setBlueDown(boolean blueDown)
	{
		this.blueDown = blueDown;
	}

	public void setRedDown(boolean redDown)
	{
		this.redDown = redDown;
	}

	public void setGreenDown(boolean greenDown)
	{
		this.greenDown = greenDown;
	}

}

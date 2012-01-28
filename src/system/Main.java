package system;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

abstract class Main
{
	/// CONSTANTS
	private static final int DESIRED_W = 1024;
	private static final int DESIRED_H = 768;
	private static final boolean USE_FULLSCREEN = false;
	
	/// FUNCTIONS
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new Game());
			app.setDisplayMode(DESIRED_W, DESIRED_H, USE_FULLSCREEN);
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}

package system;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

abstract class Main
{
	/// CONSTANTS
	private static final int DESIRED_W = 1024; //1280;
	private static final int DESIRED_H = 768; //800;
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

package system;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

abstract class Main
{
	/// CONSTANTS
	private static final int DESIRED_W = 1280;
	private static final int DESIRED_H = 800;
	private static boolean USE_FULLSCREEN = true;
	
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
			USE_FULLSCREEN = false;
			try {
				AppGameContainer app = new AppGameContainer(new Game());
				app.setDisplayMode(DESIRED_W, DESIRED_H, USE_FULLSCREEN);
				app.start();
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}

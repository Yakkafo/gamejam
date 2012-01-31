package system;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public abstract class Main
{
	/// CONSTANTS
	/*private static final int DEFAULT_W = 1280;
	private static final int DEFAULT_H = 800;
	private static boolean USE_FULLSCREEN = true;*/
	
	/// FUNCTIONS
	public static void main(String[] args)
	{
		AppGameContainer app;
		try
		{
			// Try full-screen
			app = new AppGameContainer(new Game());
			app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
			// Start the game
			app.start();
		}
		catch (SlickException e)
		{
			try 
			{
				// If full-screen fails, try again in windowed mode
				System.gc();
				app = new AppGameContainer(new Game());
				app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(),
									false);
				// Start the game
				app.start();
			} 
			catch (SlickException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}

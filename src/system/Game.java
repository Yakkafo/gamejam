package system;
 
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
 
public class Game extends BasicGame 
{
	/// ATTRIBUTES
	Image snake;
	
	/// METHODS
	
	public Game()
	{
		super("Snack Spin GGJ game");
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{
		snake = new Image("snake1.png");
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException
	{
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException
	{
		g.drawString("Hello, Slick world!", 0, 100);
		g.drawImage(snake, 10, 10);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new Game());
			app.setDisplayMode(1024, 720, false);
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}

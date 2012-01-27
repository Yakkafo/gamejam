package system;
 
import model.SnakeHead;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
 
public class Game extends BasicGame 
{
	/// ATTRIBUTES
	//SnakeHead inner_snake = new SnakeHead();
	
	/// METHODS
	
	public Game()
	{
		super("Snack Spin GGJ game");
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{
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
		inner_snake.draw(g);
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

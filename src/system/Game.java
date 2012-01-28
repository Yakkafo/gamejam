package system;
 
import java.awt.Dimension;
import java.awt.Point;

import model.CenterSnake;
import model.MiddleSnake;
import model.OuterSnake;
import model.SnakeHead;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
 
public class Game extends BasicGame 
{
	/// CONSTANTS
	private static final int DESIRED_W = 1024;
	private static final int DESIRED_H = 768;
	private static final boolean USE_FULLSCREEN = false;
	private static final int MAX_FPS = 30;
	
	/// ATTRIBUTES
	private Dimension size;
	private SnakeHead snakes[];
	private int frames_since_update = 0;
	
	/// METHODS
	
	// creation
	public Game()
	{
		super("Snack Spin GGJ game");
	}

	// framework
	@Override
	public void init(GameContainer container) throws SlickException
	{
		size = new Dimension(container.getWidth(), container.getHeight());
		
		// Snakes are centred on middle of screen
		Point middle = new Point(size.width/2, size.height/2);
		
		// Create the snakes themselves
		snakes = new SnakeHead[3];
		snakes[0] = new CenterSnake(middle);
		snakes[1] = new MiddleSnake(middle);
		snakes[2] = new OuterSnake(middle);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException
	{
		// Regulate framerate
		frames_since_update += delta;
		if(frames_since_update < 1000/MAX_FPS)
			return;
		frames_since_update = 0;
		
		for(int i = 0; i < 3; i++)
			snakes[i].addAngle(0.3);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException
	{
		for(int i = 0; i < 3; i++)
			snakes[i].draw(g);
	}

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

package system;
 
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import model.CenterSnake;
import model.Marble;
import model.MiddleSnake;
import model.OuterSnake;
import model.SnakeHead;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class Game extends BasicGame 
{
	/// CONSTANTS
	private static final int MAX_FPS = 30;
	
	/// ATTRIBUTES
	private Dimension size;
	private SnakeHead snakes[];
	private int frames_since_update = 0;
	private Input input;
	private ArrayList<Marble> marbleList;
	
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
		input = container.getInput();
		
		
		// Snakes are centred on middle of screen
		Point middle = new Point(size.width/2, size.height/2);
		
		// Create the snakes themselves
		snakes = new SnakeHead[3];
		snakes[0] = new CenterSnake(middle);
		snakes[1] = new MiddleSnake(middle);
		snakes[2] = new OuterSnake(middle);
		
		//Create marbles
		marbleList = new ArrayList<Marble>();
		//marbleList.add(new Marble(new Point(50, 50), middle));
		
		//Controllers test
		container.getInput().initControllers();

		System.out.println(container.getInput().getControllerCount());
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
		
		// update the snakes
		//for(int i = 0; i < 3; i++)
			//snakes[i].addAngle(0.1);
		
		//Mouse game controlling :
		if(container.getInput().isMouseButtonDown(0))
			snakes[0].setAngle(container.getInput().getMouseY() / 10);
		if(container.getInput().isMouseButtonDown(2))
			snakes[1].setAngle(container.getInput().getMouseY() / 10);
		if(container.getInput().isMouseButtonDown(1))
			snakes[2].setAngle(container.getInput().getMouseY() / 10);
		
		//Gamepad game controlling :
		if(container.getInput().isControllerDown(2))
			snakes[0].addAngle(-0.1);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException
	{
		//UPDATE TO LEVEL.DRAW !!!
		// draw marbles
		if(!marbleList.isEmpty()){
			for(Marble mar : marbleList)
				mar.draw(g);
		}
		// draw the snakes
		for(int i = 0; i < 3; i++)
			snakes[i].draw(g);
	}
}

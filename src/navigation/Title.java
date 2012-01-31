package navigation;

import model.Level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import resources.ResourceManager;
import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;

public class Title extends Scene
{
	/// ATTRIBUTES
	private Scene nextScene = null;
	private Image background;
	
	/// METHODS
	
	// creation
	public Title()
	{
		background = ResourceManager.getInstance().getImage("menu_title");
	}
	// implementation
	
	public void draw(Graphics g)
	{
		background.drawCentered(centre.x, centre.y);
	}

	public IDynamic.Rtn update()
	{
		// The Scene can't decide when the Game changes it, but it can
		// "suggest" a change.
		
		ControlManager cm = ControlManager.getInstance();
		
		// view Tutorial
		if(cm.isColourKey(ColourCode.RED, true))
		{
			buttonSound();
			nextScene = new HowToPlay();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		// launch the game!
		if(cm.isColourKey(ColourCode.GREEN, true))
		{
			buttonSound();
			nextScene = new Level();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		// view Credits
		if(cm.isColourKey(ColourCode.BLUE, true))
		{
			buttonSound();
			nextScene = new Credits();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		// exit game
		if(cm.isExitKey())
			return IDynamic.Rtn.EXIT_GAME;
		

		// Nothing to report
		return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public Scene getNextScene()
	{
		return nextScene;
	}
}

package navigation;

import model.GameObject;
import model.Level;

import org.newdawn.slick.Graphics;

import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;

public class Title extends Scene
{
	/// ATTRIBUTES
	private Scene nextScene = null;
	
	/// METHODS
	
	// implementation
	
	public void draw(Graphics g)
	{
		g.drawString("TITLE -- PRESS ANY KEY", 32, 32);
	}

	public IDynamic.Rtn update()
	{
		// The Scene can't decide when the Game changes it, but it can
		// "suggest" a change.
		
		ControlManager cm = ControlManager.getInstance();
		
		// view Tutorial
		if(cm.isColourKey(ColourCode.RED, true))
		{
			nextScene = new HowToPlay();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		// launch the game!
		if(cm.isColourKey(ColourCode.GREEN, true))
		{
			nextScene = new Level();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		// view Credits
		if(cm.isColourKey(ColourCode.BLUE, true))
		{
			nextScene = new Credits();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		
		
		
		// Nothing to report
		return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public Scene getNextScene()
	{
		return nextScene;
	}
}

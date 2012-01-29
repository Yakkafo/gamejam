package navigation;

import model.Level;

import org.newdawn.slick.Graphics;

import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;

public class GameOver extends Scene
{
	/// ATTRIBUTES
	private Scene nextScene = null;
	private int final_score;
	
	/// METHODS
	
	public GameOver(int init_final_score)
	{
		final_score = init_final_score;
	}
	
	// implementation
	
	public void draw(Graphics g)
	{
		g.drawString("GAME OVER", 32, 32);
	}

	public IDynamic.Rtn update()
	{
		// The Scene can't decide when the Game changes it, but it can
		// "suggest" a change.
		
		ControlManager cm = ControlManager.getInstance();
		
		// back to menu
		if(cm.isColourKey(ColourCode.RED, true) || cm.isExitKey())
		{
			nextScene = new Title();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		// try again!
		if(cm.isColourKey(ColourCode.GREEN, true))
		{
			nextScene = new Level();
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

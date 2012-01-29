package navigation;

import model.Level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import resources.ResourceManager;
import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;

public class GameOver extends Scene
{
	/// ATTRIBUTES
	private Scene nextScene = null;
	private int final_score;
	private Image background;
	
	/// METHODS
	
	public GameOver(int init_final_score)
	{
		final_score = init_final_score;
		
		ResourceManager rm = ResourceManager.getInstance();
		// image
		background = rm.getImage("menu_lose");
		// music
		rm.getMusic("music_level").stop();
		rm.getMusic("music_menu").loop();
	}
	
	// implementation
	
	public void draw(Graphics g)
	{
		background.drawCentered(centre.x, centre.y);
		g.setFont(ResourceManager.getInstance().getFontBig());
		g.drawString("Final Score : " + final_score, 412, 332);
	}

	public IDynamic.Rtn update()
	{
		// The Scene can't decide when the Game changes it, but it can
		// "suggest" a change.
		
		ControlManager cm = ControlManager.getInstance();
		
		// back to menu
		if(cm.isColourKey(ColourCode.RED, true) || cm.isExitKey())
		{
			buttonSound();
			nextScene = new Title();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		
		// try again!
		if(cm.isColourKey(ColourCode.GREEN, true))
		{
			buttonSound();
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

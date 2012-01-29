package navigation;

import model.Level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import resources.ResourceManager;
import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;

public class HowToPlay extends Scene
{
	/// ATTRIBUTES
	private Image background;
	
	/// METHODS
	
	// creation
	public HowToPlay()
	{
		try
		{
			background = new Image(ResourceManager.RESDIR+"how_to_play.jpg");
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// implementation
	
	public void draw(Graphics g)
	{
		background.drawCentered(centre.x, centre.y);
	}

	public IDynamic.Rtn update()
	{
		ControlManager cm = ControlManager.getInstance();
		
		// Return to title screen on red or exit
		if(cm.isColourKey(ColourCode.RED, true) || cm.isExitKey())
			return IDynamic.Rtn.CHANGE_SCENE;
		else
			return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public Scene getNextScene()
	{
		return new Title();
	}
}

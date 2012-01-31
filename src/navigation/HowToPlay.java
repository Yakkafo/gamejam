package navigation;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

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
		background = ResourceManager.getInstance().getImage("menu_howto");
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
		{
			buttonSound();
			return IDynamic.Rtn.CHANGE_SCENE;
		}
		else
			return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public Scene getNextScene()
	{
		return new Title();
	}
}

package navigation;

import model.Level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import resources.ResourceManager;
import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;

public class Credits extends Scene
{
	/// ATTRIBUTES
	private Image background;
	
	/// METHODS
	
	// creation
	public Credits()
	{
		try
		{
			background = new Image(ResourceManager.RESDIR+"credits.jpg");
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
		// Return to title screen
		if(ControlManager.getInstance().isColourKey(ColourCode.RED, true))
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

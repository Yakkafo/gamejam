package navigation;

import model.Level;

import org.newdawn.slick.Graphics;

import system.ColourCode;
import system.ControlManager;
import utility.IDynamic;

public class Credits extends Scene
{
	/// ATTRIBUTES
	
	/// METHODS
	
	// implementation
	
	public void draw(Graphics g)
	{
		g.drawString("CREDITS -- PRESS ANY KEY", 32, 32);
	}

	public IDynamic.Rtn update()
	{
		// Return to title screen
		if(ControlManager.getInstance().isColourKey(ColourCode.RED))
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

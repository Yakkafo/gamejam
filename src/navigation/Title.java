package navigation;

import model.Level;

import org.newdawn.slick.Graphics;

import system.ControlManager;
import utility.IDynamic;

public class Title extends Scene
{
	/// ATTRIBUTES
	
	/// METHODS
	
	// implementation
	
	public void draw(Graphics g)
	{
		g.drawString("PRESS ANY KEY", 32, 32);
	}

	public IDynamic.Rtn update()
	{
		return IDynamic.Rtn.CONTINUE;
		//if(ControlManager.getInstance());
	}

	@Override
	public Scene getNextScene()
	{
		return new Level();
	}
}

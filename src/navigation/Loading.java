package navigation;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import resources.ResourceManager;
import system.ColourCode;
import system.ControlManager;

import utility.IDynamic;
import utility.IVisible;

public class Loading extends Scene
{
	/// ATTRIBUTES
	Image background;
	
	/// METHODS
	public Loading()
	{
		background = ResourceManager.getInstance().getImage("menu_loading"); 
	}

	@Override
	public void draw(Graphics g)
	{
		background.draw(0, 0, size.x, size.y);
	}

	@Override
	public Scene getNextScene()
	{
		return new Title();
	}

	@Override
	public Rtn update()
	{
		ResourceManager rm = ResourceManager.getInstance();
		
		rm.load();
		rm.getMusic("music_level").loop();
		
		return IDynamic.Rtn.CHANGE_SCENE;
	}

}

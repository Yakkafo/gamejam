package model;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utility.IVisible;

public class Marble implements IVisible
{
	/// ATTRIBUTES
	private Point position;
	private Image image;
	
	public Marble()
	{
		try {
			image = new Image("marbel.PNG");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, position.x, position.y);
		
	}
	
}

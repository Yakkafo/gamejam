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
	private Point target; //Marbel goes to the target
	private MarbelType type; //Marbel color
	
	
	public Marble(Point target)
	{
		try {
			image = new Image("marbel.PNG");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.target = target;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, position.x, position.y);
		
	}
	
}

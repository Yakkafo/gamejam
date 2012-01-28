package model;

import math.dVect;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import utility.ICollider;

public class Marble extends GameObject
{
	/// NESTED DEFINITIONS
	public enum MarbleType 
	{
		RED, GREEN, BLUE
	}

	/// ATTRIBUTES
	private Image image;
	private dVect speed;
	private MarbleType type;

	public Marble(dVect init_position, dVect target)
	{
		super(init_position);
		speed = new dVect(target.x-position.x, target.y-position.y);
	}

	@Override
	public void draw(Graphics g)
	{
		//g.drawImage(image, (float)position.x, (float)position.y);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isColliding(ICollider other)
	{
		// TODO Auto-generated method stub
		return false;
	}

}

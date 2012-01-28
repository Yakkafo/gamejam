package model;

import java.util.Random;

import math.dVect;

import org.newdawn.slick.Graphics;

import utility.IDynamic;

public class Marble extends GameObject
{
	/// CONSTANTS
	private static double DEFAULT_SPEED = 3;

	/// ATTRIBUTES
	private dVect speed;
	private GameObject.Colour colour;

	public Marble(dVect init_position, dVect target)
	{
		super(init_position, new dVect(32,32));
		// Set speed towards target
		speed = new dVect(target.x-position.x, target.y-position.y)
						.normalise().scale(DEFAULT_SPEED);
		// Set colour randomly
		int pick = new Random().nextInt(Colour.values().length);
		colour = Colour.values()[pick];

	}

	@Override
	public void draw(Graphics g)
	{
		drawHitbox(g);
		//ResourceManager.getInstance().getAnimation("red_marble")
			//.draw((float)position.x, (float)position.y);
	}

	@Override
	public IDynamic.Rtn update()
	{
		super.update();
		translatePosition(speed);
		return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public void treatEvent(ObjectEvent e)
	{
		// TODO Auto-generated method stub
		
	}


}

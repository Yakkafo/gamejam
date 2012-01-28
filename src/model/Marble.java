package model;

import java.util.Random;

import math.dVect;
import model.GameObject.Colour;

import org.newdawn.slick.Graphics;

import resources.ResourceManager;

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
		ResourceManager.getInstance().getImage(ColourToImage())
			.draw((float)position.x-24, (float)position.y-24, 48, 48);

	}

	private String ColourToImage()
	{
		switch(colour)
		{
			case RED: return "marble_red";
			case GREEN: return "marble_green";
			case BLUE: return "marble_blue";
			case WHITE: default: return "marble_white";
		}
	}

	@Override
	public IDynamic.Rtn update()
	{
		// Check for interrupts
		IDynamic.Rtn super_rtn = super.update();
		if(super_rtn != IDynamic.Rtn.CONTINUE) return super_rtn;
		// Move marble and continue
		translatePosition(speed);
		return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public void treatEvent(ObjectEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	public Colour getColour()
	{
		return colour;
	}


}

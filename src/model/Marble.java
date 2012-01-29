package model;

import java.util.Random;

import math.FVect;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import resources.ResourceManager;
import system.ColourCode;

import utility.IDynamic;

public class Marble extends GameObject
{
	/// CONSTANTS
	private static float DEFAULT_SPEED = 3;
	private static int DEFAULT_SPIN = 3;

	/// ATTRIBUTES
	private final boolean reverse_spin;
	private FVect speed;
	private int angle = (int)(Math.random()*360);
	private ColourCode colour;

	public Marble(FVect init_position, FVect target)
	{
		super(init_position, new FVect(32,32));
		
		// Randomise
		float rand_x = (float)(Math.random()*64-31);
		float rand_y = (float)(Math.random()*64-31);
		
		// Set speed towards target
		speed = new FVect(target.x+rand_x-position.x, 
						target.y+rand_y-position.y)
				.normalise().scale(DEFAULT_SPEED);
		reverse_spin = Math.random() > 0.5 ? true : false;
		// Set colour randomly
		int pick = new Random().nextInt(ColourCode.values().length);
		colour = ColourCode.values()[pick];

	}

	@Override
	public void draw(Graphics g)
	{
		Image pending = ResourceManager.getInstance().getImage(ColourToImage());
		pending.setRotation(angle);
		pending.draw(position.x-24, position.y-24, 48, 48);

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
		angle += (reverse_spin) ? -DEFAULT_SPEED : DEFAULT_SPEED;
		translatePosition(speed);
		return IDynamic.Rtn.CONTINUE;
	}

	@Override
	public void treatEvent(ObjectEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	public ColourCode getColour()
	{
		return colour;
	}


}

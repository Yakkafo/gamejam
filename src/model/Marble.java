package model;

import java.util.Random;

import math.dVect;

import org.newdawn.slick.Graphics;

import resources.ResourceManager;

public class Marble extends GameObject
{
	/// NESTED DEFINITIONS
	public static enum Type 
	{
		RED, GREEN, BLUE
	}
	
	/// CONSTANTS
	private static double DEFAULT_SPEED = 3;

	/// ATTRIBUTES
	private dVect speed;
	private Type type;

	public Marble(dVect init_position, dVect target)
	{
		super(init_position);
		// Set speed towards target
		speed = new dVect(target.x-position.x, target.y-position.y)
						.normalise().scale(DEFAULT_SPEED);
		// Set colour randomly
		int pick = new Random().nextInt(Type.values().length);
		type = Type.values()[pick];

	}

	@Override
	public void draw(Graphics g)
	{
		//g.drawString("MARBLE", (float)position.x, (float)position.y);
		ResourceManager.getInstance().getAnimation("red_marble").draw((float)position.x, (float)position.y);
	}

	@Override
	public void update()
	{
		position.add(speed);
	}


}

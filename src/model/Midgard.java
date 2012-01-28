package model;

import math.dVect;

import org.newdawn.slick.Graphics;

public class Midgard extends GameObject
{
	/// ATTRIBUTES
	private int hitpoints = 100;
	
	/// METHODS
	
	// creation
	public Midgard(dVect init_position)
	{
		super(init_position, new dVect(32, 32));
	}
	
	// implement

	@Override
	public void draw(Graphics g)
	{
		drawHitbox(g);
		
	}
	
	public void treatEvent(ObjectEvent e)
	{
		
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

}

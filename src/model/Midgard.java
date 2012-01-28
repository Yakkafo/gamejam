package model;

import math.dVect;

import org.newdawn.slick.Graphics;

import utility.IDynamic;

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
		switch(e.getType())
		{
			case COLLISION:
				CollisionEvent ce = (CollisionEvent)e;
				if(ce.getOther().getClass() == Marble.class)
					;
					//other.die();
				break;
		}
		
	}

	@Override
	public IDynamic.Rtn update()
	{
		super.update();
		return IDynamic.Rtn.CONTINUE;
		
	}

}

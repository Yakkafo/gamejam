package model;

import math.dVect;

import org.newdawn.slick.Graphics;

import utility.IDynamic;

public class Midgard extends GameObject
{
	/// CONSTANTS
	public static final int MARBLE_DAMAGE = 10;
	
	/// ATTRIBUTES
	private int hitpoints = 100;
	
	/// METHODS
	
	// creation
	public Midgard(dVect init_position)
	{
		super(init_position, new dVect(32, 32));
	}
	
	// access
	public boolean tryResist(int damage_amount)
	{
		if(hitpoints < damage_amount)
		{
			hitpoints = 0;
			return false;
		}
		
		hitpoints -= damage_amount;
		return true;
	}
	
	// implement

	@Override
	public void draw(Graphics g)
	{
		drawHitbox(g);
		g.drawString(""+hitpoints, (float)position.x, (float)position.y);
	}
	
	public void treatEvent(ObjectEvent e)
	{
		switch(e.getType())
		{
			case COLLISION:
				CollisionEvent ce = (CollisionEvent)e;
				GameObject other = ce.getOther();
				if(other.getClass() == Marble.class)
				{
					other.die();
					if(!tryResist(MARBLE_DAMAGE))
						this.die();
				}
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

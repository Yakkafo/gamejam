package model;

import math.FVect;

import org.newdawn.slick.Graphics;

import utility.IDynamic;

public class Midgard extends GameObject
{
	/// CONSTANTS
	public static final int MAX_HITPOINTS = 100;
	public static final int MARBLE_DAMAGE = 10;
	public static final int MARBLE_HEAL = 5;
	public static final int HEALTH_LOSS_PERIOD = 60;
	
	/// ATTRIBUTES
	private int hitpoints = MAX_HITPOINTS;
	private int bleed_timer = HEALTH_LOSS_PERIOD;
	
	/// METHODS
	
	// creation
	public Midgard(FVect init_position)
	{
		super(init_position, new FVect(32, 32));
	}
	
	// access
	public void heal(int heal_amount)
	{
		
		hitpoints += heal_amount;
	}
	
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
		g.drawString(""+hitpoints, position.x, position.y);
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
					// Destroy the offending marble
					other.die();
					// Heal if it was white
					if(((Marble)other).getColour() == GameObject.Colour.WHITE)
					{
						heal(MARBLE_HEAL);
						level.addBonus(Level.Bonus.HEAL);
					}
					// Take damage otherwise
					else if(!tryResist(MARBLE_DAMAGE))
						this.die();
				}
				break;
		}
		
	}

	@Override
	public IDynamic.Rtn update()
	{
		super.update();
		
		// decrease health slowly if over maximum
		if(hitpoints > MAX_HITPOINTS)
		{
			if(bleed_timer == 0)
			{
				hitpoints--;
				bleed_timer = HEALTH_LOSS_PERIOD;
			}
			else
				bleed_timer--;
				
		}
		
		return IDynamic.Rtn.CONTINUE;
		
	}

}

package model;

import math.FVect;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import resources.ResourceManager;
import system.ColourCode;
import utility.IDynamic;

public class Midgard extends GameObject
{
	/// CONSTANTS
	public static final int MAX_HITPOINTS = 5;
	public static final int MARBLE_DAMAGE = 1;
	public static final int MARBLE_HEAL = 1;
	public static final int N_IMAGES = 5;
	
	/// ATTRIBUTES
	private int hitpoints = MAX_HITPOINTS;
	private Image[] images;
	
	/// METHODS
	
	// creation
	public Midgard(FVect init_position)
	{
		super(init_position, new FVect(96, 96));
		
		ResourceManager rm = ResourceManager.getInstance();
		images = new Image[5];
		for(int i = 0; i < 5; i++)
			images[i] = rm.getImage("earth"+i);
	}
	
	// access
	public void heal(int heal_amount)
	{
		
		hitpoints = Math.min(hitpoints+heal_amount,MAX_HITPOINTS);
	}
	
	public boolean tryResist(int damage_amount)
	{
		if(hitpoints <= damage_amount)
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
		int sub_image = (hitpoints <= 0) ? 0 : hitpoints-1;
		images[sub_image].drawCentered(position.x, position.y);
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
					if(((Marble)other).getColour() == ColourCode.WHITE)
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
		// Check for interrupts
		IDynamic.Rtn super_rtn = super.update();
		if(super_rtn == IDynamic.Rtn.DELETE_OBJECT)
			return IDynamic.Rtn.CHANGE_SCENE;
		else if(super_rtn != IDynamic.Rtn.CONTINUE) 
			return super_rtn;
		
		return IDynamic.Rtn.CONTINUE;
		
	}

}

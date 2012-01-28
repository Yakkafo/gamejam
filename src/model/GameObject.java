package model;

import java.util.LinkedList;
import java.util.Queue;

import org.newdawn.slick.Graphics;

import math.dRect;
import math.dVect;

import utility.IDynamic;
import utility.IVisible;

public abstract class GameObject implements IVisible, IDynamic
{
	/// NESTED DEFINITIONS
	public static enum Colour 
	{
		RED, GREEN, BLUE;
	}
	
	/// ATTRIBUTES
	private boolean dead = false;
	protected dVect position;
	protected dRect hitbox;
	protected Queue<ObjectEvent> events = new LinkedList<ObjectEvent>();
	
	/// METHODS
	
	// creation
	public GameObject(dVect init_position, dVect hitbox_size)
	{
		hitbox = new dRect(0, 0, hitbox_size.x, hitbox_size.y);
		position = init_position;
		positionUpdated();	// move hitbox, etc to position
	}
	
	// access
	public void die()
	{
		dead = true;
	}
	
	public void addEvent(ObjectEvent e)
	{
		events.add(e);
	}
	
	public void translatePosition(dVect translation)
	{
		position.add(translation);
		positionUpdated();
	}
	
	public void setPosition(dVect new_position)
	{
		position = new_position;
		positionUpdated();
	}
	
	// should be called each time position is modified
	public void positionUpdated()
	{
		hitbox.centerOn(position);
	}
	
	public void drawHitbox(Graphics g)
	{
		g.drawRect((float)hitbox.x, (float)hitbox.y,
				(float)hitbox.width, (float)hitbox.height);
	}
	
	// interface
	
	public IDynamic.Rtn update()
	{
		pollEvents();
		return (dead) ? IDynamic.Rtn.KILLME : IDynamic.Rtn.CONTINUE;
	}
	
	public void pollEvents()
	{
        ObjectEvent e = events.poll();
        while(e != null)
        {
        	// pass the event to the specific object to treat
            treatEvent(e);
            // get next event
            e = events.poll();
        }
	}
	
	public abstract void treatEvent(ObjectEvent e);
	
	public boolean isColliding(GameObject other)
	{
		return (!dead && hitbox.intersects(other.hitbox));
	}
}

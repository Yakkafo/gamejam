package model;

import java.util.LinkedList;
import java.util.Queue;

import org.newdawn.slick.Graphics;

import math.FRect;
import math.FVect;

import utility.IDynamic;
import utility.IVisible;

public abstract class GameObject implements IVisible, IDynamic
{
	/// NESTED DEFINITIONS
	public static enum Colour 
	{
		RED, GREEN, BLUE, WHITE;
	}
	
	/// ATTRIBUTES
	private boolean dead = false;
	protected FVect position;
	protected FRect hitbox;
	protected Queue<ObjectEvent> events = new LinkedList<ObjectEvent>();
	protected Level level;
	
	/// METHODS
	
	// creation
	public GameObject(FVect init_position, FVect hitbox_size)
	{
		hitbox = new FRect(0, 0, hitbox_size.x, hitbox_size.y);
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
	
	public void translatePosition(FVect translation)
	{
		position.add(translation);
		positionUpdated();
	}
	
	public void setPosition(FVect new_position)
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
		g.drawRect(hitbox.x, hitbox.y,hitbox.width, hitbox.height);
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

	public void setLevel(Level init_level)
	{
		level = init_level;
	}
}

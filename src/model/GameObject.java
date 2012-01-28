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
		RED, GREEN, BLUE
	}
	
	/// ATTRIBUTES
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
	
	public void update()
	{
		pollEvents();
	}
	
	public void pollEvents()
	{
        ObjectEvent e = events.poll();
        while(e != null)
        {
            System.out.println(e);

            // get next event
            e = events.poll();
        }
	}
	
	public abstract void treatEvent(ObjectEvent e);
	
	public boolean isColliding(GameObject other)
	{
		return hitbox.intersects(other.hitbox);
	}
}

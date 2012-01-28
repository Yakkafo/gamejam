package model;

import java.util.LinkedList;
import java.util.Queue;

import math.dRect;
import math.dVect;

import utility.IDynamic;
import utility.IVisible;

public abstract class GameObject implements IVisible, IDynamic
{
	/// ATTRIBUTES
	protected dVect position;
	protected dRect hitbox;
	protected Queue<ObjectEvent> events = new LinkedList<ObjectEvent>();
	
	/// METHODS
	
	// creation
	public GameObject(dVect init_position)
	{
		position = init_position;
		hitbox = new dRect(0, 0, 64, 64);
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
	
	// interface
	public boolean isColliding(GameObject other)
	{
		return hitbox.intersects(other.hitbox);
	}
}

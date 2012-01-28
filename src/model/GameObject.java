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
	}
	
	// access
	public void addEvent(ObjectEvent e)
	{
		events.add(e);
	}
	
	// interface
	public boolean isColliding(GameObject other)
	{
		return hitbox.intersects(other.hitbox);
	}
}

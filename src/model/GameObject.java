package model;

import java.util.LinkedList;
import java.util.Queue;

import math.dVect;

import utility.ICollider;
import utility.IDynamic;
import utility.IVisible;

public abstract class GameObject implements IVisible, IDynamic, ICollider
{
	/// ATTRIBUTES
	protected dVect position;
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
}

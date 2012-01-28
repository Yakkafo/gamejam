package model;

import math.dVect;

import utility.ICollider;
import utility.IDynamic;
import utility.IVisible;

public abstract class GameObject implements IVisible, IDynamic, ICollider
{
	/// ATTRIBUTES
	protected dVect position;
	
	/// METHODS
	public GameObject(dVect init_position)
	{
		position = init_position;
	}
}

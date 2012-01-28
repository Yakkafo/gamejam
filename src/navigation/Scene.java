package navigation;

import math.FVect;
import utility.IDynamic;
import utility.IVisible;

public abstract class Scene implements IDynamic, IVisible
{
	/// CLASS VARIABLES
	protected static FVect size;
	protected static FVect centre;
	
	/// CLASS FUNCTIONS
	public static void init(FVect init_size)
	{
		// Initialise variables
		size = init_size;
		centre = ((FVect)size.clone()).scale(0.5f);
	}
	
	/// METHODS
	public abstract Scene getNextScene();
}

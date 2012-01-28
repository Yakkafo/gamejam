/* @author william */

package model;

public class ObjectEvent
{
	/// NESTED DEFINITIONS
	public static enum Type
	{
		COLLISION
	}
	
    /// ATTRIBUTES
    private Type type;

    /// METHODS

    // creation
    public ObjectEvent(Type init_type)
    {
        type = init_type;
    }

    // query
    public Type getType()
    {
        return type;
    }


}

/* @author william */

package model;

public class ObjectEvent
{
    /// ATTRIBUTES
    private String type;

    /// METHODS

    // creation
    public ObjectEvent(String init_type)
    {
        type = init_type;
    }

    // query
    public String getType()
    {
        return type;
    }


}

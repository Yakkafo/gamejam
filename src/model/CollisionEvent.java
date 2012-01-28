/* @author william */

package model;

class CollisionEvent extends ObjectEvent
{
    /// CLASS NAMESPACE FUNCTIONS
    public static void generate(GameObject a, GameObject b)
    {
        if(a.isColliding(b))
        {
            a.addEvent(new CollisionEvent(b));
            b.addEvent(new CollisionEvent(a));
        }
    }

    /// ATTRIBUTES
    private GameObject other;

    /// METHODS

    // creation
    public CollisionEvent(GameObject init_other)
    {
        super("actor_collision");
        other = init_other;
    }

    // query
    public GameObject getOther()
    {
        return other;
    }
}

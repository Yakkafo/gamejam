/* @author william */

package math;

import java.awt.Rectangle;

public class iRect extends Rectangle
{
    /// METHODS

    // creation
    public iRect(int int_x, int int_y, int int_w, int int_h)
    {
        super(int_x, int_y, int_w, int_h);
    }

    // query
    public boolean containsAll(iRect r)
    {
        return (contains(r.x, r.y)
                && contains(r.x+r.width, r.y)
                && contains(r.x, r.y+r.height)
                && contains(r.x+r.width, r.y+r.height));
    }
}

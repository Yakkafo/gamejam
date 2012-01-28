/* @author william */

package math;

import java.awt.Point;

public class iVect extends Point
{
	/// CONSTANTS
	private static final long serialVersionUID = 1L;
	
    /// METHODS

    // creation
    public iVect(int init_x, int init_y)
    {
        x = init_x;
        y = init_y;
    }

    // cast
    public dVect dVect()
    {
        return new dVect((double)x, (double)y);
    }

}

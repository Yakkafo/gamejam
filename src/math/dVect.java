/* @author william */

package math;

import java.awt.geom.Point2D;

public class dVect extends Point2D.Double
{
	/// CONSTANTS
	private static final long serialVersionUID = 1L;
	
    /// METHODS

	// creation
    public dVect(double init_x, double init_y)
    {
        x = init_x;
        y = init_y;
    }

    // V2i2D.Double can't be translated!
    public void translate(double dx, double dy)
    {
        x += dx;
        y += dy;
    }

    public dVect add(dVect v)
    {
        x += v.x;
        y += v.y;
        return this;
    }

    public dVect scale(double amount)
    {
        x *= amount;
        y *= amount;
        return this;
    }
    
    public dVect normalise()
    {
    	double norm = getNorm();
    	x /= norm;
    	y /= norm;
    	return this;
    }
    
    // query
    public double getNorm()
    {
    	return Math.sqrt(x*x + y*y);
    }

    // cast
    public iVect iVect()
    {
        return new iVect((int)x, (int)y);
    }
}

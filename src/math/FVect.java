/* @author william */

package math;

import java.awt.geom.Point2D;

public class FVect extends Point2D.Float
{
	/// CONSTANTS
	private static final long serialVersionUID = 1L;
	
    /// METHODS

	// creation
    public FVect(float init_x, float init_y)
    {
        x = init_x;
        y = init_y;
    }

    // V2i2D.Double can't be translated!
    public void translate(float dx, float dy)
    {
        x += dx;
        y += dy;
    }

    public FVect add(FVect v)
    {
        x += v.x;
        y += v.y;
        return this;
    }

    public FVect scale(float amount)
    {
        x *= amount;
        y *= amount;
        return this;
    }
    
    public FVect normalise()
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

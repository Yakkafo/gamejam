/* @author william */

package math;

import java.awt.geom.Rectangle2D;

public class dRect extends Rectangle2D.Double
{
	/// CONSTANTS
	private static final long serialVersionUID = 1L;
	
    /// ATTRIBUTES

    /// METHODS
	// creation
	public dRect(double init_x, double init_y, double init_w, double init_h)
	{
		super(init_x, init_y, init_w, init_h);
	}

	// access
	public dRect translate(dVect v)
	{
		x += v.x;
		y += v.y;
		return this;
	}
	
	public dRect centerOn(dVect v)
	{
		x = v.x - width/2;
		y = v.y - height/2;
		return this;
	}
}

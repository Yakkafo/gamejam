/* @author william */

package math;

import java.awt.geom.Rectangle2D;

public class FRect extends Rectangle2D.Float
{
	/// CONSTANTS
	private static final long serialVersionUID = 1L;
	
    /// ATTRIBUTES

    /// METHODS
	// creation
	public FRect(float init_x, float init_y, float init_w, float init_h)
	{
		super(init_x, init_y, init_w, init_h);
	}

	// access
	public FRect translate(FVect v)
	{
		x += v.x;
		y += v.y;
		return this;
	}
	
	public FRect centerOn(FVect v)
	{
		x = v.x - width/2;
		y = v.y - height/2;
		return this;
	}
}

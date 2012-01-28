/* @author william */

package resources;

import java.awt.image.BufferedImage;
import math.IRect;

public class Graphic
{
    /// ATTRIBUTES
    protected BufferedImage image;
    protected IRect frame;

    /// METHODS

    // creation
    public Graphic(BufferedImage init_image, IRect init_frame)
    {
        image = init_image;
        frame = init_frame;
    }

    // query
    public BufferedImage getImage()
    {
        return image;
    }

    public IRect getFrame()
    {
        return frame;
    }
}

/* @author william */

package resources;

import java.awt.image.BufferedImage;
import math.iRect;

public class Graphic
{
    /// ATTRIBUTES
    protected BufferedImage image;
    protected iRect frame;

    /// METHODS

    // creation
    public Graphic(BufferedImage init_image, iRect init_frame)
    {
        image = init_image;
        frame = init_frame;
    }

    // query
    public BufferedImage getImage()
    {
        return image;
    }

    public iRect getFrame()
    {
        return frame;
    }
}

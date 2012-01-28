/* @author william */

package view;

import utility.IVisible;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import resources.Graphic;
import math.iRect;
import math.iVect;

public class GraphicCanvas implements IVisible
{
    /// ATTRIBUTES
    protected Graphic graphic;
    protected iRect dest;
    protected boolean flip = false;

    /// METHODS

    // creation
    public GraphicCanvas(Graphic init_graphic, iRect init_dest)
    {
        graphic = init_graphic;
        dest = init_dest;
    }

    // update
    public void draw(Graphics2D g2d)
    {
        // Grab the appropriate subimage
        iRect src = getSubrect();
        try
        {
            BufferedImage subimage = graphic.getImage().getSubimage(src.x, src.y,
                                                    src.width, src.height);
            // Draw it on the screen
            g2d.drawImage(subimage, dest.x, dest.y, dest.width, dest.height, null);
        }
        catch (java.awt.image.RasterFormatException e)
        {
            System.out.println("Problem with " + getSubrect());
        }
    }

    // modification
    public void setPosition(iVect new_position)
    {
        dest.setLocation(new_position);
        if(flip)
            dest.x -= dest.width;
    }

    public void setFlip(boolean new_flip)
    {
        if(new_flip == flip)
            return;

        flip = new_flip;
        dest.x += dest.width;
        dest.width *= -1;
    }

    /// SUBROUTINES

    protected iRect getSubrect()
    {
        // This is overriden for animated canvases
        return graphic.getFrame();
    }
}

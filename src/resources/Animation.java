/* @author william */

package resources;

import java.awt.image.BufferedImage;
import math.iRect;

public class Animation extends Graphic
{
    /// NESTED TYPES
    public static enum LoopType
    {
        STOP_AT_END,
        STOP_AT_START,
        WRAP_AROUND,
        ALTERNATE_DIRECTION;
    };

    /// ATTRIBUTES -- INHERITED
    // private BufferedImage image;
    // private iRect frame;
    /// ATTRIBUTES
    private iRect strip;
    private int numFrames;
    private LoopType loopType;

    /// METHODS

    // creation
    public Animation(BufferedImage init_image, iRect init_frame,
            int init_numFrames)
    {
        super(init_image, init_frame);
        numFrames = init_numFrames;
        strip = new iRect(frame.x, frame.y,
                frame.width*numFrames, frame.height);
    }

    public Animation(BufferedImage init_image, iRect init_frame,
            int init_numFrames, LoopType init_loopType)
    {
        this(init_image, init_frame, init_numFrames);
        loopType = init_loopType;
    }

    public iRect getFrame(double frame_number)
    {
        if(frame_number == 0)
            return getFrame();

        // check that the requested area is not out of bounds
        int offset = (int)(Math.floor(frame_number) * frame.width);
        if(offset + frame.width > strip.width)
            return frame;

        // return a translated version of the frame
        iRect result = (iRect)frame.clone();
        result.translate(offset, 0);
        return result;
    }
    
    public int getNumFrames()
    {
        return numFrames;
    }

    public LoopType getLoopType()
    {
        return loopType;
    }
}

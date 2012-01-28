/* @author william */

package view;

import resources.Animation;
import math.iRect;

public class AnimationCanvas extends GraphicCanvas
        // implements IVisible via GraphicCanvas
{
    /// ATTRIBUTES -- inherited
    // private Graphic graphic;
    // private iRect dest;

    // ATTRIBUTES
    private float current_frame = 0;
    private float frame_speed;

    /// METHODS

    // creation
    public AnimationCanvas(Animation init_anim, iRect init_dest)
    {
        super(init_anim, init_dest);
        frame_speed = 0.3f;    // FIXME
    }

    // update
    public void animate()
    {
        // Increment frame
        current_frame += frame_speed;

        // Detect if we're over the maximum number of frames
        Animation animation = ((Animation)graphic);
        int frame_number = animation.getNumFrames();
        if(current_frame >= frame_number || current_frame < 0)
        {
            switch(animation.getLoopType())
            {
                case STOP_AT_END:
                    current_frame = frame_number - 1;
                    frame_speed = 0;
                    break;

                case STOP_AT_START:
                    current_frame = 0;
                    frame_speed = 0;
                    break;

                case WRAP_AROUND:
                    current_frame -= Math.signum(frame_speed)*frame_number;
                    break;

                case ALTERNATE_DIRECTION:
                    current_frame = (current_frame < 0) ? 
                                    0 : frame_number - 1 - frame_speed;
                    frame_speed *= -1;
                    break;
                
            }
        }
    }

    /// SUBROUTINES
    @Override
    protected iRect getSubrect()
    {
        // offset the frame based on current animation subimage
        return ((Animation)graphic).getFrame(current_frame);
    }

}

/* @author william */

package resources;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ResourceManager
{
    /// CLASS NAMESPACE CONSTANTS
	private static final String RESDIR = "data/";
    private static final String SNAKE1 = "snake1.png";
    private static final String SNAKE2 = "snake2.png";
    private static final String REDMARBLE = "redMarble.PNG";

    /// CLASS NAMESPACE VARIABLES
    private static ResourceManager instance = null;

    /// CLASS NAMESPACE FUNCTIONS
    public static ResourceManager getInstance()
    {
        if(instance == null)
            instance = new ResourceManager();
        return instance;
    }

    /// ATTRIBUTES
    private HashMap<String, Animation> animations 
    					= new HashMap<String, Animation>();


    /// METHODS
    
    // creation
    private ResourceManager()
    {
    	try
		{
    		// Snakes
			Image[] snake_frames = { new Image(RESDIR+SNAKE1), new Image(RESDIR+SNAKE2) };
			int [] duration = {300, 300};
			animations.put("snake", new Animation(snake_frames, duration, true));
			
			// Marbles
			Image[] redMarble_frames = { new Image(RESDIR+REDMARBLE) };
			animations.put("redMarble", new Animation(redMarble_frames, 300, true));
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    // query
    public Animation getAnimation(String animation_name)
    {
        return animations.get(animation_name);
    }
    
    // addition
    protected void addAnimation()
    {
    }
    
}

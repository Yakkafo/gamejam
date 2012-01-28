/* @author william */

package resources;

import java.util.HashMap;

import org.newdawn.slick.Animation;


public class ResourceManager
{
    /// CLASS NAMESPACE CONSTANTS
     private static final String PLAYER_IMAGE = "fox.png";

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

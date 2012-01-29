/* @author william */

package resources;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;


public class ResourceManager
{
    /// CLASS NAMESPACE CONSTANTS
	// system
	public static final String RESDIR = "data/";
	public static final String PNG = ".png";
	public static final String JPG = ".jpg";
	// title
	private static final String CREDITS = "credits";
	private static final String GAMEOVER = "game_over";
	private static final String HOWTO = "how_to_play";
	private static final String TITLE = "title";
	// game
	private static final String WHEEL_BACK = "wheel_back";
	private static final String SNAKE_GREEN = "snake_green";
	private static final String SNAKE_RED = "snake_red";
	private static final String SNAKE_BLUE = "snake_blue";
    private static final String MARBLE_RED = "marble_red";
    private static final String MARBLE_BLUE = "marble_blue";
    private static final String MARBLE_GREEN = "marble_green";
    private static final String MARBLE_WHITE = "marble_white";

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
    private HashMap<String, Image> images 
		= new HashMap<String, Image>();
    private TrueTypeFont font;

    /// METHODS
    
    // creation
    private ResourceManager()
    {
		// Images
    	// menu
    	addImage(TITLE, PNG);
    	addImage(CREDITS, PNG);
    	addImage(HOWTO, JPG);
    	addImage(GAMEOVER, JPG);
    	// game
    	addImage(WHEEL_BACK, PNG);
    	addImage(SNAKE_BLUE, PNG);
    	addImage(SNAKE_RED, PNG);
    	addImage(SNAKE_GREEN, PNG);
    	addImage(MARBLE_RED, PNG);
    	addImage(MARBLE_GREEN, PNG);
    	addImage(MARBLE_BLUE, PNG);
    	addImage(MARBLE_WHITE, PNG);
    	
    	// Font
		Font temp = new Font("Dominican Small Caps", Font.PLAIN, 32);
		font = new TrueTypeFont(temp, true);	
    }

    // query
    
    public TrueTypeFont getFont()
    {
    	return font;
    }
    
    public Animation getAnimation(String animation_name)
    {
        Animation result = animations.get(animation_name);
        if(result == null)
        	System.out.println("Couldn't find animation " + animation_name);
        return result;
    }
    
    public Image getImage(String image_name)
    {
    	Image result = images.get(image_name);
        if(result == null)
        	System.out.println("Couldn't find image " + image_name);
        return result;
    }
    
    // addition
    protected void addImage(String name, String ext)
    {
    	try
		{
    		// Try to load the image
    		Image new_image = new Image(RESDIR+name+ext);
    		// We will always rotate around the centre
    		new_image.setCenterOfRotation(new_image.getWidth()/2, 
										new_image.getHeight()/2);
    		// Add to the hashmap
			images.put(name, new_image);
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void addAnimation(String name, int n_frames, int frame_duration)
    {
		try
		{
			Animation new_anim = new Animation();
			
			// Load and add each frame
	    	Image frame_i;
	    	for(int i = 0; i < n_frames; i++)
	    	{
	    		frame_i = new Image(RESDIR+name+i+PNG);
	    		new_anim.addFrame(frame_i, frame_duration);
	    	}
	    	
	    	// Save the animation
	    	animations.put(name, new_anim);
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    }
    
}

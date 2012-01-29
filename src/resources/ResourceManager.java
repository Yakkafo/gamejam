/* @author william */

package resources;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;


public class ResourceManager
{
    /// CLASS NAMESPACE CONSTANTS
	// system
	public static final String RESDIR = "data/";
	public static final String PNG = ".png";
	public static final String JPG = ".jpg";
	public static final String OGG = ".ogg";
	// menu
	private static final String CREDITS = "menu_credits";
	private static final String GAMEOVER = "menu_lose";
	private static final String HOWTO = "menu_howto";
	private static final String TITLE = "menu_title";
	private static final String LOADING = "menu_loading";
	// music, sound
	private static final String MUSIC_LEVEL = "music_level";
	private static final String MUSIC_MENU = "music_menu";
	// game
	private static final String WHEEL_BACK = "wheel_back";
	private static final String SNAKE_GREEN = "snake_green";
	private static final String SNAKE_RED = "snake_red";
	private static final String SNAKE_BLUE = "snake_blue";
	private static final String HEAD_GREEN = "head_green";
	private static final String HEAD_RED = "head_red";
	private static final String HEAD_BLUE = "head_blue";
    private static final String MARBLE_RED = "marble_red";
    private static final String MARBLE_BLUE = "marble_blue";
    private static final String MARBLE_GREEN = "marble_green";
    private static final String MARBLE_WHITE = "marble_white";
    private static final String BACKGROUND = "background";
    private static final String EARTH = "earth";

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
    private boolean loaded = false;
    private HashMap<String, Image> images = new HashMap<String, Image>();
    private HashMap<String, Sound> sounds = new HashMap<String, Sound>();
    private HashMap<String, Music> musics = new HashMap<String, Music>();
    private TrueTypeFont font;
    private TrueTypeFont fontBig;

    /// METHODS
    
    // creation
    private ResourceManager()
    {
    	// Load the loading image+music to display while loading ;)
    	addImage(LOADING, JPG);    	
    }
    
    public void load()
    {
    	if(loaded)
    		return;
    	loaded = true;
    	
    	// Music
    	addMusic(MUSIC_MENU, OGG);
    	addMusic(MUSIC_LEVEL, OGG);
    	
    	// Sounds
    	
		// Images
    	// menu
    	addImage(TITLE, JPG);
    	addImage(CREDITS, JPG);
    	addImage(HOWTO, JPG);
    	addImage(GAMEOVER, JPG);
    	// game
    	addImage(BACKGROUND, JPG);
    	addImage(WHEEL_BACK, PNG);
    	addImage(SNAKE_BLUE, PNG);
    	addImage(SNAKE_RED, PNG);
    	addImage(SNAKE_GREEN, PNG);
    	addImage(MARBLE_RED, PNG);
    	addImage(MARBLE_GREEN, PNG);
    	addImage(MARBLE_BLUE, PNG);
    	addImage(MARBLE_WHITE, PNG);
    	
    	// Animations
    	addImages(HEAD_RED, 3);
    	addImages(HEAD_GREEN, 3);
    	addImages(HEAD_BLUE, 3);
    	addImages(EARTH, 5);
    	
    	// Font
		Font temp = new Font("Dominican Small Caps", Font.PLAIN, 32);
		font = new TrueTypeFont(temp, true);	
		temp = new Font("Dominican Small Caps", Font.PLAIN, 48);
		fontBig = new TrueTypeFont(temp, true);
    }

    // query
    
    public TrueTypeFont getFont()
    {
    	return font;
    }
    
    public TrueTypeFont getFontBig()
    {
    	return fontBig;
    }
    
    public Sound getSound(String sound_name)
    {
    	Sound result = sounds.get(sound_name);
        if(result == null)
        	System.out.println("Couldn't find sound " + sound_name);
        return result;
    }
    
    public Music getMusic(String music_name)
    {
    	Music result = musics.get(music_name);
        if(result == null)
        	System.out.println("Couldn't find music " + music_name);
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
    
    protected void addSound(String name, String ext)
    {
    	try
		{
			Sound new_sound = new Sound(RESDIR+name+ext);
			sounds.put(name, new_sound);
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void addMusic(String name, String ext)
    {
    	try
		{
			Music new_music = new Music(RESDIR+name+ext);
			musics.put(name, new_music);
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
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
    
    protected void addImages(String name, int n_images)
    {
		try
		{
			// Load and add each image
	    	Image image_i;
	    	for(int i = 0; i < n_images; i++)
	    	{
	    		image_i = new Image(RESDIR+name+i+PNG);
	    		images.put(name+i, image_i);
	    	}
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    }
    
}

package system;

import org.newdawn.slick.GameContainer;

import resources.ResourceManager;

public class ControlManager {

	/// CLASS NAMESPACE CONSTANTS

	
   /// CLASS NAMESPACE VARIABLES
   private static ControlManager instance = null;

   /// CLASS NAMESPACE FUNCTIONS
   public static ControlManager getInstance()
   {
       if(instance == null)
           instance = new ControlManager();
       return instance;
   }
   
   /// ATTRIBUTES
   private GameContainer container;
   
   public ControlManager(GameContainer container)
   {
	   this.container = container;
   }
	
}

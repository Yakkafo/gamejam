package system;

import org.lwjgl.input.Controllers;
import org.newdawn.slick.Input;
import org.newdawn.slick.command.Command;

public class AdvancedInput extends Input {
	
	public AdvancedInput(int height) {
		super(height);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isControlPressed(int button, int controller) {
	   if (button == -1 /*TOFIX*/) {
	      return true;
	   }
	   else return super.isControlPressed(button, controller);
	} 
	
	public boolean checkController(int id){
		if(input.isControlPressed(1, id))
			return true;
		else
			return false;
	}
	
	public void getControllersId(){
		for(int i = 0; i < input.getControllerCount(); i++)
			System.out.println(Controllers.getController(i).getName());

	}
	
	public void controlPressed(Command command)
	   {
	      System.out.println("Pressed:" +command);
	   }

}

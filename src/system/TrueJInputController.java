package system;

import java.util.ArrayList;

import net.java.games.input.Component;

import org.lwjgl.input.Controller;

public class TrueJInputController implements Controller {

        /** The JInput controller this class is wrapping */
        private net.java.games.input.Controller target;
       /** The index that has been assigned to this controller */
       private int index;
	        /** The Buttons that have been detected on the JInput controller */
	        private ArrayList buttons = new ArrayList();
	        /** The Axes that have been detected on the JInput controller */
	        private ArrayList axes = new ArrayList();
	        /** The POVs that have been detected on the JInput controller */
	        private ArrayList pov = new ArrayList();
	/** The state of the buttons last check */
        private boolean[] buttonState;
        /** The values that were read from the pov last check */
	        private float[] povValues;
	        /** The values that were read from the axes last check */
	        private float[] axesValue;
	        /** The maximum values read for each axis */
        private float[] axesMax;
	        /** The dead zones for each axis */
	        private float[] deadZones;
        /** The index of the X axis or -1 if no X axis is defined */
	        private int xaxis = -1;
	        /** The index of the Y axis or -1 if no Y axis is defined */
        private int yaxis = -1;
       /** The index of the X axis or -1 if no Z axis is defined */
       private int zaxis = -1;
        /** The index of the RX axis or -1 if no RX axis is defined */
       private int rxaxis = -1;
        /** The index of the RY axis or -1 if no RY axis is defined */
	        private int ryaxis = -1;
	        /** The index of the RZ axis or -1 if no RZ axis is defined */
	        private int rzaxis = -1;
	
	 public TrueJInputController(int index,net.java.games.input.Controller target) {
               this.target = target;
                this.index = index;
                
                Component[] sourceAxes = target.getComponents();
               
	                for (int i=0;i<sourceAxes.length;i++) {
                        if (sourceAxes[i].getIdentifier() instanceof Component.Identifier.Button) {
                                buttons.add(sourceAxes[i]);             
                        } else if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.POV)) {
                                pov.add(sourceAxes[i]);
                        } else {
                              axes.add(sourceAxes[i]);
	                        }
                }
	                
	                buttonState = new boolean[buttons.size()];
	                povValues = new float[pov.size()];
                axesValue = new float[axes.size()];
	                int buttonsCount = 0;
	                int axesCount = 0;
	                
	                // initialise the state
	                for (int i=0;i<sourceAxes.length;i++) {
	                        if (sourceAxes[i].getIdentifier() instanceof Component.Identifier.Button) {
	                                buttonState[buttonsCount] = sourceAxes[i].getPollData() != 0;
	                                buttonsCount++;
	                        } else if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.POV)) {
	                                // no account for POV yet
	                                // pov.add(sourceAxes[i]);
	                        } else {
	                                axesValue[axesCount] = sourceAxes[i].getPollData();
	                                if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.X)) {
	                                        xaxis = axesCount;
	                                }
	                                if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.Y)) {
	                                        yaxis = axesCount;
	                                }
                                if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.Z)) {
                                        zaxis = axesCount;
                                }
                                if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.RX)) {
	                                        rxaxis = axesCount;
	                                }
	                                if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.RY)) {
	                                        ryaxis = axesCount;
	                                }
	                                if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.RZ)) {
                                        rzaxis = axesCount;
                                }
                                
	                                axesCount++;
                        }
                }	                
	                axesMax = new float[axes.size()];
	                deadZones = new float[axes.size()];
	                
	                for (int i=0;i<axesMax.length;i++) {
	                        axesMax[i] = 1.0f;
	                        deadZones[i] = 0.05f;
	                }
	        }
	 
	 public boolean isButtonDown(){
		 return false;
		 
	 }
	
	@Override
	public int getAxisCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAxisName(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getAxisValue(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getButtonCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getButtonName(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getDeadZone(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getPovX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getPovY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRXAxisDeadZone() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRXAxisValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRYAxisDeadZone() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRYAxisValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRZAxisDeadZone() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRZAxisValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getXAxisDeadZone() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getXAxisValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYAxisDeadZone() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYAxisValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getZAxisDeadZone() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getZAxisValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isButtonPressed(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void poll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDeadZone(int arg0, float arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRXAxisDeadZone(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRYAxisDeadZone(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRZAxisDeadZone(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setXAxisDeadZone(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setYAxisDeadZone(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setZAxisDeadZone(float arg0) {
		// TODO Auto-generated method stub

	}

}

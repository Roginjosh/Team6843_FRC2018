package org.usfirst.frc.team6843.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsBase extends Subsystem {
	
	private Compressor Compressor = new Compressor(0);
	private DoubleSolenoid cubePush = new DoubleSolenoid(2, 1);
	private DoubleSolenoid jaws = new DoubleSolenoid(5, 4);
	private boolean cubePushToggle = true;
	private boolean toggleJawsToggle = true;
	private boolean toggleClutch = true;
	private Solenoid clutch = new Solenoid(0);
	
    public void initDefaultCommand() {
       
    }
    
    public void toggleClutch() {
      	if(toggleClutch == true) {
    			clutch.set(true);

    		} else {
    			clutch.set(false);
    		} 	
    }
    
    public boolean JawsState() {
    		return toggleJawsToggle;
    }
    
    public void ToggleJaws() {
    	toggleJawsToggle = !toggleJawsToggle;
    	if(toggleJawsToggle == true) {
			jaws.set(DoubleSolenoid.Value.kForward);
		} else {
			jaws.set(DoubleSolenoid.Value.kReverse);
		} 	
    }
    
    public void OpenJaws() {
    	toggleJawsToggle = true;
			jaws.set(DoubleSolenoid.Value.kForward);
    }
    
    public void initializeSetup() {
    	cubePush.set(DoubleSolenoid.Value.kForward);
    	jaws.set(DoubleSolenoid.Value.kForward);
    }
    
    public void cubePush() {
    		cubePushToggle = !cubePushToggle;
    		if(cubePushToggle == true) {
			cubePush.set(DoubleSolenoid.Value.kForward);

		} else {
			cubePush.set(DoubleSolenoid.Value.kReverse);
		} 	    
    	}
}
    
    

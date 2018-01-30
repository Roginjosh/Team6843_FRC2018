package org.usfirst.frc.team6843.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsBase extends Subsystem {

	public Compressor Compressor = new Compressor(0);
	public DoubleSolenoid cubePush = new DoubleSolenoid(0,1);
	public DoubleSolenoid jaws = new DoubleSolenoid(2,3);
	public boolean cubePushToggle = true;
	public boolean toggleJawsToggle = true;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void ToggleJaws() {
    	toggleJawsToggle = !toggleJawsToggle;
    	if(toggleJawsToggle == true) {
			jaws.set(DoubleSolenoid.Value.kForward);

		} else {
			jaws.set(DoubleSolenoid.Value.kReverse);
		} 	
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


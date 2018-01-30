package org.usfirst.frc.team6843.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsBase extends Subsystem {

	public Solenoid cubePush = new Solenoid(0);
	public DoubleSolenoid jaws = new DoubleSolenoid(1, 2);
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
    
    public void cubePushOn() {
    cubePush.set(true);
    }
    
    public void cubePushOff() {
    	cubePush.set(false);
    }
}


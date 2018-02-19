package org.usfirst.frc.team6843.robot.commands;

import org.usfirst.frc.team6843.robot.OI;
import org.usfirst.frc.team6843.robot.Robot;
import org.usfirst.frc.team6843.robot.subsystems.LiftVertAxis;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualOverride extends Command {

	protected LiftVertAxis LiftVertAxis;
	protected OI oi;
	
    public ManualOverride() {
        this.LiftVertAxis = Robot.getInstance().getLiftVertAxis();
        requires(LiftVertAxis);
        
    		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		this.oi = Robot.getInstance().getOI();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.LiftVertAxis.manualOverride(-(this.oi.getBMGamepadAxis5() * .75));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {

    public Wait() {
        
    }

    protected void initialize() {
    	setTimeout(.5);
    }

    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    if (isTimedOut()) {
    	return true;
    } else {
    	return false;
    }
    }
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

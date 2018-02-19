package org.usfirst.frc.team6843.robot.commands;

import org.usfirst.frc.team6843.robot.OI;
import org.usfirst.frc.team6843.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6843.robot.subsystems.LiftVertAxis;


public class LiftGoToScale extends Command {
	
protected LiftVertAxis LiftVertAxis;
protected OI oi;

    public LiftGoToScale() {
       	this.LiftVertAxis = Robot.getInstance().getLiftVertAxis();
        requires(this.LiftVertAxis);  
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		setTimeout(1.75);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
 //   this.LiftVertAxis.liftDrive(.75);
    	  this.LiftVertAxis.goToHeight(9000);
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
    	this.LiftVertAxis.goToHeight(9000);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

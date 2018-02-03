package org.usfirst.frc.team6843.robot.commands;

import org.usfirst.frc.team6843.robot.OI;
import org.usfirst.frc.team6843.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6843.robot.subsystems.LiftVertAxis;

/**
 * @param <LiftVertAxis>
 *
 */
public class LiftGoToBottom extends Command {
	
protected LiftVertAxis LiftVertAxis;
protected OI oi;

    public LiftGoToBottom() {
       	this.LiftVertAxis = Robot.getInstance().getLiftVertAxis();
        requires(this.LiftVertAxis);  
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.LiftVertAxis.goToHeight(0);
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

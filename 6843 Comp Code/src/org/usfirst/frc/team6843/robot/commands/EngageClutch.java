package org.usfirst.frc.team6843.robot.commands;

import org.usfirst.frc.team6843.robot.Robot;
import org.usfirst.frc.team6843.robot.subsystems.LiftVertAxis;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EngageClutch extends Command {
	LiftVertAxis LiftVertAxis;
    public EngageClutch() {
        this.LiftVertAxis = Robot.getInstance().getLiftVertAxis();
        requires(LiftVertAxis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		this.LiftVertAxis.engageClutch();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    		this.LiftVertAxis.manualOverride(0);
    		this.LiftVertAxis.toggleClimbMode();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

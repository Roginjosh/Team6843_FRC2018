package org.usfirst.frc.team6843.robot.commands;

import org.usfirst.frc.team6843.robot.OI;
import org.usfirst.frc.team6843.robot.Robot;
import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DistDrive extends Command {

	protected final DriveSubsystem driveSubsystem;
	protected OI oi;
	
    public DistDrive() {
        this.driveSubsystem = Robot.getInstance().getDriveSubsystem();
        requires(this.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		super.initialize();
		this.oi = Robot.getInstance().getOI();
		this.driveSubsystem.ClearEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    		this.driveSubsystem.strateMasheen(.26, -.30);  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		if(this.driveSubsystem.getRawLeftEncoderCounts() >= 10400 && this.driveSubsystem.getRawRightEncoderCounts() <= -10400) {
    					return true;
    } else {
    	return false;
    			}
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

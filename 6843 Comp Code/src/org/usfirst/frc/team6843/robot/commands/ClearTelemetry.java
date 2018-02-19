package org.usfirst.frc.team6843.robot.commands;

import org.usfirst.frc.team6843.robot.Robot;
import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.Command;


public class ClearTelemetry extends Command {

    private DriveSubsystem driveSubsystem;

	public ClearTelemetry() {
    	this.driveSubsystem = Robot.getInstance().getDriveSubsystem();
		requires(this.driveSubsystem);
    }

    protected void initialize() {
   
    }

    protected void execute() {
    	this.driveSubsystem.ClearEncoders();
    	this.driveSubsystem.resetGyro();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
   
    }

    protected void interrupted() {
  
    }
}

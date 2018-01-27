/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team6843.robot.OI;
import org.usfirst.frc.team6843.robot.Robot;
import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;

/**
 * An example command.  You can replace me with your own command.
 
public class ExampleCommand extends Command {
	public ExampleCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kExampleSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
*/
public class RightTurnn extends Command {
	protected /* final */ OI oi;
	private DriveSubsystem driveSubsystem;
	public RightTurnn() {
		this.driveSubsystem = Robot.getInstance().getDriveSubsystem();
		requires(this.driveSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		super.initialize();
		this.oi = Robot.getInstance().getOI();
		this.driveSubsystem.ClearEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (this.oi.buttonX.get()) {
			this.driveSubsystem.encoderTest(0.3 * 575, 0.3 * -1000);
		} else { 
			this.driveSubsystem.encoderTest(1000, -575);

		}
	}
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if((this.driveSubsystem.getLeftPosition() >= 90 && this.driveSubsystem.getRightPosition() >= 48) || 
				(this.driveSubsystem.getLeftPosition() >= 48 && this.driveSubsystem.getRightPosition() >= 95)){
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		//this.driveSubsystem.encoderTest(300, -800);
		this.driveSubsystem.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		super.interrupted();
	}
}

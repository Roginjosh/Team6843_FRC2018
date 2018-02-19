/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

package org.usfirst.frc.team6843.robot.commands;

import org.usfirst.frc.team6843.robot.OI;
import org.usfirst.frc.team6843.robot.Robot;
import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command.  You can replace me with your own command.
 */
public class ThrottledDrive extends Command {
	/**
	 * The subsystem can and should be initialized in the constructor since the
	 * subsystems are the first thing created during robot initialization and we
	 * want to have the command requirements correct from the start.
	 */
	protected final DriveSubsystem driveSubsystem;

	/**
	 * We would like to have {@link OI} final and initialized during construction
	 * but the commands are typically created as part of OI initialization so OI may
	 * not be available as this command is constructed. So, initialization of this
	 * field is put off until {@link #initialize()}.
	 */
	protected /* final */ OI oi;

	public ThrottledDrive() {
		this.driveSubsystem = Robot.getInstance().getDriveSubsystem();
		requires(this.driveSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		super.initialize();
		this.oi = Robot.getInstance().getOI();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double power = this.oi.getVertAxis();
		double curve = this.oi.getHorizAxis();
		this.driveSubsystem.TalonVeloDrive(0.4 * power, 0.4 * curve);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		this.driveSubsystem.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		super.interrupted();
	}
}

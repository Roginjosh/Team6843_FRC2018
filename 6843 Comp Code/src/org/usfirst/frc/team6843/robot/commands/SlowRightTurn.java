/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team6843.robot.AutoParameters;
import org.usfirst.frc.team6843.robot.OI;
import org.usfirst.frc.team6843.robot.Robot;
import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;

public class SlowRightTurn extends Command {
	protected /* final */ OI oi;
	private DriveSubsystem driveSubsystem;
	AutoParameters testTurn;
	public SlowRightTurn() {
		this.driveSubsystem = Robot.getInstance().getDriveSubsystem();
		requires(this.driveSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		super.initialize();
		this.oi = Robot.getInstance().getOI();
		//testTurn = this.driveSubsystem.autoTurnValue(360.0, 40.0, 500.0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		this.driveSubsystem.ternMasheen(1);
		}
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(this.driveSubsystem.getAngle() >= 83) {
				return true;
		} else {
			return false;
		}	
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		this.driveSubsystem.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		super.interrupted();
	}
}

/*package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import org.usfirst.frc.team6843.robot.subsystems.PneumaticsBase;

import org.usfirst.frc.team6843.robot.Robot;
import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class ToggleJaws extends Command{
	
	protected PneumaticsBase PneumaticsBase;
	
	public ToggleJaws() {
		this.PneumaticsBase = Robot.getInstance().getPneumaticsBase();
		requires(this.PneumaticsBase);

	}
	
	
	// Called just before this Command runs the first time
	    protected void initialize() {
	     }
		
		// Called repeatedly when this Command is scheduled to run
		
		protected void execute() {
		this.PneumaticsBase.ToggleJaws();
		}
		// Make this return true when this Command no longer needs to run execute()
		protected boolean isFinished() {
			return true;
				
		}
		
		// Called once after isFinished returns true
		protected void end() {
		}
	

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		protected void interrupted() {
		}
	}
*/

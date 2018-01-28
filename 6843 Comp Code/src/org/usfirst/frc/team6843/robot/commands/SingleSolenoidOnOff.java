package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class SingleSolenoidOnOff {
	
		private Solenoid single = new Solenoid(0);
		
		private int a = 0 ;
		
	// Called just before this Command runs the first time
	    protected void initialize() {
	    }
		
		// Called repeatedly when this Command is scheduled to run
		
		protected void execute() {
			single.set(true);
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			single.set(false);
			a = a + 1 ;
		}
		
		// Make this return true when this Command no longer needs to run execute()
		protected boolean isFinished() {
			if(a >= 1 ){
				return true ;
			} else {
				return false ;
			}
				
		}
		
		// Called once after isFinished returns true
		protected void end() {
			a = 0;
		}
	

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		protected void interrupted() {
		}
	}


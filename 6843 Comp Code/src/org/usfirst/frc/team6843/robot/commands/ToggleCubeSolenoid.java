package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6843.robot.subsystems.PneumaticsBase;
import org.usfirst.frc.team6843.robot.Robot;

public class ToggleCubeSolenoid extends Command{
	protected PneumaticsBase PneumaticsBase;
	
		public ToggleCubeSolenoid() {
			this.PneumaticsBase = Robot.getInstance().getPneumaticsBase();
			requires(this.PneumaticsBase);
	
		}
	
	
	    protected void initialize() {
	    	
	    }
		
		protected void execute() {
		this.PneumaticsBase.cubePush();
		
		}

		protected boolean isFinished() {
			return true;
		}
			
		
		
		protected void end() {
		
		}
	

		protected void interrupted() {

		}
	}


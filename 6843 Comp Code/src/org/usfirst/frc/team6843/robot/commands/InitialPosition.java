package org.usfirst.frc.team6843.robot.commands;

import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team6843.robot.subsystems.PneumaticsBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InitialPosition extends Command {
	DriveSubsystem driveSubsystem;
	PneumaticsBase PneumaticsBase;
	//LiftVertAxis LiftVertAxis;
	
    public InitialPosition() {
    		requires(driveSubsystem);
    		requires(PneumaticsBase);
    		//requires(LiftVertAxis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		this.PneumaticsBase.initializeSetup();
    	//	this.LiftVertAxis.goToHeight(-100);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    //	 if (this.LiftVertAxis.bottomLimit.get()) {
    	//	 return true;
   // 	 } else {
        return false;
    	 //}
   }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftSwitch extends CommandGroup {

    	public  AutoLeftSwitch() {
    		addSequential(new ClearEncoders());
    		addSequential(new DistDrive168());
    		addSequential(new ClearEncoders());
    		addSequential(new ZoopZoopRight());
    		addSequential(new ClearEncoders());
    		// Put code to raise lift to switch height here
    		addSequential(new ClearEncoders());
    		addSequential(new DistDrive41());
    		addSequential(new ClearEncoders());
    		// Put code here to push cube onto switch here
    		addSequential(new DistDriveReverse41());
    		addSequential(new ClearEncoders());
    		addSequential(new ZoopZoopLeft1());
    		addSequential(new ClearEncoders());
    		addSequential(new DistDrive100());
    	}
}
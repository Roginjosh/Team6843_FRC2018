package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightSwitch extends CommandGroup {

	public  AutoRightSwitch() {
		addSequential(new ClearEncoders());
		addSequential(new DistDrive168());
		addSequential(new ClearEncoders());
		addSequential(new ZoopZoopLeft());
		addSequential(new ClearEncoders());
		// Put code to raise lift to switch height here
		addSequential(new ClearEncoders());
		addSequential(new DistDrive41());
		addSequential(new ClearEncoders());
		// Put code here to push cube onto switch here
		addSequential(new DistDriveReverse41());
		addSequential(new ClearEncoders());
		addSequential(new ZoopZoopRight());
		addSequential(new ClearEncoders());
		addSequential(new DistDrive100());
	}
}
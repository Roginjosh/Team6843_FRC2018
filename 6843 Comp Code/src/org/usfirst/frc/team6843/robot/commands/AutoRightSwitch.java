package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightSwitch extends CommandGroup {

	public  AutoRightSwitch() {
		addSequential(new ClearEncoders());
		addSequential(new DistDrive168());
		addSequential(new ClearEncoders());
		addSequential(new ZoopZoopLeft1());
		addSequential(new ClearEncoders());
		addSequential(new ClearEncoders());
		addSequential(new DistDrive41());
		addSequential(new ClearEncoders());
        addSequential(new SingleSolenoidOnOff());
		addSequential(new DistDriveReverse41());
		addSequential(new ClearEncoders());
		addSequential(new ZoopZoopRight());
		addSequential(new ClearEncoders());
		addSequential(new DistDrive100());
	}
}
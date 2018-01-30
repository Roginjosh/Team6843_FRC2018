package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftSwitch extends CommandGroup {

    public  AutoLeftSwitch() {
    	addSequential(new ClearEncoders());
    	addSequential(new DistDrive());
    	addSequential(new ClearEncoders());
    	addSequential(new DistDrive());
    	addSequential(new ClearEncoders());	
    	addSequential(new DistDrive());
    	addSequential(new ClearEncoders());
    	addSequential(new DistDrive());
    	addSequential(new ClearEncoders());
    	addSequential(new ZoopZoopLeft());
    	addSequential(new ClearEncoders());
    	addSequential(new DistDrive());
    	addSequential(new ClearEncoders());	
    	addSequential(new DistDriveReverse());
    	addSequential(new ClearEncoders());
    	addSequential(new ZoopZoopRight());
    	addSequential(new ClearEncoders());
    	addSequential(new DistDrive());
    	addSequential(new ClearEncoders());
    	addSequential(new DistDrive());
    	addSequential(new ClearEncoders());
    }
}
package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMiddleLSwitch extends CommandGroup {

    public AutoMiddleLSwitch() {
    	    addSequential(new ClearEncoders());
        addSequential(new DistDrive36());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopLeft1());
        addSequential(new ClearEncoders());
        addSequential(new DistDrive119()); 
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopRight());
        addSequential(new ClearEncoders());
        addSequential(new DistDrive115_5());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopRight());
        addSequential(new ClearEncoders());
        addSequential(new ClearEncoders());
        addSequential(new DistDrive20());
        addSequential(new ClearEncoders());
//      addSequential(new SingleSolenoidOnOff());
        addSequential(new ClearEncoders());
        addSequential(new DistDriveReverse41());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopLeft1());
        addSequential(new ClearEncoders());
        addSequential(new DistDrive100());
        addSequential(new ClearEncoders());
    }
}

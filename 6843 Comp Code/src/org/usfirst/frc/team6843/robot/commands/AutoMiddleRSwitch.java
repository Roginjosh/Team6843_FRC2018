package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMiddleRSwitch extends CommandGroup {

    public AutoMiddleRSwitch() {
        addSequential(new ClearEncoders());
        addSequential(new DistDrive36());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopRight());
        addSequential(new ClearEncoders());
        addSequential(new DistDrive100());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopLeft());
        addSequential(new ClearEncoders());
        addSequential(new DistDrive115_5());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopLeft());
        addSequential(new ClearEncoders());
        // Add code to raise lift for switch here
        addSequential(new ClearEncoders());
        addSequential(new DistDrive9());
        addSequential(new ClearEncoders());
        // Add code to push cube here
        addSequential(new ClearEncoders());
        addSequential(new DistDriveReverse41());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopRight());
        addSequential(new ClearEncoders());
        addSequential(new DistDrive100());
        addSequential(new ClearEncoders());
        
    }
}

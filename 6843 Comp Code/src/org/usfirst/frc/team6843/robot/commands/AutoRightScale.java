package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
        addSequential(new ClearEncoders());
        addSequential(new DistDrive323());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopLeft());
        addSequential(new ClearEncoders());
        // Raise lift for scale
        addSequential(new ClearEncoders());
        addSequential(new DistDrive27());
        addSequential(new ClearEncoders());
        // Push cube onto scale
        addSequential(new ClearEncoders());
        addSequential(new DistDriveReverse27());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopRight());
        addSequential(new ClearEncoders());
    }
}

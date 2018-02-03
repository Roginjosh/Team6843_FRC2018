package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
        addSequential(new ClearEncoders());
        addParallel(new DistDrive323());
        addParallel(new LiftGoToScale());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopLeft1());
        addSequential(new ClearEncoders());
        addSequential(new ClearEncoders());
        addSequential(new DistDrive27());
        addSequential(new ClearEncoders());
        addSequential(new SingleSolenoidOnOff());
        addSequential(new ClearEncoders());
        addSequential(new DistDriveReverse27());
        addSequential(new ClearEncoders());
        addSequential(new ZoopZoopRight());
        addSequential(new ClearEncoders());
    }
}

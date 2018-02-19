package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightScale extends CommandGroup {

    public AutoRightScale() {
        addSequential(new ClearTelemetry());
        addSequential(new InchesDriving(305));
        //addSequential(new LiftGoToScale());
        addSequential(new ClearTelemetry());
        addSequential(new SlowLeftTurn());
        addSequential(new ClearTelemetry());
        addSequential(new ClearTelemetry());
        addSequential(new InchesDriving(0));
        addSequential(new ClearTelemetry());
        addSequential(new EjectCube());
        addSequential(new ClearTelemetry());
        addSequential(new DistDriveReverse27());
        addSequential(new ClearTelemetry());
        addSequential(new SlowRightTurn());
        addSequential(new ClearTelemetry());
    }
}

package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMiddleLSwitch extends CommandGroup {

    public AutoMiddleLSwitch() {
    	    addSequential(new ClearTelemetry());
        addSequential(new InchesDriving(30));
        addSequential(new Wait());
        addSequential(new ClearTelemetry());
        addSequential(new SlowLeftTurn());
        addSequential(new Wait());
        addSequential(new ClearTelemetry());
        addSequential(new InchesDriving(40.0)); 
        addSequential(new Wait());
        addSequential(new ClearTelemetry());
        addSequential(new SlowRightTurn());
        addSequential(new Wait());
        addSequential(new ClearTelemetry());
        addSequential(new InchesDriving(55));
        addSequential(new ClearTelemetry());
        addSequential(new EjectCube());
        addSequential(new ClearTelemetry());
    }
}

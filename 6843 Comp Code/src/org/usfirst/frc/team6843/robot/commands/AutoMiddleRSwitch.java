package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMiddleRSwitch extends CommandGroup {

    public AutoMiddleRSwitch() {
       /* addSequential(new ClearTelemetry());
        addSequential(new DistDrive36());
        addSequential(new ClearTelemetry());
        addSequential(new SlowRightTurn());
        addSequential(new ClearTelemetry());
        addSequential(new DistDrive100());
        addSequential(new ClearTelemetry());
        addSequential(new SlowLeftTurn());
        addSequential(new ClearTelemetry());
        addSequential(new DistDrive115_5());
        addSequential(new ClearTelemetry());
        addSequential(new SlowLeftTurn());
        addSequential(new ClearTelemetry());
        addSequential(new ClearTelemetry());
        addSequential(new DistDrive9());
        addSequential(new ClearTelemetry());
        addSequential(new EjectCube());
        addSequential(new ClearTelemetry());
        addSequential(new DistDriveReverse41());
        addSequential(new ClearTelemetry());
        addSequential(new SlowRightTurn());
        addSequential(new ClearTelemetry());
        addSequential(new DistDrive100());
        addSequential(new ClearTelemetry());*/
    	
    	  addSequential(new ClearTelemetry());
          addSequential(new InchesDriving(30));
          addSequential(new Wait());
          addSequential(new ClearTelemetry());
          addSequential(new SlowRightTurn());
          addSequential(new Wait());
          addSequential(new ClearTelemetry());
          addSequential(new InchesDriving(40.0)); 
          addSequential(new Wait());
          addSequential(new ClearTelemetry());
          addSequential(new SlowLeftTurn());
          addSequential(new Wait());
          addSequential(new ClearTelemetry());
          addSequential(new InchesDriving(55));
          addSequential(new ClearTelemetry());
          addSequential(new EjectCube());
          addSequential(new ClearTelemetry());
        
    }
}

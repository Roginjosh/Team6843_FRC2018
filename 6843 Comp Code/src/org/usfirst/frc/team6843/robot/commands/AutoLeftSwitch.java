package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoLeftSwitch extends CommandGroup {

    	public  AutoLeftSwitch() {
    		addSequential(new ClearTelemetry());
    		addSequential(new InchesDriving(144.0));
    		addSequential(new ClearTelemetry());
    		addSequential(new SlowRightTurn());
    		addSequential(new ClearTelemetry());
    		addSequential(new ClearTelemetry());
    		addSequential(new Wait());
    		addSequential(new InchesDriving(16));
    		addSequential(new Wait());
    		addSequential(new ClearTelemetry());
     	addSequential(new EjectCube());
    		addSequential(new DistDriveReverse41());
    		addSequential(new ClearTelemetry());
    		addSequential(new SlowLeftTurn());
    		addSequential(new ClearTelemetry());
    		addSequential(new InchesDriving(100));
    		addSequential(new WaitCommand(1));
    	}
}
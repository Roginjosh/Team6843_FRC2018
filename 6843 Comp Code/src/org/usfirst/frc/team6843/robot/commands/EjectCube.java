package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class EjectCube extends CommandGroup {

    public EjectCube() {
    addSequential(new ToggleCubeSolenoid());
    addSequential(new Wait());
    addSequential(new ToggleCubeSolenoid());
    }
}

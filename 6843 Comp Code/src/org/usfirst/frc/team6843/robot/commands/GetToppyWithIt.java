package org.usfirst.frc.team6843.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GetToppyWithIt extends CommandGroup {

    public GetToppyWithIt() {
       addSequential(new LiftGoToTop());
    }
}

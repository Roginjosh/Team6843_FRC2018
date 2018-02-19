/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6843.robot;

import org.usfirst.frc.team6843.robot.commands.AutoMiddleLSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoMiddleRSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoRightScale;
import org.usfirst.frc.team6843.robot.commands.ClearTelemetry;
import org.usfirst.frc.team6843.robot.commands.EjectCube;
import org.usfirst.frc.team6843.robot.commands.LiftGoToBottom;
import org.usfirst.frc.team6843.robot.commands.LiftGoToScale;
import org.usfirst.frc.team6843.robot.commands.LiftGoToTop;
import org.usfirst.frc.team6843.robot.commands.ManualOverride;
import org.usfirst.frc.team6843.robot.commands.SlowLeftTurn;
import org.usfirst.frc.team6843.robot.commands.SlowRightTurn;
import org.usfirst.frc.team6843.robot.commands.ThrottledDrive;
import org.usfirst.frc.team6843.robot.commands.ToggleJaws;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick driveGamepad = new Joystick(0);
	public Joystick buttonMonkeyGamepad = new Joystick(1);
	
	public Button driveButtonA = new JoystickButton(driveGamepad, 1),
	  driveButtonB = new JoystickButton(driveGamepad, 2),
	  driveButtonX = new JoystickButton(driveGamepad, 3),
	  driveButtonY = new JoystickButton(driveGamepad, 4),
	  driveButtonLB = new JoystickButton(driveGamepad, 5),
	  driveButtonRB = new JoystickButton(driveGamepad, 6),
	  driveButtonBack = new JoystickButton(driveGamepad, 7),
	  driveButtonStart = new JoystickButton(driveGamepad, 8),
	  driveButtonLJoyClick = new JoystickButton(driveGamepad, 9),
	  driveButtonRJoyClick = new JoystickButton(driveGamepad, 10),
	  driveButton11 = new JoystickButton(driveGamepad, 11),
	  driveButton12 = new JoystickButton(driveGamepad, 12),
	  bMButtonA = new JoystickButton(buttonMonkeyGamepad, 1),
	  bMButtonB = new JoystickButton(buttonMonkeyGamepad, 2),
	  bMButtonX = new JoystickButton(buttonMonkeyGamepad, 3),
	  bMButtonY = new JoystickButton(buttonMonkeyGamepad, 4),
	  bMButtonLB = new JoystickButton(buttonMonkeyGamepad, 5),
	  bMButtonRB = new JoystickButton(buttonMonkeyGamepad, 6),
	  bMButtonBack = new JoystickButton(buttonMonkeyGamepad, 7),
	  bMButtonStart = new JoystickButton(buttonMonkeyGamepad, 8),
	  bMButtonLJoyClick = new JoystickButton(buttonMonkeyGamepad, 9),
	  bMButtonRJoyClick = new JoystickButton(buttonMonkeyGamepad, 10),
	  bMButton11 = new JoystickButton(buttonMonkeyGamepad, 11),
	  bMButton12 = new JoystickButton(buttonMonkeyGamepad, 12);
																							  

	
	
	public OI() {
		driveButtonA.toggleWhenPressed(new ThrottledDrive());
		driveButtonRB.whenPressed(new ClearTelemetry());
		bMButtonA.whenPressed(new LiftGoToBottom());
		bMButtonB.whenPressed(new LiftGoToScale());
		bMButtonY.whenPressed(new LiftGoToTop());
		bMButtonLB.whenPressed(new ToggleJaws());
		bMButtonRB.whenPressed(new EjectCube());
		bMButtonLJoyClick.toggleWhenPressed(new ManualOverride());
	}
	
	public double getRightTriggerAxis() {
		return driveGamepad.getRawAxis(3);
	}
	public double getVertAxis() {
		return driveGamepad.getRawAxis(1);
	}
	
	public double getHorizAxis() {
		return driveGamepad.getRawAxis(4);
	}

	public double getTarget() {
		return getVertAxis() * 1000;
	}
	public double getBMGamepadAxis5() {
		return buttonMonkeyGamepad.getRawAxis(5);
	}
	}

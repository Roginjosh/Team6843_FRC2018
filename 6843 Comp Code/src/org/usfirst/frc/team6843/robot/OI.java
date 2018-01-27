/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6843.robot;

import org.usfirst.frc.team6843.robot.commands.ClearEncoders;
import org.usfirst.frc.team6843.robot.commands.DistDrive;
import org.usfirst.frc.team6843.robot.commands.ExampleCommand;
import org.usfirst.frc.team6843.robot.commands.RightTurn;
import org.usfirst.frc.team6843.robot.commands.RightTurnn;
import org.usfirst.frc.team6843.robot.commands.ZoopZoopLeft;
import org.usfirst.frc.team6843.robot.commands.ZoopZoopRight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick gamepad = new Joystick(0);
	public Button buttonA = new JoystickButton(gamepad, 1);
	public Button buttonB = new JoystickButton(gamepad, 2);
	public Button buttonX = new JoystickButton(gamepad, 3);
	public Button buttonY = new JoystickButton(gamepad, 4);
	public Button buttonLB = new JoystickButton(gamepad, 5);
	public Button buttonRB = new JoystickButton(gamepad, 6);
	public Button buttonBack = new JoystickButton(gamepad, 7);
	public Button buttonStart = new JoystickButton(gamepad, 8);
	public Button buttonLJoyClick = new JoystickButton(gamepad, 9);
	public Button buttonRJoyClick = new JoystickButton(gamepad, 10);
	public Button button11 = new JoystickButton(gamepad, 11);
	public Button button12 = new JoystickButton(gamepad, 12);
	
	public OI() {
		buttonLB.whileHeld(new ExampleCommand());
		buttonRB.whenPressed(new DistDrive());
		buttonA.whenPressed(new ClearEncoders());
		buttonY.whenPressed(new RightTurn());
		buttonB.whenPressed(new RightTurnn());
		buttonStart.whenPressed(new ZoopZoopRight());
		buttonBack.whenPressed(new ZoopZoopLeft());
	}
	
	public double getVertAxis() {
		return gamepad.getRawAxis(1);
	}
	
	public double getHorizAxis() {
		return gamepad.getRawAxis(4);
	}

	public double getTarget() {
		return getVertAxis() * 1000;
	}
	}

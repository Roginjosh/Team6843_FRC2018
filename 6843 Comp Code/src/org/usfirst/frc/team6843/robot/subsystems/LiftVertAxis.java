/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6843.robot.subsystems;

import org.usfirst.frc.team6843.robot.AutoParameters;
import org.usfirst.frc.team6843.robot.DifferentDrive;
import org.usfirst.frc.team6843.robot.OI;
import org.usfirst.frc.team6843.robot.RobotMap;
import org.usfirst.frc.team6843.robot.commands.JoystickTankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class LiftVertAxis extends Subsystem {
	public WPI_TalonSRX platformMotor = new WPI_TalonSRX(RobotMap.PLATFORM_MOTOR);
	public Talon climbMotor = new Talon(RobotMap.CLIMB_MOTOR);
	double holdPosition;
	
	public LiftVertAxis() {
		
		platformMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
		platformMotor.setSensorPhase(true);
		// set the peak, nominal outputs, and deadband 
		platformMotor.configNominalOutputForward(0, 100); 
		platformMotor.configNominalOutputReverse(0, 100); 
		platformMotor.configPeakOutputForward(1, 100); 
		platformMotor.configPeakOutputReverse(-1, 100);
		// set closed loop gains in slot0 
		platformMotor.config_kF(0, 0, 100); 
		platformMotor.config_kP(0, 0.2, 100); 
		platformMotor.config_kI(0, 0.0, 100);
		platformMotor.config_kD(0, 0, 100);
		platformMotor.config_IntegralZone(0, 20, 100);
		
		platformMotor.setNeutralMode(NeutralMode.Brake);
	}
	
	
	/*public void initDefaultCommand() {
		setDefaultCommand(new JoystickTankDrive());
	}*/
	
	public double getLiftEncoder() {
		return platformMotor.getSelectedSensorPosition(0);
	}
	
	public boolean rightMotor1ForwardLimit() {
		return platformMotor.getSensorCollection().isFwdLimitSwitchClosed();

	}	
	
	public void manualOverride(double power) {
		platformMotor.set(ControlMode.PercentOutput, power);
	}
	
	public boolean rightMotor1ReverseLimit() {
		return platformMotor.getSensorCollection().isRevLimitSwitchClosed();
	}

	public void goToHeight(double height) {
		platformMotor.set(ControlMode.Position, height);
	}
	
	public void liftDrive(double power) {
		platformMotor.set(ControlMode.PercentOutput, power);
	}
	
	public void holdPosition() {
		holdPosition = platformMotor.getSelectedSensorPosition(0);
		platformMotor.set(ControlMode.Position, holdPosition);
	}
	
	
	public void stop() {
		//drive.arcadeDrive(0.0, 0.0);
	//	platformMotor.set(ControlMode.Velocity, 0);		
	}

	/*public void climbMotorDrive(double power) {
		climbMotor.set(ControlMode.PercentOutput, power * .25);
	}*/
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

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

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
	//public OI oi;
	public final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_1);
	//private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_2);
	public final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_1);
	//private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_2);
	private final DifferentialDrive drive = new DifferentialDrive(leftMotor1, rightMotor1);
	double kP = 0;
	double kI = 0;
	double kD = 0;
	double kF = 1.0;
	public double dIN = 0;
	public double dOUT = 0;
	public double vOUT = 0;
	public double vIN = 0;
	boolean dimeMode = false;
	
//	leftEncoderVelocity 1080
//	rightEncoderVelocity 1080
	
	public DriveSubsystem() {
		
		rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
		rightMotor1.setSensorPhase(true);
		// set the peak, nominal outputs, and deadband 
		rightMotor1.configNominalOutputForward(0, 100); 
		rightMotor1.configNominalOutputReverse(0, 100); 
		rightMotor1.configPeakOutputForward(1, 100); 
		rightMotor1.configPeakOutputReverse(-1, 100);
		// set closed loop gains in slot0 
		rightMotor1.config_kF(0, 1, 100); 
		rightMotor1.config_kP(0, 0.55, 100); 
		rightMotor1.config_kI(0, 0.0055, 100);
		rightMotor1.config_kD(0, 0, 100);
		rightMotor1.config_IntegralZone(0, 20, 100);
		
		
		leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
		leftMotor1.setSensorPhase(true);
		// set the peak, nominal outputs, and deadband 
		leftMotor1.configNominalOutputForward(0, 100); 
		leftMotor1.configNominalOutputReverse(0, 100); 
		leftMotor1.configPeakOutputForward(1, 100); 
		leftMotor1.configPeakOutputReverse(-1, 100);
		// set closed loop gains in slot0 
		leftMotor1.config_kF(0, 0.95, 100); 
		leftMotor1.config_kP(0, 0.55, 100); 
		leftMotor1.config_kI(0, 0.0055, 100);
		leftMotor1.config_kD(0, 0, 100);
		leftMotor1.config_IntegralZone(0, 20, 100);
		
		leftMotor1.setNeutralMode(NeutralMode.Brake);
		rightMotor1.setNeutralMode(NeutralMode.Brake);
		//leftMotor1.set(ControlMode.PercentOutput, 0.0);
	//	leftMotor2.set(ControlMode.Follower, RobotMap.LEFT_MOTOR_1);
		//rightMotor1.set(ControlMode.PercentOutput, 0.0);
	//	rightMotor2.set(ControlMode.Follower, RobotMap.RIGHT_MOTOR_1);
	}
	
	//public double target() {
	//	return 1000 * this.oi.getVertAxis();
	//}
	public double getLeftPosition() {
		double leftRawPos = leftMotor1.getSelectedSensorPosition(0);
		double leftUnitPos = leftRawPos / 1440;
		double leftInchPos = leftUnitPos * 18.85;
		return leftInchPos;
	}
	public void AutoDerive(double power, double distance) {
		if (distance == 0) {
			
		} else { 
			leftMotor1.set(ControlMode.Velocity, power);
			rightMotor1.set(ControlMode.Velocity, power);
			
		}
	}
	
	public AutoParameters autoTurnValue(double angle, double radius, double vIN) {
		dIN = (angle/360) * 2 * Math.PI * radius;
		dOUT = (angle/360) * 2 * Math.PI * (radius + 24);
		vOUT = vIN * (dOUT / dIN);
		AutoParameters returnVal = new AutoParameters(dIN, dOUT, vOUT, vIN);
		return returnVal;
	}		
	
	public void TalonVeloDrive(double power, double curve) {
	/*	if ((Math.round(magn * 10)) <= 1) {
			dimeMode = true; 
		} else {
			dimeMode = false;
		}
		if (dimeMode) {
			rightMotor1.set(ControlMode.Velocity, (1000 * curve));
			leftMotor1.set(ControlMode.Velocity, (1000 * curve));
		} else if (((Math.round(curve * 10)) <= 1) && ((Math.round(magn * 10)) >= -1)) {
			rightMotor1.set(ControlMode.Velocity, (1000 * magn));
			leftMotor1.set(ControlMode.Velocity, (-1000 * magn));
		} else if (Math.round(curve * 100) < 0) {
			rightMotor1.set(ControlMode.Velocity, (1000 * magn));
			leftMotor1.set(ControlMode.Velocity, 1000 * -(Math.abs(magn) - (Math.abs(magn) * curve)));
		} else if (Math.round(curve * 100) > 0) {
			leftMotor1.set(ControlMode.Velocity, 1000 * magn);
			rightMotor1.set(ControlMode.Velocity, 1000 * -(Math.abs(magn) - (Math.abs(magn) * curve)));
			}*/
		if ((Math.round(Math.abs(power) * 10) <= 1 && (Math.round(10 * curve) != 0))) {
			rightMotor1.set(ControlMode.Velocity, (1000 * curve));
			leftMotor1.set(ControlMode.Velocity, (1000 * curve));
		} else if (Math.round((10 * curve)) == 0) {
			leftMotor1.set(ControlMode.Velocity, (-1000 * power));
			rightMotor1.set(ControlMode.Velocity, (1000 * power));
		} else if ((Math.round(((10 * curve))) > 0)) {
			leftMotor1.set(ControlMode.Velocity, (-1000 * power));
			rightMotor1.set(ControlMode.Velocity, (1000 * (power * (1 - curve))));
		} else if ((Math.round((10 * curve)) < 0)) {
			rightMotor1.set(ControlMode.Velocity, (1000 * power));
			leftMotor1.set(ControlMode.Velocity, (-1000 * (power * (1 + curve))));
		}
		
		
	}
	
	public  void ClearEncoders() {
		rightMotor1.setSelectedSensorPosition(0, 0, 100);
		leftMotor1.setSelectedSensorPosition(0, 0, 100);

	}
	public double getRightPosition() {
		double rightRawPos = -rightMotor1.getSelectedSensorPosition(0);
		double rightUnitPos = rightRawPos / 1440;
		double rightInchPos = rightUnitPos * 18.85;
		return rightInchPos;
	}
	
	public double getLeftVelocity() {
		return leftMotor1.getSelectedSensorVelocity(0);
	}
	public double getRightVelocity() {
		return rightMotor1.getSelectedSensorVelocity(0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickTankDrive());
	}

	public void encoderTest(double leftPower, double rightPower) {
		leftMotor1.set(ControlMode.Velocity, leftPower);
		rightMotor1.set(ControlMode.Velocity, rightPower);
		}
	
	public void arcadeDrive(double power, double curve) {
		drive.arcadeDrive((-1 * power), (1 * curve));
	}

	public void stop() {
		//drive.arcadeDrive(0.0, 0.0);
		leftMotor1.set(ControlMode.Velocity, 0);
		rightMotor1.set(ControlMode.Velocity, 0);
		
	}
}

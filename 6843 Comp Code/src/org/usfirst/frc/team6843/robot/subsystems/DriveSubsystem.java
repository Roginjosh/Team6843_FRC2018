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

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
	//public OI oi;
	private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_1);
	//private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_2);
	public final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_1);
	//private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_2);
	//private final DifferentialDrive drive = new DifferentialDrive(leftMotor1, rightMotor1);
	private Gyro gyro = new AnalogGyro(0);
	double holdPosition;
	public boolean manualOverride = false;
	
//	leftEncoderVelocity 1080
//	rightEncoderVelocity 1080
	
	public DriveSubsystem() {
		rightMotor1.configOpenloopRamp(0, 0);
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
		
		// set the peak, nominal outputs, and deadband 
		// set closed loop gains in slot0 
		rightMotor1.config_kF(1, 0, 100); 
		rightMotor1.config_kP(1, 0.2, 100); 
		rightMotor1.config_kI(1, 0.0, 100);
		rightMotor1.config_kD(1, 0, 100);
		rightMotor1.config_IntegralZone(1, 10, 100);
		
		leftMotor1.configOpenloopRamp(0, 0);
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
		
		leftMotor1.config_kF(1, 0, 100); 
		leftMotor1.config_kP(1, 0.2, 100); 
		leftMotor1.config_kI(1, 0.0, 100);
		leftMotor1.config_kD(1, 0, 100);
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
	public boolean rightMotor1ForwardLimit() {
		return rightMotor1.getSensorCollection().isFwdLimitSwitchClosed();

	}	
	public boolean rightMotor1ReverseLimit() {
		return rightMotor1.getSensorCollection().isRevLimitSwitchClosed();

	}
	
	public double getRawLeftEncoderCounts() {
		return leftMotor1.getSelectedSensorPosition(0);
	}
	public double getRawRightEncoderCounts() {
		return rightMotor1.getSelectedSensorPosition(0);
	}
	public String getLeftControlMode() {
		return leftMotor1.getControlMode().name();
	}
	public String getRightControlMode() {
		return rightMotor1.getControlMode().name();
	}
	public double getAngle() {
		return gyro.getAngle();
	}
	
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
	
	public void gyroStraightAssist(double power) {
		double leftPower = -power;
		double rightPower = power;
		if (gyro.getAngle() > 0){
		leftMotor1.set(ControlMode.Velocity, leftPower * (1 - ( gyro.getAngle()/100)));
		rightMotor1.set(ControlMode.Velocity, rightPower);
			
		} else if (gyro.getAngle() < 0){
			leftMotor1.set(ControlMode.Velocity, leftPower);
			rightMotor1.set(ControlMode.Velocity, rightPower * (1 + ( gyro.getAngle()/100)));
				
		} else {
			leftMotor1.set(ControlMode.Velocity, leftPower);
			rightMotor1.set(ControlMode.Velocity, rightPower);
		}
		
		
	}
	
	
	public void TalonVeloDrive(double power, double curve) {

		if ((Math.round(Math.abs(power) * 10) <= 1 && (Math.round(10 * curve) != 0))) {
			rightMotor1.set(ControlMode.PercentOutput, (1 * curve));
			leftMotor1.set(ControlMode.PercentOutput, (1 * curve));
		} else if (Math.round((10 * curve)) == 0) {
			leftMotor1.set(ControlMode.PercentOutput, (-1 * power));
			rightMotor1.set(ControlMode.PercentOutput, (1 * power));
		} else if ((Math.round(((10 * curve))) > 0)) {
			leftMotor1.set(ControlMode.PercentOutput, (-1 * power));
			rightMotor1.set(ControlMode.PercentOutput, (1 * (power * (1 - (1 * curve)))));
		} else if ((Math.round((10 * curve)) < 0)) {
			rightMotor1.set(ControlMode.PercentOutput, (1 * power));
			leftMotor1.set(ControlMode.PercentOutput, (-1 * (power * (1 + (1 * curve)))));
		} else {
			rightMotor1.set(ControlMode.PercentOutput, 0);
			leftMotor1.set(ControlMode.PercentOutput, 0);
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
	
	/*public void arcadeDrive(double power, double curve) {
		drive.arcadeDrive((-1 * power), (1 * curve));
	}
*/
	public void dDriveTest(double leftPos, double rightPos) {
		rightMotor1.selectProfileSlot(1, 0);
		leftMotor1.selectProfileSlot(1, 0);
		leftMotor1.set(ControlMode.Position, leftPos);
		rightMotor1.set(ControlMode.Position, rightPos);
		
	}
	
	public void ternMasheen(int negative) {
		leftMotor1.set(ControlMode.PercentOutput, negative * .3);
		rightMotor1.set(ControlMode.PercentOutput, negative * .3);
	}
	
	public void mannyOver(double power) {
		rightMotor1.set(ControlMode.PercentOutput, power);
	}
	public void strateMasheen(double left_power) {
        double angle = gyro.getAngle();
        double scale_left_for_right = -0.445 / 0.4; 
		double angle_scale=0.04;
		double right_power = left_power * scale_left_for_right;
		if (Math.abs(angle) > 0.3) {
			right_power = right_power + (-angle * angle_scale);
		}
	  
        //leftMotor1.set(ControlMode.PercentOutput, 0.4);		
		 // rightMotor1.set(ControlMode.PercentOutput, -.445); //Holy Imperator
		  //rightMotor1.set(ControlMode.PercentOutput, -.415);   //Celtic BroBot
	    leftMotor1.set(ControlMode.PercentOutput, left_power);
	    rightMotor1.set(ControlMode.PercentOutput, right_power);
	}
	
	public void notStrateMasheen() {
		  leftMotor1.set(ControlMode.PercentOutput, -.26);
		  rightMotor1.set(ControlMode.PercentOutput, .30);
	}
	
	public void goToHeight(double height) {
		rightMotor1.selectProfileSlot(1, 0);
		rightMotor1.set(ControlMode.Position, height);
	}
	
	public void holdPosition() {
		holdPosition = rightMotor1.getSelectedSensorPosition(0);
		rightMotor1.set(ControlMode.Position, holdPosition);
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	public void stop() {
		//drive.arcadeDrive(0.0, 0.0);
		leftMotor1.set(ControlMode.Velocity, 0);
		rightMotor1.set(ControlMode.Velocity, 0);
		
	}
}

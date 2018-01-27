package org.usfirst.frc.team6843.robot;

import java.text.DecimalFormat;

import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class TalonVeloDrive {
// DecimalFormat format = new DecimalFormat(#.00);
	DriveSubsystem driveSubsystem;
	boolean dimeMode = false;
	double power;
	double curve;
	//double tSpeedI;
	//double tSpeedO;
	
	public TalonVeloDrive(double power, double curve) {
	if ((Math.round(power * 10)) <= 1) {
		dimeMode = true; 
	} else {
		dimeMode = false;
	}
	if (dimeMode) {
		this.driveSubsystem.rightMotor1.set(ControlMode.Velocity, 1000 * curve);
		this.driveSubsystem.leftMotor1.set(ControlMode.Velocity, 1000 * curve);
	} else if (Math.round(curve * 100) == 0) {
		this.driveSubsystem.rightMotor1.set(ControlMode.Velocity, 1000 * power);
		this.driveSubsystem.leftMotor1.set(ControlMode.Velocity, 1000 * power);
	} else if (Math.round(curve * 100) != 0 && curve < 0) {
		this.driveSubsystem.rightMotor1.set(ControlMode.Velocity, 1000 * power);
		this.driveSubsystem.leftMotor1.set(ControlMode.Velocity, 1000 * (power - (power * curve)));
	} else if (Math.round(curve * 100) != 0 && curve > 0) {
		this.driveSubsystem.leftMotor1.set(ControlMode.Velocity, 1000 * power);
		this.driveSubsystem.rightMotor1.set(ControlMode.Velocity, 1000 * (power - (power * curve)));
		}
}
}

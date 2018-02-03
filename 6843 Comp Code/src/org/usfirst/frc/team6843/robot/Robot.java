/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6843.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;

import javax.swing.Spring;

import org.usfirst.frc.team6843.robot.commands.AutoLeftScale;
import org.usfirst.frc.team6843.robot.commands.AutoLeftSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoRightScale;
import org.usfirst.frc.team6843.robot.commands.AutoRightSwitch;
import org.usfirst.frc.team6843.robot.commands.ExampleCommand;
import org.usfirst.frc.team6843.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team6843.robot.subsystems.LiftVertAxis;
import org.usfirst.frc.team6843.robot.subsystems.PneumaticsBase;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	public Compressor Compressor = new Compressor(0);

	public Command autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
private static Robot INSTANCE;
private DriveSubsystem driveSubsystem;
private PneumaticsBase PneumaticsBase;
private LiftVertAxis LiftVertAxis;
private OI oi;
private Logger logger;
private String Version = "1.0.0";
//SendableChooser<Command> auto_chooser = new SendableChooser<>();


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	/*	m_oi = new OI();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		*/
		INSTANCE = this;
		this.logger = Logger.getLogger(this.getClass().getName());
		this.driveSubsystem = new DriveSubsystem();
		this.PneumaticsBase = new PneumaticsBase();
		this.oi = new OI();   //must be below subsystems!!!
		
		//auto_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", auto_chooser);
		
		
	}

	public static Robot getInstance() {
		if (INSTANCE == null) {
			throw new IllegalStateException("Robot.getInstance() was called before Robot.robotInit() was called.");
		}
		return INSTANCE;
	}
	
	public Logger getLogger() {
		if (this.logger == null) {
			throw new IllegalStateException("Robot.getLogger() was called before Robot.robotInit() was called.");
		}
		return this.logger;
	}
	
	public OI getOI() {
		if (this.oi == null) {
			throw new IllegalStateException("Robot.getOI() was called before Robot.robotInit() was called.");
		}
		return this.oi;
	}
	
	public DriveSubsystem getDriveSubsystem() {
		if (this.driveSubsystem == null) {
			throw new IllegalStateException(
					"Robot.getDriveSubsystem() was called before Robot.robotInit() was called.");
		}
		return this.driveSubsystem;
	}
	
	public PneumaticsBase getPneumaticsBase() {
		if (this.PneumaticsBase == null) {
			throw new IllegalStateException(
					"Robot.getPneumaticsBase() was called before Robot.robotInit() was called.");
		}
		return this.PneumaticsBase;
	}
	
	public LiftVertAxis getLiftVertAxis() {
		if (this.LiftVertAxis == null) {
			throw new IllegalStateException(
					"Robot.getLiftVertAxis() was called before Robot.robotInit() was called.");
		
		}
		return this.LiftVertAxis;
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example):
		/*
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
		*/
		String gameData; {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		boolean weLeft = false;
		if (weLeft) { 		 //If we are on the left

			
		if (gameData.charAt(1) == 'L') {
			autonomousCommand = new AutoLeftScale(); 
		}
		{
//Score on scale			autonomousCommand.start();
		} 
	}
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Right Encooder Velocity", this.driveSubsystem.getRightVelocity());
		SmartDashboard.putNumber("Right Encooder Position", /*this.driveSubsystem.getRightPosition()*/ this.driveSubsystem.rightMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Encooder Velocity", this.driveSubsystem.getLeftVelocity());
		SmartDashboard.putNumber("Left Encooder Position", /*this.driveSubsystem.getLeftPosition()*/ this.driveSubsystem.leftMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putString("Version", Version);
		SmartDashboard.putNumber("Left Target", this.oi.getTarget());
		SmartDashboard.putNumber("Left Error", this.driveSubsystem.leftMotor1.getClosedLoopError(0));
		SmartDashboard.putNumber("Right Target", this.oi.getTarget());
		SmartDashboard.putNumber("Right Error", this.driveSubsystem.rightMotor1.getClosedLoopError(0));
		SmartDashboard.putString("Control Mode", this.driveSubsystem.rightMotor1.getControlMode().name());
		SmartDashboard.putNumber("Left Vert Axis", this.oi.getVertAxis());
		SmartDashboard.putNumber("Right Horiz Axis", this.oi.getHorizAxis());
		//SmartDashboard.putNumber();
	}
	
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

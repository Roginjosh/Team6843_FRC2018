/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6843.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;

import javax.swing.Spring;

import org.usfirst.frc.team6843.robot.commands.AutoLeftScale;
import org.usfirst.frc.team6843.robot.commands.AutoLeftSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoMiddleLSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoMiddleRSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoRightScale;
import org.usfirst.frc.team6843.robot.commands.AutoRightSwitch;
import org.usfirst.frc.team6843.robot.commands.ExampleCommand;
import org.usfirst.frc.team6843.robot.commands.ZoopZoopLeft;
import org.usfirst.frc.team6843.robot.commands.ZoopZoopLeft1;
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
public class Robot extends IterativeRobot {
	public static OI m_oi;

	public Command autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
private static Robot INSTANCE;
private DriveSubsystem driveSubsystem;
//private PneumaticsBase PneumaticsBase;
private LiftVertAxis LiftVertAxis;
private OI oi;
private Logger logger;
private String Version = "1.1.1";
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
	//	this.PneumaticsBase = new PneumaticsBase();
		this.LiftVertAxis = new LiftVertAxis();

		//must be below subsystems!!!
		this.oi = new OI();  
		//MUST BE BELOW...DON'T FERGIT IT
	
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
	
/*	public PneumaticsBase getPneumaticsBase() {
		if (this.PneumaticsBase == null) {
			throw new IllegalStateException(
					"Robot.getPneumaticsBase() was called before Robot.robotInit() was called.");
		}
		return this.PneumaticsBase;
	}
*/	
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
		/*String gameData; {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		DigitalInput weMiddle = new DigitalInput(8);
		DigitalInput weRight = new DigitalInput(7);
		DigitalInput weLeft = new DigitalInput(9);
		
		 if (weLeft.get() == true) { 		 //If we are on the left

			
		    if (gameData.charAt(1) == 'L') {
			autonomousCommand = new AutoLeftScale(); 
		    }
		    else if (gameData.charAt(0) == 'L') {
			autonomousCommand = new AutoLeftSwitch();
		    }
		 }
		 if (weRight.get() == true) {
			
			if (gameData.charAt(1) == 'R') {
				autonomousCommand = new AutoRightScale();
			}
			else if (gameData.charAt(0) == 'R') {
				autonomousCommand = new AutoRightSwitch();
			}
		if (weMiddle.get() == true) {
			if (gameData.charAt(0) == 'R') {
				autonomousCommand = new AutoMiddleRSwitch();
			}
			else if (gameData.charAt(0) == 'L') {
				autonomousCommand = new AutoMiddleLSwitch();
			}
		}
		}
	*/ autonomousCommand = new AutoLeftScale(); // After fixing ZoopZoopLeft1, AutoRightSwitch(), and AutoLeftSwitch work.
		
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
		SmartDashboard.putNumber("Right Encooder Position", this.driveSubsystem.getRawRightEncoderCounts());
		SmartDashboard.putNumber("Left Encooder Velocity", this.driveSubsystem.getLeftVelocity());
		SmartDashboard.putNumber("Left Encooder Position", this.driveSubsystem.getRawLeftEncoderCounts());
		SmartDashboard.putString("Version", Version);
		SmartDashboard.putString("Left Control Mode", this.driveSubsystem.getLeftControlMode());
		SmartDashboard.putString("Right Control Mode", this.driveSubsystem.getRightControlMode());
		SmartDashboard.putNumber("Left Vert Axis", this.oi.getVertAxis());
		SmartDashboard.putNumber("Right Horiz Axis", this.oi.getHorizAxis());
		SmartDashboard.putNumber("Angle", this.driveSubsystem.getAngle());
	}
	
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

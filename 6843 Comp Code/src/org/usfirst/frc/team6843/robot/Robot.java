/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6843.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.logging.Logger;
import org.usfirst.frc.team6843.robot.commands.AutoLeftScale;
import org.usfirst.frc.team6843.robot.commands.AutoLeftSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoMiddleLSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoMiddleRSwitch;
import org.usfirst.frc.team6843.robot.commands.AutoRightScale;
import org.usfirst.frc.team6843.robot.commands.AutoRightSwitch;
import org.usfirst.frc.team6843.robot.commands.InchesDriving;
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
	public Command autonomousCommand;
	SendableChooser chooser;
	SendableChooser prefChooser;
private static Robot INSTANCE;
private DriveSubsystem driveSubsystem;
private PneumaticsBase PneumaticsBase;
private LiftVertAxis LiftVertAxis;
private OI oi;
private Logger logger;
private String Version = "1.1.1";  
String currentAuto = "Don't Know Yet";
String fieldPosition;
char left = 'L';
char mid = 'M';
char right = 'R';
String Scale = "Scale";
String Switch = "Switch";



	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		chooser = new SendableChooser();
		prefChooser = new SendableChooser();
		prefChooser.addDefault("Prefer Scale", Scale);
		prefChooser.addObject("Prefer Switch", Switch);
		chooser.addDefault("Left", left);
		chooser.addObject("Middle", mid);
		chooser.addObject("Right", right);
		SmartDashboard.putData("Robot Position", chooser);
		SmartDashboard.putData("Auto Goal Preference", prefChooser);
        CameraServer.getInstance().startAutomaticCapture(); //USB Camera

		INSTANCE = this;
		this.logger = Logger.getLogger(this.getClass().getName());
		this.driveSubsystem = new DriveSubsystem();
		this.PneumaticsBase = new PneumaticsBase();
	 	this.LiftVertAxis = new LiftVertAxis();

		
		this.oi = new OI(); //Make sure that this is the last thing within robotInit
		
		
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
		int Singularity = 0;
		// Singularity is used for our switch/case statement in autonomous
		//      It is four digits: ABCD
		//                         A ---- 1: Prefer Switch
		//                                2: Prefer Scale
		//                          B --- 0: Robot is at the left position
		//                                1: Robot is in the middle
		//                                2: Robot is at right position
		//                           C -- 0: Scale Color for my team on Left
		//                                1: Scale Color for my team on Right
		//                            D - 0: Switch Color for my team on Left
		//                                1: Switch Color for my team on Right

		char fieldPosition = (char) chooser.getSelected();
		String AutoPreference = (String) prefChooser.getSelected();
		String gameData; 
		{
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
	    if (gameData.charAt(0) == 'R') {
	    	Singularity = Singularity + 1;
	    }
	    if (gameData.charAt(1) == 'R') {
	    	Singularity = Singularity + 10;
	    }
	    if (fieldPosition == 'M') {
	    	Singularity = Singularity + 100;
	    }
	    if (fieldPosition == 'R') {
	    	Singularity = Singularity + 200;
	    }
	    if (AutoPreference == "Switch") {
	    	Singularity = Singularity + 1000;
	    }
	    if (AutoPreference == "Scale") {
	    	Singularity = Singularity + 2000;
	    }
	    

	    switch(Singularity) {
		case 1000:
			autonomousCommand = new AutoLeftSwitch();
			currentAuto = "Auto Left Switch";
			break;
		case 1001:
			autonomousCommand = new AutoLeftScale();
			currentAuto = "Auto Left Scale";
			break;
		case 1010:
			autonomousCommand = new AutoLeftSwitch();
			currentAuto = "Auto Left Switch";
			break;
		case 1011:
			autonomousCommand = new InchesDriving(200);
			currentAuto = "Go Straight Forward Only";
			break;
		case 1100:
			autonomousCommand = new AutoMiddleLSwitch();
			currentAuto = "Auto Middle Left Switch";
			break;
		case 1101:
			autonomousCommand = new AutoMiddleRSwitch();
			currentAuto = "Auto Middle Right Switch";
			break;
		case 1110:
			autonomousCommand = new AutoMiddleLSwitch();
			currentAuto = "Auto Middle Left Switch";
			break;
		case 1111:
			autonomousCommand = new AutoMiddleRSwitch();
			currentAuto = "Auto Middle Right Switch";
			break;
		case 1200:
			autonomousCommand = new InchesDriving(200);
			currentAuto = "Go Straight Forward Only";
			break;
		case 1201:
			autonomousCommand = new AutoRightSwitch();
			currentAuto = "Auto Right Switch";
			break;
		case 1210:
			autonomousCommand = new AutoRightScale();
			currentAuto = "Auto Right Scale";
			break;
		case 1211:
			autonomousCommand = new AutoRightSwitch();
			currentAuto = "Auto Right Switch";
			break;
		case 2000:
			autonomousCommand = new AutoLeftScale();
			currentAuto = "Auto Left Scale";
			break;
		case 2001:
			autonomousCommand = new AutoLeftScale();
			currentAuto = "Auto Left Scale";
			break;
		case 2010:
			autonomousCommand = new AutoLeftSwitch();
			currentAuto = "Auto Left Switch";
			break;
		case 2011:
			autonomousCommand = new InchesDriving(200);
			currentAuto = "Go Straight Forward Only";
			break;
		case 2100:
			autonomousCommand = new AutoMiddleLSwitch();
			currentAuto = "Auto Middle Left Switch";
			break;
		case 2101:
			autonomousCommand = new AutoMiddleRSwitch();
			currentAuto = "Auto Middle Right Switch";
			break;
		case 2110:
			autonomousCommand = new AutoMiddleLSwitch();
			currentAuto = "Auto Middle Left Switch";
			break;
		case 2111:
			autonomousCommand = new AutoMiddleRSwitch();
			currentAuto = "Auto Middle Right Switch";
			break;
		case 2200:
			autonomousCommand = new InchesDriving(200);
			currentAuto = "Go Straight Forward Only";
			break;
		case 2201:
			autonomousCommand = new AutoRightSwitch();
			currentAuto = "Auto Right Switch";
			break;
		case 2210:
			autonomousCommand = new AutoRightScale();
			currentAuto = "Auto Right Scale";
			break;
		case 2211:
			autonomousCommand = new AutoRightScale();
			currentAuto = "Auto Right Scale";
			break;
		
	    
	    }
	    	

		
		
	    /*
	    
		
		 if ((fieldPosition == 'L') && AutoPreference == "Scale") { 		 //If we are on the left

			
		    if (gameData.charAt(1) == 'L') {
			autonomousCommand = new AutoLeftScale();
			currentAuto = "Auto Left Scale";
		    }
		    else if (gameData.charAt(0) == 'L') {
			autonomousCommand = new AutoLeftSwitch();
			currentAuto = "Auto Left Switch";
		    } else {
		    	autonomousCommand = new InchesDriving(200);
		    	currentAuto = "Go Straight Forward Only";
		    }
		    	
		 } 
		 
		 if (fieldPosition == 'R') {
			
			if (gameData.charAt(1) == 'R') {
				autonomousCommand = new AutoRightScale();
				currentAuto = "Auto Right Scale";
			}
			else if (gameData.charAt(0) == 'R') {
				autonomousCommand = new AutoRightSwitch();
				currentAuto = "Auto Right Switch";

			} else {
		    	autonomousCommand = new InchesDriving(200);
		    	currentAuto = "Go Straight Forward Only";
		    }
			
		 }
		 
		if (fieldPosition == 'M') {
			if (gameData.charAt(0) == 'R') {
				autonomousCommand = new AutoMiddleRSwitch();
				currentAuto = "Auto Middle Right Switch";

			}
			else if (gameData.charAt(0) == 'L') {
				autonomousCommand = new AutoMiddleLSwitch();
				currentAuto = "Auto Middle Left Switch";

			} 
		}*/
		
			SmartDashboard.putString("Auto Mode Running Currently", currentAuto);
		
		 if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
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
		SmartDashboard.putBoolean("Right Motor Reverse Limit", this.driveSubsystem.rightMotor1ForwardLimit());
		SmartDashboard.putBoolean("Right Motor Forward Limit", this.driveSubsystem.rightMotor1ReverseLimit());
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
		SmartDashboard.putBoolean("Right Motor Reverse Limit", this.driveSubsystem.rightMotor1ForwardLimit());
		SmartDashboard.putBoolean("Right Motor Forward Limit", this.driveSubsystem.rightMotor1ReverseLimit());
		SmartDashboard.putNumber("Lift Position", this.LiftVertAxis.getLiftEncoder());
		SmartDashboard.putBoolean("Are the Jaws Open?", this.PneumaticsBase.JawsState());
		
	}
	
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

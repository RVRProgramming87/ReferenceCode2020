/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.subsystems.AutonomousSequential;
import frc.robot.subsystems.DistanceSensor;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Limelight;
import frc.robot.commands.autonomous.AutonomousDrive;

import java.util.concurrent.TimeUnit;

public class Robot extends TimedRobot {

Command autonomousCommand;

  /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
  public final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);



	// Subsystem variables

  //TODO: Add other subsystems when complete

	public static DriveBase driveBase = new DriveBase();

	public static DistanceSensor distanceSensor = new DistanceSensor();

	public static AutonomousDrive autonomousDrive = new AutonomousDrive();

	public static OI oi;

	private void WaitCommand(double i) {
	}

	private boolean m_LimelightHasValidTarget = false;
	private static double m_LimelightDriveCommand = 0.0;
	private static double m_LimelightSteerCommand = 0.0;

	/**
	 * 
	 * This function is run when the robot is first started up and should be
	 * 
	 * used for any initialization code.
	 * 
	 */

	@Override

	public void robotInit() {

		oi = new OI();

		autonomousCommand = new AutonomousSequential();
	}

	/**
	 * 
	 * This function is called every robot packet, no matter the mode. Use
	 * 
	 * this for items like diagnostics that you want ran during disabled,
	 * 
	 * autonomous, teleoperated and test.
	 *
	 * 
	 * 
	 * <p>
	 * This runs after the mode specific periodic functions, but before
	 * 
	 * LiveWindow and SmartDashboard integrated updating.
	 * 
	 */

	@Override

	public void robotPeriodic() {
		/**
		 * The method GetColor() returns a normalized color value from the sensor and
		 * can be useful if outputting the color to an RGB LED or similar. To read the
		 * raw color, use GetRawColor().
		 * 
		 * The color sensor works best when within a few inches from an object in well
		 * lit conditions (the built in LED is a big help here!). The farther an object
		 * is the more light from the surroundings will bleed into the measurements and
		 * make it difficult to accurately determine its color.
		 */
		Color detectedColor = m_colorSensor.getColor();

		/**
		 * The sensor returns a raw IR value of the infrared light detected.
		 */
		// double IR = m_colorSensor.getIR();

		/**
		 * Open Smart Dashboard or Shuffleboard to see the color detected by the sensor.
		 */
		SmartDashboard.putNumber("Red", detectedColor.red);
		SmartDashboard.putNumber("Green", detectedColor.green);
		SmartDashboard.putNumber("Blue", detectedColor.blue);
		// SmartDashboard.putNumber("IR", IR);

		/**
		 * In addition to RGB IR values, the color sensor can also return an infrared
		 * proximity value. The chip contains an IR led which will emit IR pulses and
		 * measure the intensity of the return. When an object is close the value of the
		 * proximity will be large (max 2047 with default settings) and will approach
		 * zero when the object is far away.
		 * 
		 * Proximity can be used to roughly approximate the distance of an object or
		 * provide a threshold for when an object is close enough to provide accurate
		 * color values.
		 */
		int proximity = m_colorSensor.getProximity();

		SmartDashboard.putNumber("Proximity", proximity);
	}

	/**
	 * 
	 * This function is called once each time the robot enters Disabled mode.
	 * 
	 * You can use it to reset any subsystem information you want to clear when
	 * 
	 * the robot is disabled.
	 * 
	 */

	@Override

	public void disabledInit() {

	}

	@Override

	public void disabledPeriodic() {

		Scheduler.getInstance().run();

	}

	/**
	 * 
	 * This autonomous (along with the chooser code above) shows how to select
	 * 
	 * between different autonomous modes using the dashboard. The sendable
	 * 
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * 
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * 
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * 
	 * 
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * 
	 * chooser code above (like the commented example) or additional comparisons
	 * 
	 * to the switch structure below with additional strings & commands.
	 * 
	 */

	@Override

	public void autonomousInit() {

	}

	/**
	 * 
	 * This function is called periodically during autonomous.
	 * 
	 */

	@Override


	
	public void autonomousPeriodic() {

		Scheduler.getInstance().run(); // needed to run autonomous code

		driveBase.alignRobot(true); // sets driveBase.drive to the limelight values during autonomous

		WaitCommand(2);

		driveBase.launcher(true);
		
		// code going in here will cross line, aim, and shoot 3 balls into target
		// TODO: Add To Autonomous Code

	}

	@Override

	public void teleopInit() {

		// This makes sure that the autonomous stops running when

		// teleop starts running. If you want the autonomous to

		// continue until interrupted by another command, remove

		// this line or comment it out.

		// if (driveCommand != null) {

		// driveCommand.cancel();

		// }
	}

	/**
	 * 
	 * This function is called periodically during operator control.
	 * 
	 */

	@Override

	public void teleopPeriodic() {

		Scheduler.getInstance().run(); // needed to start all code

		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		
		NetworkTableEntry tx = table.getEntry("tx");
		
		//reads values periodically
		double x = tx.getDouble(0.0);
		
		//post to SmartDashboard periodically
		SmartDashboard.putNumber("tx", x);
		
		DriveBase.drive(OI.drive(), OI.turn(), OI.switchButton()); // calls drive function from driveBase

		driveBase.intake(oi.intakeOn(), OI.reverseIntake()); // calls intake function from driveBasea

		driveBase.conveyor(oi.conveyorOn(), SmartDashboard.getNumber("Proximity", 0)); // calls conveyor function from driveBase

		driveBase.launcher(oi.launcherOn()); // calls launcher function from driveBase

		driveBase.color(OI.driveC());

		driveBase.lift(oi.liftOn());

		driveBase.alignRobot(OI.alignOn());

		SmartDashboard.putNumber("steeringAdjust", OI.steeringAdjust);
		
		SmartDashboard.putNumber("headingError", OI.headingError);

		SmartDashboard.putBoolean("Reversed?", OI.switchButton());

		SmartDashboard.putBoolean("Button Pressed", oi.intakeOn());

		SmartDashboard.putNumber("Switched Value", OI.switchedValue);
	}
	// TODO: Test Drive Systems

	/**
	 * 
	 * This function is called periodically during test mode.
	 * 
	 */

	@Override

	public void testPeriodic() {

	}

	/**
	 * This function implements a simple method of generating driving and steering
	 * commands based on the tracking data from a limelight camera.
	 */
	public void Update_Limelight_Tracking() // used to get info from the limelight
	{
		// These numbers must be tuned for your Robot! Be careful!
		final double STEER_K = 0.3; // how hard to turn toward the target
		final double DRIVE_K = 0.26; // how hard to drive fwd toward the target
		final double DESIRED_TARGET_AREA = 13.0; // Area of the target when the robot reaches the wall
		final double MAX_DRIVE = 0.7; // Simple speed limit so we don't drive too fast

		// gets different variables from limelight (see limelight documentation)
		double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
		double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
		double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
		double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

		if (tv < 1.0) {
			m_LimelightHasValidTarget = false;
			m_LimelightDriveCommand = 0.0;
			m_LimelightSteerCommand = 0.0;
			return;
		}

		m_LimelightHasValidTarget = true;

		// Start with proportional steering
		double steer_cmd = tx * STEER_K;
		m_LimelightSteerCommand = steer_cmd;

		// try to drive forward until the target area reaches our desired area
		double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

		// don't let the robot drive too fast into the goal
		if (drive_cmd > MAX_DRIVE) {
			drive_cmd = MAX_DRIVE;
		}
		m_LimelightDriveCommand = drive_cmd;

	}
}


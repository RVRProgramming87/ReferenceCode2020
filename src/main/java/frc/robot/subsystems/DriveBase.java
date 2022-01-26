/*----------------------------------------------------------------------------*/

/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/

//TODO: Change SRX to FX when motors come in

package frc.robot.subsystems;

import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.controls.DiabloTalonFX;
import frc.robot.controls.DiabloTalonSRX;

/**

 * Add your docs here.

 
*/
public class DriveBase extends Subsystem {


	public OI oi;

	private static DiabloTalonFX leftFrontMotor = new DiabloTalonFX(Constants.LEFTFRONTMOTOR);

	private static DiabloTalonFX leftRearMotor = new DiabloTalonFX(Constants.LEFTREARMOTOR);

	private static DiabloTalonFX rightFrontMotor = new DiabloTalonFX(Constants.RIGHTFRONTMOTOR);

	private static DiabloTalonFX rightRearMotor = new DiabloTalonFX(Constants.RIGHTREARMOTOR);

	private DiabloTalonSRX intakeMotor = new DiabloTalonSRX(Constants.INTAKEMOTOR);

	private DiabloTalonSRX conveyorMotor = new DiabloTalonSRX(Constants.CONVEYORMOTOR);

	private DiabloTalonSRX launcherRight = new DiabloTalonSRX(Constants.LAUNCHERRIGHT);

	private DiabloTalonSRX launcherLeft = new DiabloTalonSRX(Constants.LAUNCHERLEFT);

	private DiabloTalonSRX liftMotor = new DiabloTalonSRX(Constants.LIFTMOTOR);

	private DiabloTalonSRX colorMotor = new DiabloTalonSRX(Constants.COLORMOTOR);

	ArrayList<DiabloTalonSRX> motorList;
	ArrayList<DiabloTalonFX> driveMotors;

	private AHRS ahrs = new AHRS(SPI.Port.kMXP);

	// TODO: write equation for launcher

	public DriveBase() {

		motorList = new ArrayList<>();

		motorList.add(intakeMotor);

		motorList.add(conveyorMotor);

		motorList.add(launcherRight);

		motorList.add(launcherLeft);

		driveMotors = new ArrayList<>();

		driveMotors.add(leftFrontMotor);

		driveMotors.add(leftRearMotor);

		driveMotors.add(rightFrontMotor);

		driveMotors.add(rightRearMotor);
	}

	public void resetNavx() {

		ahrs.reset();

		ahrs.zeroYaw();

	}

	public static void drive(double rotate, double drive, boolean flipped) {

		if (flipped == false) {
		
		leftFrontMotor.set(drive - rotate);

		leftRearMotor.set(drive - rotate);

		rightFrontMotor.set(drive + rotate);

		rightRearMotor.set(drive + rotate);

		}

		else if (flipped == true){

		leftFrontMotor.set(drive + rotate);

		leftRearMotor.set(drive + rotate);

		rightFrontMotor.set(drive - rotate);

		rightRearMotor.set(drive - rotate);

		}

	}

	public void intake(boolean isPressed, boolean reversePressed) {

		if (isPressed == true) {

			intakeMotor.set(-.5);

		}

		else if (reversePressed == true) {

			intakeMotor.set(.5);

		}

		else{

			intakeMotor.set(0);
		}

	}

	public void conveyor(boolean buttonPressed, double inRange) {

		if (buttonPressed == true) {

			conveyorMotor.set(-.5);

		}
		if (inRange >= 2000) {

			conveyorMotor.set(-.7);

		} else {

			conveyorMotor.set(0);

		}
	}

	public void dashData() {

		for (int i = 0; i < motorList.size(); i++) {

			SmartDashboard.putNumber(motorList.get(i).getName(), motorList.get(i).get());

		}

	}

	@Override

	public void initDefaultCommand() {

		// setDefaultCommand(new ArcadeDrive());

	}

	public void launcher(boolean launcherButton) {

		if (launcherButton == true) {

			launcherLeft.set(-.7); //Limelight.launcherRPM()
			launcherRight.set(.7); //Limelight.launcherRPM()

		} else {

			launcherLeft.set(0);
			launcherRight.set(0);

		}
	}

	public void alignRobot(boolean alignButton) {

		if (alignButton) {

		drive(0, OI.autoAlign(), OI.switchButton());
	}

	else{
		drive(OI.drive(), OI.turn(), OI.switchButton());
	}
}


public void lift(boolean liftButton) {

	if (liftButton == true) {
		
		liftMotor.set(.4); //TBD

	} else {

		liftMotor.set(0);

	}
}

public void color(double driveC) {
	colorMotor.set(driveC);
}
}
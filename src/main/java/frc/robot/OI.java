package frc.robot;

import java.util.function.BiPredicate;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveBase;

import frc.robot.controls.*;
import frc.robot.Constants;



// import java.lang.Double;



/**

 * This class is the glue that binds the controls on the physical operator

 * interface to the commands and command groups that allow control of the robot.

 */

public class OI {

	BiPredicate<Double, Double> bi;


    public static enum DRIVEMETHOD{

        ARCADE,

        TANK

	};

	
	public static double accelCurveValue = 0.05;

	public static double accelCurveStart = 0.05;

	public static double accelCurveFactor = 40;



	public static double accelCurve(){

		while(accelCurveValue <= 0.4){

			accelCurveValue = accelCurveValue * accelCurveStart * accelCurveFactor;

		}

		return accelCurveValue;
		
	}

	DRIVEMETHOD driveMethod;



	static Joystick gamepad = new Joystick(Constants.GAMEPAD);

	static Joystick joystick = new Joystick(Constants.JOYSTICK);

	DriveBase driveBase = new DriveBase();

	public OI() {

	}

	/*
	 * 
	 * public double getLastVar(){
	 * 
	 * final double last;
	 * 
	 * while(joystick.getY() > 0.5){
	 * 
	 * last = joystick.getY();
	 * 
	 * }
	 * 
	 * return last;
	 * 
	 * }
	 * 
	 */
	public static double switchedValue = 0;

	public static double switchValue(){
		
		if (gamepad.getRawButtonPressed(Constants.SWITCHBUTTON)) {
			
			switchedValue = switchedValue++;

		} 
		return switchedValue;
	}

	public static boolean switchButton() {
		boolean switched = false;

		if	(switchValue() % 2 == 0){
			switched = false;
		}

		else if (switchValue() % 2 == 1) {
			switched = true;
		}
		return switched;
		}


	public static double drive() {	

			return gamepad.getRawAxis(Constants.GAMEPADDRIVE) * accelCurve();

	}

	public static double turn() {

		return gamepad.getRawAxis(Constants.GAMEPADTURN) * accelCurve();
	}

	public static double driveC() {
		return joystick.getRawAxis(Constants.COLORSLIDER);
	}

	public boolean liftOn() {
		return joystick.getRawButton(Constants.LIFTBUTTON);
	}

	public boolean intakeOn() {

		if (gamepad.getRawButton(Constants.INTAKEBUTTON)) {

			return true;

		}

		else {

			return false;

		}
	}

	public boolean conveyorOn() {

		if (gamepad.getRawButton(Constants.CONVEYORBUTTON)) {

			return true;

		} else {

			return false;

		}

	}

	// Return data on Talon output for diagnostics

	public void dashData() {

		driveBase.dashData();

	}

	public boolean launcherOn() {

		if (joystick.getRawButton(Constants.LAUNCHERBUTTON)) {

			return true;

		} else {

			return false;

		}

	}

	public static boolean reverseIntake() {

		if (gamepad.getRawButton(Constants.REVERSEINTAKEBUTTON)) {

			return true;

		}

		else {

			return false;

		}
	}

	public static DiabloTalonSRX leftFrontMotor = new DiabloTalonSRX(Constants.LEFTFRONTMOTOR);

	public static DiabloTalonSRX leftRearMotor = new DiabloTalonSRX(Constants.LEFTREARMOTOR);

	public static DiabloTalonSRX rightFrontMotor = new DiabloTalonSRX(Constants.RIGHTFRONTMOTOR);

	public static DiabloTalonSRX rightRearMotor = new DiabloTalonSRX(Constants.RIGHTREARMOTOR);

	public static int start = 0;

	/*public static void autoDrive() {
		while (start < 1) {

			leftFrontMotor.set((PathPlanner.veloLeft * PathPlanner.accelLeft) * 10);
			leftRearMotor.set((PathPlanner.veloLeft * PathPlanner.accelLeft) * 10);

			rightFrontMotor.set((PathPlanner.veloRight * PathPlanner.accelRight) * 10);
			rightRearMotor.set((PathPlanner.veloRight * PathPlanner.accelRight) * 10);

			start = start++;
		}
	}*/

	public static double kp = -0.03;

	public static double minCommand = 0.05;

	public static double headingError;

	public static double steeringAdjust;

	public static double angularVar;
	
	

	public static double autoAlign() {
		
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	double x = tx.getDouble(0.0);
		
        headingError = -x;
		steeringAdjust = 0;
		angularVar = 0;
		
		if(x > angularVar){

			steeringAdjust = kp*headingError - minCommand;

		}
		else if(x < angularVar){

			steeringAdjust = kp*headingError + minCommand;

		}

		double alignDrive = steeringAdjust;
		
		return alignDrive*.5;
}

public static boolean alignOn(){

	if(gamepad.getRawButton(Constants.AUTOALIGNBUTTON)){
		return true;
	}
	else{
		return false;
	}
}

}

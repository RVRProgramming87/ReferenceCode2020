package frc.robot.commands.autonomous;

import frc.robot.controls.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.*;
import frc.robot.subsystems.*;

public class AutonomousDrive extends CommandGroup{

	public static DiabloTalonSRX leftFrontMotor = new DiabloTalonSRX(Constants.LEFTFRONTMOTOR);

	public static DiabloTalonSRX leftRearMotor = new DiabloTalonSRX(Constants.LEFTREARMOTOR);

	public static DiabloTalonSRX rightFrontMotor = new DiabloTalonSRX(Constants.RIGHTFRONTMOTOR);

	public static DiabloTalonSRX rightRearMotor = new DiabloTalonSRX(Constants.RIGHTREARMOTOR);

    public static int start = 0;

    public static void autoDrive(){
        while(start < 1){
            leftFrontMotor.set((PathPlanner.veloLeft*PathPlanner.accelLeft)*10);
            leftRearMotor.set((PathPlanner.veloLeft*PathPlanner.accelLeft)*10);
    
            rightFrontMotor.set((PathPlanner.veloRight*PathPlanner.accelRight)*10);
            rightRearMotor.set((PathPlanner.veloRight*PathPlanner.accelRight)*10);

            start = start++;
        }
    }
}
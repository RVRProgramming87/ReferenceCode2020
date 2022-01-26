/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// Ignore dis...
	String[] tableNames = {"limelight", "raspberrypi"};

	NetworkTable table = NetworkTableInstance.getDefault().getTable(tableNames[1]);
	NetworkTableEntry tX = table.getEntry("tx");
	NetworkTableEntry tY = table.getEntry("ty");
	NetworkTableEntry tA = table.getEntry("ta");

	double x = tX.getDouble(0.0);
	double y = tX.getDouble(0.0);
	double area = tA.getDouble(0.0);

	public void putData(){
		SmartDashboard.putNumber("rPi X", x);
		SmartDashboard.putNumber("rPi Y", y);
		SmartDashboard.putNumber("rPi A", area);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}

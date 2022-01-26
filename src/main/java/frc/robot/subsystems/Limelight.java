package frc.robot.subsystems;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.lang.Math;

public class Limelight extends Subsystem{
    
    static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tv = table.getEntry("tv");
    /** Whether the limelight has any valid targets (0 or 1) */
    double v = tv.getDouble(0.0);

    NetworkTableEntry tx = table.getEntry("tx");
    /** Horizontal Offset From Crosshair To Target */
    double x = tx.getDouble(0.0);

    static NetworkTableEntry ty = table.getEntry("ty");
    /** Vertical Offset From Crosshair To Target */
    static double y = ty.getDouble(0.0);

    NetworkTableEntry ta = table.getEntry("ta");
    /** Target Area (0% of image to 100% of image) */
    double a = ta.getDouble(0.0);

    // distance from limelight directly to the target
    public static double limelightDistance() {
        double height = 30; // Height subject to change//
        double distance = (98.25 - height) / Math.tan(0 + y);
     return distance; 
    }
    public static double horizontalDistance(){
        double horizontal = Math.cos(Math.PI/4)*limelightDistance();
        return horizontal;
    }
    public static double verticalDistance(){
        double vertical = Math.sin(Math.PI/4)*limelightDistance();
        return vertical;
    }
    public static double shootingVelocity(){
        double velocity = Math.sqrt((Math.pow(horizontalDistance(), 2)*385.826771654)/(verticalDistance()*Math.sin(Math.PI/2)-2*verticalDistance()*Math.pow(Math.cos(Math.PI/4), 2)));
        return velocity;
    }
    public static double launcherRPM(){
        double rpm = shootingVelocity()/0.209433333333;
        return rpm;
     }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
}

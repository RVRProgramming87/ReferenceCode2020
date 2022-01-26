package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceSensor {
  // A MB1013 distance sensor -
  // http://www.maxbotix.com/documents/HRLV-MaxSonar-EZ_Datasheet.pdf
  // (pins 3, 6 and 7 from sensor to analog input 0)
  private static final AnalogInput mb1013 = new AnalogInput(0);

  // DONE - You will need to determine how to convert voltage to distance
  // (use information from the data sheet, or your own measurements)
  private static final double MetersPerVolt = 102;

  /** returns distance in voltage */
  public static double getVoltage() {
    return mb1013.getVoltage();
  }
  /** returns distance in meters */
  public static double getDistance() {
    return getVoltage() * MetersPerVolt;
  }
  
  public static void updateDashboard() {
    SmartDashboard.putNumber("Distance (volts)", getVoltage());
    SmartDashboard.putNumber("Distance (real)", getDistance());
  }

  public static boolean inRange(){
    if(getDistance() <= 40){
      return true;
    }
    else{
      return false;
    }
  }
}

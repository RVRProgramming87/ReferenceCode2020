package frc.robot.controls;



import edu.wpi.first.wpilibj.SpeedController;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/*

 *

 * 

 * @author Luke

 * 

 * 

*/

public class DiabloTalonSRX extends WPI_TalonSRX implements SpeedController{

    private ControlMode controlMode = ControlMode.PercentOutput;



    public DiabloTalonSRX(int deviceid){

        super(deviceid);

    }



    @Override

    public void set(double speed){

        this.set(controlMode, speed);

    }



    @Override

    public double get(){

        return this.getStatorCurrent();

    }



    @Override

    public void disable(){

        this.disable();

    }



    @Override

    public void stopMotor(){

        this.stopMotor();

    }



    @Override

    public void pidWrite(double output){

        this.set(output);

    }

}
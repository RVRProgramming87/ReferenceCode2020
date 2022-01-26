package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.AutonomousDrive;

public class AutonomousSequential extends CommandGroup{

    public AutonomousSequential(){
        addSequential(new AutonomousDrive());
    }
}
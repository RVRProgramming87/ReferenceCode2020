package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.commands.autonomous.AutonomousDrive;

public class AutonomousTest extends CommandGroup{

	public AutonomousTest(){
        addParallel(new AutonomousDrive());
    }
}
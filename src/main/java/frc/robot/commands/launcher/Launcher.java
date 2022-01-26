

package frc.robot.commands.launcher;



import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

import frc.robot.OI;


public class Launcher extends Command {

    private OI oi;



	public Launcher() {

		// Use requires() here to declare subsystem dependencies

		// eg. requires(chassis);

		requires(Robot.driveBase);

	}



	// Called just before this Command runs the first time

	@Override

	protected void initialize() {

		Robot.driveBase.launcher(oi.launcherOn());

	}



	// Called repeatedly when this Command is scheduled to run

	@Override

	protected void execute() {

		Robot.driveBase.launcher(oi.launcherOn());

	}



	// Make this return true when this Command no longer needs to run execute()

	@Override

	protected boolean isFinished() {

		return false;

	}



	// Called once after isFinished returns true

	@Override

	protected void end() {

	}



	// Called when another command which requires one or more of the same

	// subsystems is scheduled to run

	@Override

	protected void interrupted() {

	}

}
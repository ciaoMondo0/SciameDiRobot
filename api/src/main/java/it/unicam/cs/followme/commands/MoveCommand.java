package it.unicam.cs.followme.commands;


import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.space.Direction;

public class MoveCommand implements Command {

	public Direction dir;
	private double speed;

	public MoveCommand(Direction dir, double speed) {
		this.dir = dir;
		this.speed = speed;
	}

	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		this.dir = new Direction(dir.getX(), dir.getY());

		robot.move(speed, dir);
		System.out.println(robot + "move to " + dir + " at speed " + this.speed + " from " + robot.getPosition());
		programExecution.increment();
	}

	

}

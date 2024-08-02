package it.unicam.cs.followme.commands;

import java.util.Random;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;

public class MoveRandom implements Command {
	private Coordinates randomCoord1;
	private Coordinates randomCoord2;

	private double speed;

	public MoveRandom(Coordinates randomCoord1, Coordinates randomCoord2, double speed) {
		this.randomCoord1 = randomCoord1;
		this.randomCoord2 = randomCoord2;

		this.speed = speed;
	}

	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		Direction dir = Direction.calculateDirection(robot.getPosition(),
				Coordinates.getRandom(randomCoord1, randomCoord2));
		robot.move(speed, dir);
		programExecution.increment();
		System.out.println(robot + " is moving randomly to " + dir);

	}



}

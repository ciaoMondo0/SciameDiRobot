package it.unicam.cs.followme.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;

public class FollowCommand implements Command {

	private String label;
	private double distance;
	private double speed;
	private List<Robot> robots;
	private ProgramExecutor execution;

	public FollowCommand(String label, double distance, double speed) {
		this.label = label;
		this.distance = distance;
		this.speed = speed;
		this.robots = new ArrayList<>();
		this.execution = new ProgramExecutor();

	}

	public void execute(Robot robot, ProgramExecutor execution) {
		

		List<Robot> labelledRobots = this.robots.stream().filter(r -> r.getLabel().equals(label))
				.filter(r -> Coordinates.calculateDistance(robot.getPosition(), r.getPosition()) <= distance)
				.toList();
		if (!labelledRobots.isEmpty()) {
			Coordinates average = this.average(labelledRobots);

			Direction dir = Direction.calculateDirection(robot.getPosition(), average);
			robot.move(speed, dir);

		} else {
			this.moveRandom(robot);
		}
		System.out.println(robot + " is following " + label + " at speed " + speed);
		
		execution.increment();

	}

	private void moveRandom(Robot robot) {
		Coordinates x = new Coordinates(-this.distance, -this.distance);
		Coordinates y = new Coordinates(this.distance, this.distance);
		new MoveRandom(x, y, this.speed).execute(robot, execution);
	}

	private Coordinates average(List<Robot> robots) {
		OptionalDouble averageX = robots.stream().mapToDouble(r -> r.getPosition().getX()).average();
		OptionalDouble averageY = robots.stream().mapToDouble(r -> r.getPosition().getY()).average();
		double avgX = averageX.isPresent() ? averageX.getAsDouble() : 0;
		double avgY = averageY.isPresent() ? averageY.getAsDouble() : 0;
		return new Coordinates(avgX, avgY);

	}

	

}

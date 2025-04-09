package it.unicam.cs.followme.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;

/**
 * Comando che permette ad un robot di seguire altri robot identificati da un'etichetta.
 */
public class FollowCommand implements Command<Robot> {

	private final String label;
	private final double distance;
	private final double speed;
	private final List<Robot> robots = new ArrayList<>();

	/**
	 * Costruttore.
	 *
	 * @param label    l'etichetta da seguire
	 * @param distance la distanza massima per il riconoscimento
	 * @param speed    la velocità di movimento
	 */
	public FollowCommand(String label, double distance, double speed) {
		this.label = label;
		this.distance = distance;
		this.speed = speed;
	}

	@Override
	public void execute(Robot robot) {
		List<Robot> labelledRobots = this.robots.stream()
				.filter(r -> r.getLabel() != null && r.getLabel().equals(label))
				.filter(r -> Coordinates.calculateDistance(robot.getPosition(), r.getPosition()) <= distance)
				.toList();

		if (!labelledRobots.isEmpty()) {
			Coordinates average = average(labelledRobots);
			Direction dir = Direction.calculateDirection(robot.getPosition(), average);
			robot.move(speed, dir);
		} else {
			moveRandom(robot);
		}
		System.out.println(robot + " sta seguendo " + label + " a velocità " + speed);
		robot.getProgramExecutor().increment();
	}

	/**
	 * Calcola la posizione media dei robot in una lista.
	 *
	 * @param robots lista dei robot
	 * @return le coordinate medie
	 */
	private Coordinates average(List<Robot> robots) {
		OptionalDouble averageX = robots.stream().mapToDouble(r -> r.getPosition().getX()).average();
		OptionalDouble averageY = robots.stream().mapToDouble(r -> r.getPosition().getY()).average();
		double avgX = averageX.orElse(0);
		double avgY = averageY.orElse(0);
		return new Coordinates(avgX, avgY);
	}

	/**
	 * Muove il robot in modo casuale se non trova robot con la label specificata.
	 *
	 * @param robot il robot che esegue il comando
	 */
	private void moveRandom(Robot robot) {
		Coordinates lowerBound = new Coordinates(-this.distance, -this.distance);
		Coordinates upperBound = new Coordinates(this.distance, this.distance);
		// Viene utilizzato il comando MoveRandom per effettuare un movimento casuale
		new MoveRandom(lowerBound, upperBound, this.speed).execute(robot);
	}
}

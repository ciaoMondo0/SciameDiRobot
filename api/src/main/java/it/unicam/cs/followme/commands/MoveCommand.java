package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.space.Direction;

/**
 * Comando che muove il robot in una direzione specificata a una velocità definita.
 */
public class MoveCommand implements Command<Robot> {

	private final Direction dir;
	private final double speed;

	/**
	 * Costruttore.
	 *
	 * @param dir   la direzione di movimento
	 * @param speed la velocità di movimento
	 */
	public MoveCommand(Direction dir, double speed) {
		// Viene creata una copia della direzione per evitare riferimenti condivisi
		this.dir = new Direction(dir.getX(), dir.getY());
		this.speed = speed;
	}

	@Override
	public void execute(Robot robot) {
		robot.move(speed, dir);
		System.out.println(robot + " si muove verso " + dir + " a velocità " + speed + " da " + robot.getPosition());
		robot.getProgramExecutor().increment();
	}
}

package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.space.Direction;

/**
 * Comando che muove il robot in una direzione specificata a una velocità definita.
 */
public class MoveCommand implements Command {

	private Direction dir;
	private final double speed;

	/**
	 * Costruttore.
	 *
	 * @param dir   la direzione di movimento
	 * @param speed la velocità di movimento
	 */
	public MoveCommand(Direction dir, double speed) {
		// Crea una nuova istanza per evitare riferimenti condivisi non desiderati
		this.dir = new Direction(dir.getX(), dir.getY());
		this.speed = speed;
	}

	/**
	 * Esegue il comando spostando il robot e incrementando l'indice del programma.
	 *
	 * @param robot            il robot su cui eseguire il comando
	 * @param programExecution il gestore dell'esecuzione dei comandi
	 */
	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		robot.move(speed, dir);
		System.out.println(robot + " si muove verso " + dir + " a velocità " + speed + " da " + robot.getPosition());
		programExecution.increment();
	}
}

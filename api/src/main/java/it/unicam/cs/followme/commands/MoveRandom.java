package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;

/**
 * Comando che muove il robot in maniera casuale entro due coordinate.
 */
public class MoveRandom implements Command {

	private final Coordinates lowerBound;
	private final Coordinates upperBound;
	private final double speed;

	/**
	 * Costruttore.
	 *
	 * @param lowerBound la coordinata inferiore del range
	 * @param upperBound la coordinata superiore del range
	 * @param speed      la velocità di movimento
	 */
	public MoveRandom(Coordinates lowerBound, Coordinates upperBound, double speed) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.speed = speed;
	}

	/**
	 * Esegue il comando generando una direzione casuale e muovendo il robot.
	 *
	 * @param robot            il robot su cui eseguire il comando
	 * @param programExecution il gestore dell'esecuzione dei comandi
	 */
	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		Direction dir = Direction.calculateDirection(robot.getPosition(),
				Coordinates.getRandom(lowerBound, upperBound));
		robot.move(speed, dir);
		System.out.println(robot + " si muove casualmente verso " + dir);
		programExecution.increment();
	}
}

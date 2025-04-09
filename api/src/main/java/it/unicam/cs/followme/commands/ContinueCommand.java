package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

/**
 * Comando che fa continuare il movimento del robot per un determinato intervallo di tempo.
 */
public class ContinueCommand implements Command {

	private int seconds;
	private final int jumpIndex;
	private final int time;

	/**
	 * Costruisce un comando per continuare il movimento.
	 *
	 * @param time      il numero di secondi per il movimento continuo
	 * @param jumpIndex l'indice di salto per il ProgramExecutor
	 */
	public ContinueCommand(int time, int jumpIndex) {
		this.time = time;
		this.seconds = 0;
		this.jumpIndex = jumpIndex;
	}

	/**
	 * Esegue il comando sul robot e gestisce il salto o l'incremento dell'indice del programma.
	 *
	 * @param robot            il robot su cui eseguire il comando
	 * @param programExecution il gestore dell'esecuzione dei comandi
	 */
	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		System.out.println(robot + " continua per " + time + " secondi da " + robot.getPosition());
		seconds++;

		if (seconds < time) {
			robot.continueMovement();
			programExecution.jumpTo(jumpIndex);
		} else {
			programExecution.increment();
		}
	}
}

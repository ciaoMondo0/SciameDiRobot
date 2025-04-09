package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;

/**
 * Comando che continua il movimento del robot per un certo numero di "tick".
 */
public class ContinueCommand implements Command<Robot> {

	private int seconds;
	private final int jumpIndex;
	private final int time;

	/**
	 * Costruttore.
	 *
	 * @param time      il numero di "tick" in cui continuare il movimento
	 * @param jumpIndex l'indice a cui saltare se non sono trascorsi sufficienti tick
	 */
	public ContinueCommand(int time, int jumpIndex) {
		this.time = time;
		this.seconds = 0;
		this.jumpIndex = jumpIndex;
	}

	@Override
	public void execute(Robot robot) {
		System.out.println(robot + " continua per " + time + " tick da " + robot.getPosition());
		seconds++;
		// Utilizza il ProgramExecutor associato al robot per controllare il flusso dei comandi.
		if (seconds < time) {
			robot.continueMovement();
			robot.getProgramExecutor().jumpTo(jumpIndex);
		} else {
			robot.getProgramExecutor().increment();
		}
	}
}

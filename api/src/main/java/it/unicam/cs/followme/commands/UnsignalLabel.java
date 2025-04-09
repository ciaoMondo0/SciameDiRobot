package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;

/**
 * Comando che rimuove (unsignal) un'etichetta dal robot.
 */
public class UnsignalLabel implements Command<Robot> {

	private final String label;

	/**
	 * Costruttore.
	 *
	 * @param label l'etichetta da rimuovere
	 */
	public UnsignalLabel(String label) {
		this.label = label;
	}

	@Override
	public void execute(Robot robot) {
		robot.unsignal(label);
		System.out.println(robot + " unsignalled " + label);
		robot.getProgramExecutor().increment();
	}
}

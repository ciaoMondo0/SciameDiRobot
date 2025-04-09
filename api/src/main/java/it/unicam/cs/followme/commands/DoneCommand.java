package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;

/**
 * Comando che segnala la conclusione di un ciclo di esecuzione.
 */
public class DoneCommand implements Command<Robot> {

	private final int jumpIndex;

	/**
	 * Costruttore.
	 *
	 * @param jumpIndex l'indice a cui saltare dopo il completamento
	 */
	public DoneCommand(int jumpIndex) {
		this.jumpIndex = jumpIndex;
	}

	@Override
	public void execute(Robot robot) {
		robot.getProgramExecutor().jumpTo(jumpIndex);
		System.out.println("Done");
	}
}

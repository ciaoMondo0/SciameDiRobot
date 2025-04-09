package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;

/**
 * Comando che esegue un ciclo infinito (do forever).
 */
public class DoForeverCommand implements Command<Robot>, LoopInstructions {

	private final int jumpIndex;
	private int endCommandIndex;

	/**
	 * Costruttore.
	 *
	 * @param jumpIndex l'indice di salto del ciclo
	 */
	public DoForeverCommand(int jumpIndex) {
		this.jumpIndex = jumpIndex;
	}

	@Override
	public void execute(Robot robot) {
		robot.getProgramExecutor().jumpTo(jumpIndex);
		robot.getProgramExecutor().increment();
	}

	@Override
	public void setEndIndex(int n) {
		this.endCommandIndex = n;
	}

	@Override
	public int getJump() {
		return jumpIndex;
	}
}

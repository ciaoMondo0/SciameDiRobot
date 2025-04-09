package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;

/**
 * Comando che ripete un gruppo di istruzioni per un numero fissato di volte.
 */
public class RepeatCommand implements Command<Robot>, LoopInstructions {

	private final int times;
	private int repetitions;
	private final int jumpIndex;
	private int endCommandIndex;

	/**
	 * Costruttore.
	 *
	 * @param times     il numero di ripetizioni desiderate
	 * @param jumpIndex l'indice di salto iniziale per il loop
	 */
	public RepeatCommand(int times, int jumpIndex) {
		this.times = times;
		this.jumpIndex = jumpIndex;
		this.repetitions = 0;
	}

	@Override
	public synchronized void execute(Robot robot) {
		System.out.println("REPEAT: ripetizione " + repetitions);
		repetitions++;
		if (repetitions < times) {
			robot.getProgramExecutor().jumpTo(jumpIndex);
		} else {
			robot.getProgramExecutor().jumpTo(endCommandIndex);
			repetitions = 0;
		}
		robot.getProgramExecutor().increment();
	}

	@Override
	public synchronized void setEndIndex(int endIndex) {
		this.endCommandIndex = endIndex;
	}

	@Override
	public synchronized int getJump() {
		return jumpIndex;
	}
}

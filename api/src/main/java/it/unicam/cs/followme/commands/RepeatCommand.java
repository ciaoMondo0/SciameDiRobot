package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

/**
 * Comando che ripete un gruppo di istruzioni per un numero fissato di volte.
 */
public class RepeatCommand implements Command, LoopInstructions {

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
		this.endCommandIndex = 0;
	}

	/**
	 * Esegue il comando, gestendo il ciclo di ripetizione e aggiornando l'indice nel ProgramExecutor.
	 *
	 * @param robot            il robot su cui eseguire il comando
	 * @param programExecution il gestore dell'esecuzione dei comandi
	 */
	@Override
	public synchronized void execute(Robot robot, ProgramExecutor programExecution) {
		System.out.println("REPEAT: ripetizione " + repetitions);
		repetitions++;

		if (repetitions < times) {
			programExecution.jumpTo(jumpIndex);
		} else {
			programExecution.jumpTo(endCommandIndex);
			repetitions = 0;
		}
		programExecution.increment();
	}

	/**
	 * Imposta l'indice finale del ciclo.
	 *
	 * @param endIndex l'indice al termine del ciclo
	 */
	@Override
	public synchronized void setEndIndex(int endIndex) {
		this.endCommandIndex = endIndex;
	}

	/**
	 * Restituisce l'indice di salto.
	 *
	 * @return l'indice di salto
	 */
	@Override
	public synchronized int getJump() {
		return jumpIndex;
	}
}

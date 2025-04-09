package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

/**
 * Comando che esegue un ciclo infinito (do forever).
 */
public class DoForeverCommand implements Command, LoopInstructions {

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

	/**
	 * Esegue il comando, effettuando il salto al punto indicato e poi incrementando l'indice.
	 *
	 * @param robot            il robot su cui eseguire il comando
	 * @param programExecution il gestore dell'esecuzione dei comandi
	 */
	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		programExecution.jumpTo(jumpIndex);
		programExecution.increment();
	}

	/**
	 * Imposta l'indice finale del ciclo.
	 *
	 * @param n l'indice finale
	 */
	@Override
	public void setEndIndex(int n) {
		this.endCommandIndex = n;
	}

	/**
	 * Restituisce l'indice di salto.
	 *
	 * @return l'indice di salto
	 */
	@Override
	public int getJump() {
		return jumpIndex;
	}
}

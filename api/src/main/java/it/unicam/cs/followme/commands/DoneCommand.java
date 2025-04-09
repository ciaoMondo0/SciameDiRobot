package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

/**
 * Comando che segnala la conclusione di un ciclo di esecuzione.
 */
public class DoneCommand implements Command {

	private final int jumpIndex;

	/**
	 * Costruttore.
	 *
	 * @param jumpIndex l'indice a cui saltare dopo il completamento
	 */
	public DoneCommand(int jumpIndex) {
		this.jumpIndex = jumpIndex;
	}

	/**
	 * Esegue il comando effettuando il salto al punto indicato e stampa il messaggio "Done".
	 *
	 * @param robot            il robot su cui eseguire il comando
	 * @param programExecution il gestore dell'esecuzione dei comandi
	 */
	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		programExecution.jumpTo(jumpIndex);
		System.out.println("Done");
	}
}

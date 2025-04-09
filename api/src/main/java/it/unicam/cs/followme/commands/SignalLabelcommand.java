package it.unicam.cs.followme.commands;

import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

/**
 * Comando per segnalare un'etichetta associata al robot.
 */
public class SignalLabelcommand implements Command {

	private final String label;
	private final List<String> labels = new ArrayList<>();

	/**
	 * Costruttore.
	 *
	 * @param label l'etichetta da segnalare
	 */
	public SignalLabelcommand(String label) {
		this.label = label;
	}

	/**
	 * Esegue il comando di segnalazione sul robot.
	 *
	 * @param robot            il robot su cui eseguire il comando
	 * @param programExecution il gestore dell'esecuzione dei comandi
	 */
	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		robot.signals(label);
		System.out.println(robot + " segnala " + label);
		programExecution.increment();
	}
}

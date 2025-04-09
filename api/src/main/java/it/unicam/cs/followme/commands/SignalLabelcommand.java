package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import java.util.ArrayList;
import java.util.List;

/**
 * Comando per segnalare un'etichetta associata al robot.
 */
public class SignalLabelcommand implements Command<Robot> {

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

	@Override
	public void execute(Robot robot) {
		robot.signals(label);
		System.out.println(robot + " segnala " + label);
		robot.getProgramExecutor().increment();
	}
}

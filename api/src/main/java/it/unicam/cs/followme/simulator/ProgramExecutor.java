package it.unicam.cs.followme.simulator;

import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Area;

/**
 * Classe responsabile dell'esecuzione di una sequenza di comandi.
 */
public class ProgramExecutor {

	private int counter;
	private List<Command> commands;
	private List<Area> shapes;

	/**
	 * Costruttore con lista di comandi e aree.
	 *
	 * @param commands lista dei comandi
	 * @param shapes   lista delle aree
	 */
	public ProgramExecutor(List<Command> commands, List<Area> shapes) {
		this.commands = new ArrayList<>(commands);
		this.shapes = new ArrayList<>(shapes);
		this.counter = 0;
	}

	/**
	 * Costruttore di default.
	 */
	public ProgramExecutor() {
		this.commands = new ArrayList<>();
		this.shapes = new ArrayList<>();
		this.counter = 0;
	}

	/**
	 * Aggiunge un comando alla lista.
	 *
	 * @param command il comando da aggiungere
	 */
	public synchronized void addCommand(Command command) {
		commands.add(command);
	}

	/**
	 * Esegue il comando corrente sul robot.
	 *
	 * @param robot il robot su cui eseguire il comando
	 */
	public synchronized void executeCommand(Robot robot) {
		if (counter >= 0 && counter < commands.size()) {
			commands.get(counter).execute(robot, this);
		}
	}

	/**
	 * Imposta il contatore ad un indice specifico.
	 *
	 * @param index l'indice verso cui saltare
	 */
	public synchronized void jumpTo(int index) {
		counter = index;
	}

	/**
	 * Incrementa il contatore dei comandi.
	 */
	public synchronized void increment() {
		counter++;
	}

	/**
	 * Controlla se tutti i comandi sono stati eseguiti.
	 *
	 * @return {@code true} se il programma è terminato, {@code false} altrimenti
	 */
	public boolean isFinished() {
		return counter >= commands.size();
	}

	/**
	 * Pulisce la lista dei comandi e resetta il contatore.
	 */
	public void clearCommands() {
		commands.clear();
		counter = 0;
	}
}

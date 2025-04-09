package it.unicam.cs.followme.simulator;

import java.util.ArrayList;
import java.util.List;
import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Area;

/**
 * Classe che gestisce l'esecuzione di una sequenza di comandi.
 *
 * @param <R> il tipo di robot che esegue i comandi
 */
public class ProgramExecutor<R extends Robot> {

	private int counter;
	private List<Command<R>> commands;
	private List<Area> shapes;

	/**
	 * Costruisce il ProgramExecutor con la lista dei comandi e delle aree.
	 *
	 * @param commands la lista dei comandi
	 * @param shapes   la lista delle aree
	 */
	public ProgramExecutor(List<Command<R>> commands, List<Area> shapes) {
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
	public synchronized void addCommand(Command<R> command) {
		commands.add(command);
	}

	/**
	 * Esegue il comando corrente sul robot.
	 *
	 * @param robot il robot su cui eseguire il comando
	 */
	public synchronized void executeCommand(R robot) {
		if (counter >= 0 && counter < commands.size()) {
			commands.get(counter).execute(robot);
		}
	}

	/**
	 * Imposta il contatore all'indice specificato.
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
	 * Controlla se la sequenza di comandi è terminata.
	 *
	 * @return {@code true} se sono stati eseguiti tutti i comandi, {@code false} altrimenti
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

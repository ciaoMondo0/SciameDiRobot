package it.unicam.cs.followme.simulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.factory.Builder;
import it.unicam.cs.followme.factory.RobotFactory;
import it.unicam.cs.followme.shapes.Area;

/**
 * Classe che simula uno sciame di robot.
 */
public class RobotSwarm implements Simulator {

	private final List<Robot> robots;
	private final List<Area> shapes;
	private final ProgramExecutor<Robot> programExecutor;
	private final ExecutorService executor;
	private final SimulationEngine simulationEngine;
	private final ParsingCommands commandParser;

	// La lista dei comandi è mantenuta all'interno del ProgramExecutor,
	// quindi non esponiamo un setter pubblico qui.

	// Costruttore privato per forzare l'utilizzo del builder
	public RobotSwarm(Builder builder) {
		this.executor = builder.executor;
		this.shapes = new ArrayList<>(builder.shapes);
		// Il ProgramExecutor viene iniettato (o costruito) insieme ad una lista vuota di comandi.
		this.programExecutor = builder.programExecutor != null
				? builder.programExecutor
				: new ProgramExecutor<>(new ArrayList<>(), this.shapes);
		this.commandParser = builder.commandParser;
		this.simulationEngine = new SimulationEngine(executor);
		// Crea lo sciame di robot tramite la RobotFactory
		this.robots = RobotFactory.createRobots(builder.robotCount, programExecutor);
	}

	/**
	 * Carica i comandi da un file ed aggiorna il ProgramExecutor.
	 *
	 * @param file il file contenente i comandi
	 * @throws IOException se si verifica un errore I/O durante il parsing
	 */
	public synchronized void loadInstructions(File file) throws IOException {
		// Crea o inietta il FileReader
		FileReader fileReader = new FileReader();
		// Utilizza FileReader per analizzare i comandi
		List<Command<Robot>> parsedCommands = fileReader.parseCommands(file);

		// Rimuove tutti i comandi esistenti nel ProgramExecutor
		programExecutor.clearCommands();

		// Aggiunge i comandi analizzati nel ProgramExecutor
		parsedCommands.forEach(programExecutor::addCommand);

		// Assicura che ogni robot utilizzi il ProgramExecutor aggiornato
		robots.forEach(robot -> robot.setProgramExecutor(programExecutor));
	}

	/**
	 * Esegue la simulazione per il tempo specificato.
	 *
	 * @param timeStepSeconds  l'intervallo di tempo della simulazione (in secondi)
	 * @param totalTimeSeconds la durata totale della simulazione (in secondi)
	 */
	public void simulate(double timeStepSeconds, double totalTimeSeconds) {
		try {
			simulationEngine.run(robots, programExecutor, timeStepSeconds, totalTimeSeconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Arresta correttamente l'executor.
	 */
	public void shutdown() {
		executor.shutdown();
		try {
			if (!executor.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Restituisce una vista non modificabile dei robot.
	 *
	 * @return la lista dei robot nello sciame
	 */
	public List<Robot> getRobots() {
		return Collections.unmodifiableList(robots);
	}
}

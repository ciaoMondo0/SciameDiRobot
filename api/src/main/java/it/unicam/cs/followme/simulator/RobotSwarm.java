package it.unicam.cs.followme.simulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.space.Coordinates;

/**
 * Classe che simula uno sciame di robot.
 */
public class RobotSwarm implements Simulator {

	private final List<Robot> robots;
	private final List<Area> shapes;
	private final ExecutorService executor;
	private volatile List<Command> commands;
	private ProgramExecutor program;

	/**
	 * Costruisce lo swarm specificando il numero di robot.
	 *
	 * @param numberRobots il numero di robot nello swarm
	 */
	public RobotSwarm(int numberRobots) {
		this.robots = new ArrayList<>();
		this.commands = new ArrayList<>();
		this.shapes = new ArrayList<>();
		this.program = new ProgramExecutor(commands, shapes);
		this.executor = Executors.newFixedThreadPool(numberRobots);

		for (int i = 0; i < numberRobots; i++) {
			robots.add(new Robot(Coordinates.generateRandomCoordinates()));
		}
	}

	/**
	 * Legge il file dei comandi e li esegue impostando il ProgramExecutor.
	 *
	 * @param comandi il file contenente i comandi
	 * @throws IOException se si verificano problemi di I/O
	 */
	public synchronized void executeInstructions(File comandi) throws IOException {
		FileReader fileReader = new FileReader();
		this.commands = fileReader.parseCommands(comandi);
		this.program = new ProgramExecutor(commands, shapes);
	}

	/**
	 * Simula l'esecuzione dei comandi per un tempo totale specificato.
	 *
	 * @param dt   il passo temporale in secondi
	 * @param time il tempo totale di simulazione in secondi
	 */
	public void simulate(double dt, double time) {
		double seconds = 0.0;

		try {
			while (seconds < time) {
				List<Callable<Void>> tasks = new ArrayList<>();
				for (Robot robot : robots) {
					tasks.add(() -> {
						program.executeCommand(robot);
						return null;
					});
				}
				executor.invokeAll(tasks);
				Thread.sleep((long) (dt * 1000));
				seconds += dt;
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Arresta correttamente l'esecuzione degli executor.
	 */
	public void shutdown() {
		executor.shutdown();
		try {
			if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
	}

	/**
	 * Imposta la lista dei comandi.
	 *
	 * @param commands la lista dei comandi da impostare
	 */
	public synchronized void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	/**
	 * Restituisce la lista dei robot in maniera non modificabile.
	 *
	 * @return la lista dei robot
	 */
	public List<Robot> getRobots() {
		return Collections.unmodifiableList(robots);
	}
}

package it.unicam.cs.followme.app;

import it.unicam.cs.followme.factory.Builder;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.simulator.RobotSwarm;
import it.unicam.cs.followme.simulator.ParsingCommands;
import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.entity.Robot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	private RobotSwarm robotSwarm;

	public App(int numberOfRobots, ParsingCommands commandParser) {
		ExecutorService executor = Executors.newFixedThreadPool(numberOfRobots);
		// Costruisce lo sciame utilizzando il RobotSwarmBuilder separato
		this.robotSwarm = new Builder()
				.withRobotCount(numberOfRobots)
				.withExecutor(executor)
				.withCommandParser(commandParser)
				.build();
	}

	public void runSimulation() {
		File instructionsFile = new File("C:\\Users\\User\\Desktop\\SciameRobot\\followme\\app\\files\\comandi.txt");
		try {
			robotSwarm.loadInstructions(instructionsFile);
			robotSwarm.simulate(1, 50);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			robotSwarm.shutdown();
		}
	}

	public static void main(String[] args) {
		// Inizializza la tua istanza di command parser (ParsingCommands)
		// Inizializza una lista vuota di comandi
		List<Command<Robot>> commands = new ArrayList<>();
		// Inizializza una lista vuota di aree
		List<Area> shapesList = new ArrayList<>();

		ParsingCommands parser = new ParsingCommands(commands, shapesList);
		App app = new App(4, parser);
		app.runSimulation();
	}
}

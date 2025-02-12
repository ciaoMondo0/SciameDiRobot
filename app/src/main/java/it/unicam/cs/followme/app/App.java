package it.unicam.cs.followme.app;

import it.unicam.cs.followme.simulator.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.followme.commands.*;

public class App {

	private RobotSwarm robotSwarm;
	private List<Command> commands;

	public App(int numberRobots) {

		robotSwarm = new RobotSwarm(numberRobots);
		robotSwarm.setCommands(commands);
		this.commands = new ArrayList<>();

	}

	public void runSimulation() throws InterruptedException {
		

        File instructionsFile = new File("C:\\Users\\User\\Desktop\\SciameRobot\\followme\\app\\files\\comandi.txt");
        File figure = new File("C:\\Users\\User\\Desktop\\SciameRobot\\followme\\app\\files\\shapes.txt");


		try {
			robotSwarm.executeInstructions(instructionsFile);

			robotSwarm.simulate(1, 50);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		List<Command> commands = new ArrayList<>();

		App app = new App(4);
		app.runSimulation();
	}

}

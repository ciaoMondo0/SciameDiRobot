package it.unicam.cs.followme.simulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.space.Coordinates;

public class RobotSwarm implements Simulator {

	private List<Robot> robots;
	private List<Command> commands;
	private List<Area> shapes;
	private ProgramExecutor program;
	private FileReader files;

	public RobotSwarm(int numberRobots) {
		this.robots = new ArrayList<>();
		this.commands = new ArrayList<>();
		this.shapes = new ArrayList<>();

		this.program = new ProgramExecutor(commands, shapes);
		for (int i = 0; i < numberRobots; i++) {
			Robot robot = new Robot(Coordinates.generateRandomCoordinates());
			robots.add(robot);
		}
		files = new FileReader();

	}

	

	public void executeInstructions(File comandi, File file2) throws IOException, InterruptedException {
		shapes = files.loadShapes(file2);

		commands = files.parseCommands(comandi); // Parse new commands from the file

		this.program = new ProgramExecutor(commands, shapes);

	}

	@Override
	public void simulate(double dt, double time) {
		double seconds = 0.0;
		ExecutorService executor = Executors.newFixedThreadPool(robots.size());
		while (seconds < time) {
			for (Robot robot : robots) {
				Runnable task = () -> {
					this.program.executeCommand(robot);
				};
				executor.submit(task);
			}

			try {
				Thread.sleep((long) (dt * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			seconds += dt;
		}

		executor.shutdown();
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

}

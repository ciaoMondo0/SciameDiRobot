package it.unicam.cs.followme.simulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

	

	public void executeInstructions(File comandi) throws IOException, InterruptedException {

		commands = files.parseCommands(comandi); // Parse new commands from the file

		this.program = new ProgramExecutor(commands, shapes);

	}



	public void simulate(double dt, double time) {
		double seconds = 0.0;
		int numRobots = robots.size();

		ExecutorService executor = Executors.newFixedThreadPool(numRobots);

		try {
			while (seconds < time) {
				List<Callable<Void>> tasks = new ArrayList<>();

				for (Robot robot : robots) {
					tasks.add(() -> {
						this.program.executeCommand(robot);
						return null;
					});
				}

				executor.invokeAll(tasks);

				Thread.sleep((long) (dt * 1000));

				seconds += dt;
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} finally {
			executor.shutdown();
			try {
				if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
					executor.shutdownNow();
				}
			} catch (InterruptedException e) {
				executor.shutdownNow();
			}
		}
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public List<Robot> getRobots() {
		return robots;
	}
}

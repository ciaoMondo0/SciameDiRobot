package it.unicam.cs.followme.commands;

import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.simulator.ProgramExecutor;

public class UntilCommand implements Command, LoopInstructions {

	private final String label;
	private final List<Area> shapes;
	private ProgramExecutor programExecution;

	private final int jumpIndex;
	private int nextIndex;

	public UntilCommand(String label, int jumpIndex, List<Area> shapes) {
		this.label = label;
		this.jumpIndex = jumpIndex;
		this.shapes = shapes;
		programExecution = new ProgramExecutor();

	}
	
	@Override
	public synchronized void execute(Robot robot, ProgramExecutor programExecution) {
		System.out.println("Until " + label + robot.getPosition());
		Area shape = shapes.stream().filter(s -> s.getLabel().equalsIgnoreCase(this.label)).findFirst()
				.orElse(null);

		if (checkInsideArea(shape, robot)) {

			programExecution.jumpTo(jumpIndex);

		} else {

			programExecution.jumpTo(nextIndex);


		}
		programExecution.increment();

	}

	public boolean checkInsideArea(Area shape, Robot robot) {

		return shape.containsRobot(robot.getPosition());

	}

	@Override
	public synchronized void setEndIndex(int n) {
		this.nextIndex = n;

	}

	@Override
	public synchronized int getJump() {
		return jumpIndex;
	}

}
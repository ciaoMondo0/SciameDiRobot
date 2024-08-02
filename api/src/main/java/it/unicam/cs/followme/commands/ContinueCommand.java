package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

public class ContinueCommand implements Command {

	private int seconds;
	private final int jumpIndex;
	private ProgramExecutor programExecution;
	private final int time;

	public ContinueCommand(int time, int jumpIndex) {
		this.time = time;
		this.seconds = 0;
		programExecution = new ProgramExecutor();
		this.jumpIndex = jumpIndex;
	}

	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		System.out.println(robot + " continues for " + time + robot.getPosition());
		this.seconds++;

		if (this.seconds < this.time) {
			robot.continueMovement();
			programExecution.jumpTo(jumpIndex);

		} else {
		programExecution.increment();
		}

	}

	

}

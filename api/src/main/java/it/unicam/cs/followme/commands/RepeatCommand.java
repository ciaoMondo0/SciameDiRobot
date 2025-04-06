package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

public class RepeatCommand implements Command, LoopInstructions {

	private int times;
	private int repetitions;
	private final int jumpIndex;
	private int endCommandIndex;

	public RepeatCommand(int times, int jumpIndex) {
		this.times = times;
		this.jumpIndex = jumpIndex;
		this.endCommandIndex = 0;
	}

	@Override
	public synchronized void execute(Robot robot, ProgramExecutor programExecution) {
		System.out.println("REPEAT" + " " + repetitions);
		repetitions++;

		if (repetitions < times) {
			// We haven't completed the required number of repetitions yet
			programExecution.jumpTo(jumpIndex);
		} else if (repetitions > times) {
			programExecution.jumpTo(endCommandIndex);

			// We've completed the required number of repetitions
			repetitions = 0;
		} // Reset for potential future repeats
		programExecution.increment();
	}

	


	@Override
	public synchronized void setEndIndex(int endIndex) {
		this.endCommandIndex = endIndex;
	}

	@Override
	public synchronized int getJump() {
		// TODO Auto-generated method stub
		return jumpIndex;
	}

}

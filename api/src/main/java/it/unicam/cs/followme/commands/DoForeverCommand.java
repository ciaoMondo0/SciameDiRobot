package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

public class DoForeverCommand implements Command, LoopInstructions {
	
	private final int jumpIndex;
	private int endCommandIndex;

	
	public DoForeverCommand(int jumpIndex) {
		this.jumpIndex = jumpIndex;
	}

	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		programExecution.jumpTo(jumpIndex);
		programExecution.increment();
		
	}

	@Override
	public void setEndIndex(int n) {
		this.endCommandIndex = n;
		
	}

	@Override
	public int getJump() {
		return jumpIndex;
	}
}

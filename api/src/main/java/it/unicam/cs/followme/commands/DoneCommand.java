package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

public class DoneCommand implements Command {
	
	private final int jumpIndex;
	
	public DoneCommand(int jumpIndex) {
		this.jumpIndex = jumpIndex;
	}

	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		programExecution.jumpTo(jumpIndex);
		System.out.println("Done");
		
	}


	
	

}

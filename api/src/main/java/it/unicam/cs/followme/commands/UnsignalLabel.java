package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

public class UnsignalLabel implements Command{
	
	private String label;
	
	public UnsignalLabel(String label) {
		this.label = label;
	}

	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		robot.unsignal(label);
	programExecution.increment();
	System.out.println(robot + " unsignalled " + label);
	}

	

}

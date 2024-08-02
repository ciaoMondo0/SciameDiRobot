package it.unicam.cs.followme.commands;

import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

public class SignalLabelcommand implements Command {
	
	private String label;
	List<String> labels;
	public SignalLabelcommand(String label) {
		this.label = label;
		this.labels = new ArrayList<>();
		
	}

	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {

		robot.signals(label);
		System.out.println(robot + " is signaling " + label);
		programExecution.increment();
		
	}

	
	

}

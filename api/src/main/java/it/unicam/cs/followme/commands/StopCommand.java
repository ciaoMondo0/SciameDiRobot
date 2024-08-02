package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;

public class StopCommand implements Command{

	@Override
	public void execute(Robot robot, ProgramExecutor programExecution) {
		robot.stop();
		System.out.println(robot + " stopped " + robot.getSpeed());
		programExecution.increment();
		
	}

	

}

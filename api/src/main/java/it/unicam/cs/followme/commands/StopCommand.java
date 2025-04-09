package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;

/**
 * Comando che ferma il robot.
 */
public class StopCommand implements Command<Robot> {

	@Override
	public void execute(Robot robot) {
		robot.stop();
		System.out.println(robot + " stopped with speed " + robot.getSpeed());
		robot.getProgramExecutor().increment();
	}
}

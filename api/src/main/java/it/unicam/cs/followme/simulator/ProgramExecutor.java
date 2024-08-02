package it.unicam.cs.followme.simulator;

import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.followme.commands.*;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Area;

public class ProgramExecutor {
	private int counter;
	private List<Command> commands = new ArrayList<>();
	private List<Area> shapes = new ArrayList<>();

	public ProgramExecutor(List<Command> commands, List<Area> shapes) {
		this.commands = commands;
		this.shapes = shapes;
		this.counter = 0;
	}
	
	 

	public ProgramExecutor() {

	}

	public synchronized void addCommand(Command command) {
		commands.add(command);
	}

	// The execute method of each command will handle incrementing the instruction index 
	//or jumping to a different index based on the command's behavior.

	public synchronized void executeCommand(Robot robot) {
		if (counter >= 0 && counter < commands.size()) {
			commands.get(counter).execute(robot, this);
		}
	}

	public synchronized void jumpTo(int index) {
		counter = index;

	}

	public synchronized void increment() {
		counter++;
	}

	public  boolean isFinished() {
		return counter >= commands.size();
	}

	public  void clearCommands() {
		commands.clear();
		counter = 0;
	}
}

package it.unicam.cs.followme.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.commands.*;
import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;


public class ParsingCommands implements FollowMeParserHandler {

	private List<Command> commands;

	private Stack<LoopInstructions> stack;

	private ProgramExecutor programExecution;


	List<Area> shapesList;
	List<String> labels;


	public ParsingCommands(List<Command> commands, List<Area> shapes) {
		this.commands = new ArrayList<>();
		this.stack = new Stack<>();
		this.shapesList = shapes;
		this.labels = new ArrayList<String>();
	}





	@Override
	public void parsingStarted() {
		this.programExecution = new ProgramExecutor();

		this.commands = new ArrayList<>();
		this.stack = new Stack<>();

	}

	@Override
	public void parsingDone() {
		this.getCommands();

	}

	@Override
	public void moveCommand(double[] args) {
		this.commands.add(new MoveCommand(new Direction((args[0]), args[1]), args[2]));
	}

	@Override
	public void moveRandomCommand(double[] args) {
		this.commands
				.add(new MoveRandom(new Coordinates((args[0]), args[1]), new Coordinates((args[2]), args[3]), args[2]));
	}

	@Override
	public void signalCommand(String label) {
		this.commands.add(new SignalLabelcommand(label));

	}

	@Override
	public void unsignalCommand(String label) {
		this.commands.add(new UnsignalLabel(label));

	}

	@Override
	public void followCommand(String label, double[] args) {
		this.commands.add(new FollowCommand(label, args[5], args[6]));

	}

	@Override
	public void stopCommand() {
		this.commands.add(new StopCommand());

	}

	@Override
	public void continueCommand(int s) {
		int currentCommandIndex = commands.size();

		this.commands.add(new ContinueCommand(s, currentCommandIndex));

	}

	@Override
	public void repeatCommandStart(int n) {
		int currentCommandIndex = commands.size();

		this.commands.add(new RepeatCommand(n, currentCommandIndex));
		this.stack.push(new RepeatCommand(n, currentCommandIndex));

	}

	@Override
	public void untilCommandStart(String label) {
		int currentCommandIndex = commands.size();

		this.commands.add(new UntilCommand(label, currentCommandIndex, shapesList));
		this.stack.push(new UntilCommand(label, currentCommandIndex, shapesList));

	}

	@Override
	public void doForeverStart() {
		int currentCommandIndex = commands.size();

		this.commands.add(new DoForeverCommand(currentCommandIndex));
		this.stack.push(new DoForeverCommand(currentCommandIndex));

	}

	
	
	//sets the index to the command after done
	@Override
	public void doneCommand() {
		if (!stack.isEmpty()) {
			int index = this.stack.pop().getJump();
			((LoopInstructions) this.commands.get(index)).setEndIndex(commands.size());

			commands.add(new DoneCommand(index));
		}
	}

	public List<Command> getCommands() {
		return commands;
	}

}

package it.unicam.cs.followme.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import it.unicam.cs.followme.commands.*;
import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;

/**
 * Classe che si occupa del parsing dei comandi e della costruzione della lista di istruzioni.
 */
public class ParsingCommands implements FollowMeParserHandler {

	private List<Command> commands;
	private Stack<LoopInstructions> stack;
	private ProgramExecutor programExecution;
	private final List<Area> shapesList;
	private final List<String> labels;

	/**
	 * Costruttore.
	 *
	 * @param commands lista iniziale di comandi (potenzialmente vuota)
	 * @param shapes   lista di aree
	 */
	public ParsingCommands(List<Command> commands, List<Area> shapes) {
		this.commands = new ArrayList<>();
		this.stack = new Stack<>();
		this.shapesList = shapes;
		this.labels = new ArrayList<>();
	}

	@Override
	public void parsingStarted() {
		this.programExecution = new ProgramExecutor();
		this.commands = new ArrayList<>();
		this.stack = new Stack<>();
	}

	@Override
	public void parsingDone() {
		// Operazioni eventuali a fine parsing
		getCommands();
	}

	@Override
	public void moveCommand(double[] args) {
		commands.add(new MoveCommand(new Direction(args[0], args[1]), args[2]));
	}

	@Override
	public void moveRandomCommand(double[] args) {
		commands.add(new MoveRandom(new Coordinates(args[0], args[1]), new Coordinates(args[2], args[3]), args[2]));
	}

	@Override
	public void signalCommand(String label) {
		commands.add(new SignalLabelcommand(label));
	}

	@Override
	public void unsignalCommand(String label) {
		commands.add(new UnsignalLabel(label));
	}

	@Override
	public void followCommand(String label, double[] args) {
		commands.add(new FollowCommand(label, args[5], args[6]));
	}

	@Override
	public void stopCommand() {
		commands.add(new StopCommand());
	}

	@Override
	public void continueCommand(int s) {
		int currentCommandIndex = commands.size();
		commands.add(new ContinueCommand(s, currentCommandIndex));
	}

	@Override
	public void repeatCommandStart(int n) {
		int currentCommandIndex = commands.size();
		RepeatCommand repeat = new RepeatCommand(n, currentCommandIndex);
		commands.add(repeat);
		stack.push(repeat);
	}

	@Override
	public void untilCommandStart(String label) {
		int currentCommandIndex = commands.size();
		UntilCommand until = new UntilCommand(label, currentCommandIndex, shapesList);
		commands.add(until);
		stack.push(until);
	}

	@Override
	public void doForeverStart() {
		int currentCommandIndex = commands.size();
		DoForeverCommand forever = new DoForeverCommand(currentCommandIndex);
		commands.add(forever);
		stack.push(forever);
	}

	@Override
	public void doneCommand() {
		if (!stack.isEmpty()) {
			int index = stack.pop().getJump();
			((LoopInstructions) commands.get(index)).setEndIndex(commands.size());
			commands.add(new DoneCommand(index));
		}
	}

	/**
	 * Restituisce la lista di comandi parsati.
	 *
	 * @return la lista dei comandi
	 */
	public List<Command> getCommands() {
		return commands;
	}
}

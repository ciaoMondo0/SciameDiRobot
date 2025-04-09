package it.unicam.cs.followme.simulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.ShapeData;
import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.shapes.Circle;
import it.unicam.cs.followme.shapes.Rectangle;
import it.unicam.cs.followme.entity.Robot;

/**
 * Un file reader che analizza i comandi e carica le forme utilizzate per il simulatore di robot.
 *
 * Questa classe utilizza un FollowMeParser per caricare le forme dell'ambiente e i comandi dei robot.
 * Restituisce comandi type-safe (Command<Robot>) e una lista di oggetti Area.
 */
public class FileReader {

	private final ArrayList<Area> shapesList;
	private final FollowMeParser parser;
	private final List<Command<Robot>> commands;
	private final ParsingCommands parsingCommands;

	/**
	 * Costruisce un FileReader.
	 */
	public FileReader() {
		this.shapesList = new ArrayList<>();
		// Inizializza la lista dei comandi in modo che non sia null.
		this.commands = new ArrayList<>();
		// Passa i comandi e le forme al parser handler.
		this.parsingCommands = new ParsingCommands(commands, shapesList);
		this.parser = new FollowMeParser(parsingCommands);
	}

	/**
	 * Carica le forme dal file fornito.
	 *
	 * @param figure il file contenente le definizioni delle forme.
	 * @return la lista degli oggetti Area caricati.
	 */
	public ArrayList<Area> loadShapes(File figure) {
		try {
			List<ShapeData> shapes = parser.parseEnvironment(figure);
			for (ShapeData shape : shapes) {
				if ("RECTANGLE".equalsIgnoreCase(shape.shape())) {
					double[] args = shape.args();
					shapesList.add(new Rectangle(args[0], args[1], args[2], args[3], shape.label()));
				}
				if ("CIRCLE".equalsIgnoreCase(shape.shape())) {
					double[] args = shape.args();
					shapesList.add(new Circle(args[0], args[1], args[2], shape.label()));
				}
			}
			return shapesList;
		} catch (FollowMeParserException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Analizza i comandi dei robot dal file fornito.
	 *
	 * @param commandsFile il file contenente i comandi dei robot.
	 * @return la lista dei comandi analizzati.
	 * @throws IOException se si verifica un errore I/O durante il parsing.
	 */
	public List<Command<Robot>> parseCommands(File commandsFile) throws IOException {
		try {
			parser.parseRobotProgram(commandsFile);
		} catch (FollowMeParserException e) {
			e.printStackTrace();
		}
		// Restituisce i comandi accumulati dal gestore ParsingCommands.
		return parsingCommands.getCommands();
	}
}

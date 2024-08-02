package it.unicam.cs.followme.simulator;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import it.unicam.cs.followme.utilities.*;
import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.shapes.*;

public class FileReader {

	private ArrayList<Area> shapesList;
	private final FollowMeParser parser;
	private List<Command> commands;
	private ParsingCommands parsingCommands;

	public FileReader() {
		shapesList = new ArrayList<Area>();
		parsingCommands = new ParsingCommands(commands, shapesList);

		this.parser = new FollowMeParser(parsingCommands);
	}

	public ArrayList<Area> loadShapes(File figure){
		 try {
	            List<ShapeData> shapes = parser.parseEnvironment(figure);
	            for (ShapeData shape : shapes) {
	                if (shape.shape().equals("RECTANGLE")) {
	                	double[] args = shape.args();
	                	shapesList.add(new Rectangle(args[0], args[1], args[2], args[3], shape.label()));
	                }
	                if (shape.shape().equals("CIRCLE")) {
	                	double[] args = shape.args();
	                	shapesList.add(new Circle(args[0], args[1], args[2], shape.label()));
	                }
	            }
	            return shapesList;
	        } catch (FollowMeParserException | IOException e) {
	            throw new RuntimeException(e);
	        }
		

	}
	/*
	 * public List<Command> parseCommands(String file) throws IOException { try {
	 * File robotProgram = new File(file); parser.parseRobotProgram(robotProgram);
	 * 
	 * } catch (FollowMeParserException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return parsingCommands.getCommands();
	 * 
	 * }
	 */

	// Tempo solution

	public List<Command> parseCommands(File commands) throws IOException {
		  try {
		        parser.parseRobotProgram(commands);
		    } catch (FollowMeParserException e) {
		        e.printStackTrace();
		    }
		    return parsingCommands.getCommands();// Access the commands from the ParsingCommands object
	  }

}

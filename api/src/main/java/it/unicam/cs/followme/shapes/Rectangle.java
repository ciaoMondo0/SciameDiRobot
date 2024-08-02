package it.unicam.cs.followme.shapes;

import java.util.Random;

import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.utilities.ShapeData;

public class Rectangle implements Area {

	private Coordinates coordinates;

	private double width;
	private double height;
	private String label;

	public Rectangle(double x, double y, double width, double height, String label) {
		this.coordinates = new Coordinates(x, y);
		this.width = width;
		this.height = height;
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public Coordinates getCoordinates() {
		Random random = new Random();

		double centerX = coordinates.getX() - width / 2 + random.nextDouble() * width;
		double centerY = coordinates.getY() - height / 2 + random.nextDouble() * height;

		return new Coordinates(centerX, centerY);
	}

	@Override
	public  boolean containsRobot(Coordinates robot) {
		return robot.getX() >= coordinates.getX() - width / 2 && robot.getX() <= coordinates.getX() + width / 2
				&& robot.getY() >= coordinates.getY() - height / 2 && robot.getY() <= coordinates.getY() + height / 2;
	}

}
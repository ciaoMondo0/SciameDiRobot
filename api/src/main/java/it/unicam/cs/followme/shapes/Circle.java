package it.unicam.cs.followme.shapes;

import java.util.Random;

import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.utilities.ShapeData;

public class Circle implements Area {
	private Coordinates coordinates;
	private double radius;
	private String label;

	public Circle(double x, double y, double radius, String label) {
		this.coordinates = new Coordinates(x, y);
		this.radius = radius;
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public boolean containsRobot(Coordinates robot) {
		double distance = Coordinates.calculateDistance(robot, coordinates);
		return distance <= radius;
	}

	@Override
	public Coordinates getCoordinates() {
		Random random = new Random();
		double theta = 2 * Math.PI * random.nextDouble();

		double r = radius * Math.sqrt(random.nextDouble());
		double x = r * Math.cos(theta);
		double y = r * Math.sin(theta);
		return new Coordinates(x, y);

	}

}

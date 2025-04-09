package it.unicam.cs.followme.shapes;

import java.util.Random;

import it.unicam.cs.followme.space.Coordinates;

/**
 * Rappresenta un'area di forma circolare.
 */
public class Circle implements Area {

	private final Coordinates center;
	private final double radius;
	private final String label;

	/**
	 * Costruisce un cerchio a partire dal centro, raggio ed etichetta.
	 *
	 * @param x     coordinata x del centro
	 * @param y     coordinata y del centro
	 * @param radius il raggio del cerchio
	 * @param label  l'etichetta dell'area
	 */
	public Circle(double x, double y, double radius, String label) {
		this.center = new Coordinates(x, y);
		this.radius = radius;
		this.label = label;
	}

	/**
	 * Restituisce l'etichetta dell'area.
	 *
	 * @return l'etichetta
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * Verifica se un robot si trova all'interno del cerchio.
	 *
	 * @param robot le coordinate del robot
	 * @return {@code true} se il robot è contenuto nel cerchio, altrimenti {@code false}
	 */
	@Override
	public boolean containsRobot(Coordinates robot) {
		double distance = Coordinates.calculateDistance(robot, center);
		return distance <= radius;
	}

	/**
	 * Genera delle coordinate casuali all'interno del cerchio.
	 *
	 * @return le coordinate generate
	 */
	@Override
	public Coordinates getCoordinates() {
		Random random = new Random();
		double theta = 2 * Math.PI * random.nextDouble();
		double r = radius * Math.sqrt(random.nextDouble());
		double x = center.getX() + r * Math.cos(theta);
		double y = center.getY() + r * Math.sin(theta);
		return new Coordinates(x, y);
	}
}

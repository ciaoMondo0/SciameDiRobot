package it.unicam.cs.followme.space;

import java.util.Objects;
import java.util.Random;

/**
 * Classe che va a rappresentare una coordinata nello spazio
 */
public class Coordinates implements Points {
	/**
	 * Ascissa
	 */
	private double x;
	/**
	 * Ordinata
	 */
	private double y;

	public Coordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinates() {
		
	}
    
	@Override
	public double getX() {
		return x;
	}
    
	@Override
	public void setX(double x) {
		this.x = x;
	}
    
	@Override
	public double getY() {
		return y;
	}
    
	@Override
	public void setY(double y) {
		this.y = y;
	}

	

	@Override
	public String toString() {
		return " (x: " + this.x + "; y: " + this.y + ") ";
	}

	public static Coordinates getRandom(Coordinates position1, Coordinates position2) {
		Random random = new Random();
		double x = random.nextDouble() * (position2.getX() - position1.getX()) + position1.getX();
		double y = random.nextDouble() * (position2.getY() - position1.getY()) + position1.getY();
		return new Coordinates(x, y);
	}
	
	public static Coordinates generateRandomCoordinates() {
		Random random = new Random();
		double x = random.nextDouble() * 2 - 1; 
		double y = random.nextDouble() * 2 - 1; 
		return new Coordinates(x, y);
	}
	
	public static double calculateDistance(Coordinates robot1, Coordinates robot2) {
		double dx = robot1.getX() - robot2.getX();
		double dy = robot1.getY() - robot2.getY();
		return Math.sqrt(dx * dx + dy * dy);

	}

	

}
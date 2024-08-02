package it.unicam.cs.followme.space;

public class Direction implements Points {

	private double x;
	private double y;

	public Direction(double x, double y) {
		if ((x < -1 || x > 1) && (y < -1 || y > 1) && (x == 0 && y == 0)) {
			throw new IllegalArgumentException("Coordinates must be between -1 and 1 and one different from zero");
		}
		this.x = x;
		this.y = y;
	}

	public Direction() {
	}
    
	
	@Override
	public void setX(double x) {
		if (x < -1 || x > 1) {
			throw new IllegalArgumentException("x must be between -1 and 1");
		}
		this.x = x;
	}
    
	@Override
	public void setY(double y) {
		if (y < -1 || y > 1) {
			throw new IllegalArgumentException("y must be between -1 and 1");
		}
		this.y = y;
	}
    
	
	@Override
	public double getX() {
		return this.x;
	}
    
	@Override
	public double getY() {
		return this.y;
	}

	public static Direction calculateDirection(Coordinates source, Coordinates direction) {
		double dx = direction.getX() - source.getX();
		double dy = direction.getY() - source.getY();
		double sqrt = Math.sqrt(dx * dx + dy * dy);
		return new Direction(dx / sqrt, dy / sqrt);
	}

	@Override
	public String toString() {
		return " x: " + this.x + "; y: " + this.y + " ";
	}

}

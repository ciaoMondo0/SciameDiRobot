package it.unicam.cs.followme.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unicam.cs.followme.space.*;

public class Robot {
	private Coordinates position;
	private Direction dir;
	private double speed;
	private List<String> signalLabel;
	private String labelSignal;

	public Robot(Coordinates position, Direction dir, double speed, String signalLabel) {
		this.position = position;
		this.dir = dir;
		this.signalLabel = new ArrayList<String>();
	}

	public Robot(Coordinates position) {
		this.position = position;

	}

	public Robot() {
	}

	public void move(double speed, Direction direction) {

		this.dir = new Direction(direction.getX(), direction.getY());
		this.speed = speed;
		this.position.setX(direction.getX() * speed + this.position.getX());
		this.position.setY(direction.getY() * speed + this.position.getY());

	}

	public void signals(String label) {
		this.labelSignal = label;
	}

	public void unsignal(String label) {
		if (this.labelSignal.equals(label)) {
			this.labelSignal = "";
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Coordinates getPosition() {
		return position;
	}

	public void setPosition(Coordinates position) {
		this.position = position;
	}

	public String getLabel() {
		return this.labelSignal;
	}

	public double getSpeed() {
		return this.speed;
	}

	public Direction getDirection() {
		return dir;
	}

	public void setDirection(Direction direction) {
		this.dir = direction;
	}

	public void stop() {
		this.dir = new Direction(0, 0);
		this.speed = 0;

	}

	public void continueMovement() {
		this.move(speed, dir);
	}

}
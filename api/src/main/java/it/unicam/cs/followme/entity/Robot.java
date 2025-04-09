package it.unicam.cs.followme.entity;

import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;

/**
 * Rappresenta un robot con posizione, direzione e velocità.
 */
public class Robot {

	private Coordinates position;
	private Direction dir;
	private double speed;
	private String currentLabel;

	// Riferimento al ProgramExecutor associato al robot
	private ProgramExecutor<Robot> programExecutor;

	/**
	 * Costruisce un robot a partire da coordinate iniziali.
	 *
	 * @param position le coordinate iniziali
	 */
	public Robot(Coordinates position) {
		this.position = position;
	}

	// Setter e getter per il ProgramExecutor

	/**
	 * Imposta il ProgramExecutor per questo robot.
	 *
	 * @param programExecutor il ProgramExecutor associato
	 */
	public void setProgramExecutor(ProgramExecutor<Robot> programExecutor) {
		this.programExecutor = programExecutor;
	}

	/**
	 * Restituisce il ProgramExecutor associato.
	 *
	 * @return il ProgramExecutor
	 */
	public ProgramExecutor<Robot> getProgramExecutor() {
		return programExecutor;
	}

	/**
	 * Muove il robot secondo la direzione e velocità specificate.
	 *
	 * @param speed     la velocità
	 * @param direction la direzione
	 */
	public void move(double speed, Direction direction) {
		this.dir = new Direction(direction.getX(), direction.getY());
		this.speed = speed;
		this.position.setX(direction.getX() * speed + this.position.getX());
		this.position.setY(direction.getY() * speed + this.position.getY());
	}

	/**
	 * Imposta il segnale sul robot.
	 *
	 * @param label il segnale
	 */
	public void signals(String label) {
		this.currentLabel = label;
	}

	/**
	 * Rimuove il segnale solo se corrisponde a quello attuale.
	 *
	 * @param label il segnale da rimuovere
	 * @throws IllegalArgumentException se il segnale non corrisponde
	 */
	public void unsignal(String label) {
		if (this.currentLabel != null && this.currentLabel.equals(label)) {
			this.currentLabel = "";
		} else {
			throw new IllegalArgumentException("Label non corrispondente");
		}
	}

	/**
	 * Restituisce la posizione attuale.
	 *
	 * @return le coordinate attuali
	 */
	public Coordinates getPosition() {
		return position;
	}

	public double getSpeed(){
		return speed;
	}
	/**
	 * Restituisce l'etichetta attuale.
	 *
	 * @return l'etichetta
	 */
	public String getLabel() {
		return currentLabel;
	}

	/**
	 * Ferma il robot azzerando velocità e direzione.
	 */
	public void stop() {
		this.dir = new Direction(0, 0);
		this.speed = 0;
	}

	/**
	 * Prosegue il movimento mantenendo velocità e direzione correnti.
	 */
	public void continueMovement() {
		this.move(speed, dir);
	}

	@Override
	public String toString() {
		return "Robot in posizione " + position;
	}
}

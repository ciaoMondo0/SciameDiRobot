package it.unicam.cs.followme.entity;

import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;

/**
 * Rappresenta un robot con una posizione, direzione e velocità.
 */
public class Robot {

	private Coordinates position;
	private Direction dir;
	private double speed;
	private final List<String> signalLabels = new ArrayList<>();
	private String currentLabel;

	/**
	 * Costruisce un robot a partire dalle coordinate date.
	 *
	 * @param position la posizione iniziale del robot
	 */
	public Robot(Coordinates position) {
		this.position = position;
	}

	/**
	 * Costruisce un robot con posizione, direzione e velocità iniziali.
	 *
	 * @param position    la posizione iniziale
	 * @param dir         la direzione iniziale
	 * @param speed       la velocità iniziale
	 * @param signalLabel etichetta iniziale di segnalazione
	 */
	public Robot(Coordinates position, Direction dir, double speed, String signalLabel) {
		this.position = position;
		this.dir = dir;
		this.currentLabel = signalLabel;
	}

	/**
	 * Muove il robot aggiornando la posizione in base alla direzione e velocità.
	 *
	 * @param speed     la velocità del movimento
	 * @param direction la direzione del movimento
	 */
	public void move(double speed, Direction direction) {
		this.dir = new Direction(direction.getX(), direction.getY());
		this.speed = speed;
		this.position.setX(direction.getX() * speed + this.position.getX());
		this.position.setY(direction.getY() * speed + this.position.getY());
	}

	/**
	 * Imposta il segnale corrente.
	 *
	 * @param label il segnale da impostare
	 */
	public void signals(String label) {
		this.currentLabel = label;
	}

	/**
	 * Rimuove il segnale corrente se corrisponde a quello passato.
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
	 * Restituisce la posizione del robot.
	 *
	 * @return le coordinate attuali
	 */
	public Coordinates getPosition() {
		return position;
	}

	/**
	 * Imposta la posizione del robot.
	 *
	 * @param position le nuove coordinate
	 */
	public void setPosition(Coordinates position) {
		this.position = position;
	}

	/**
	 * Restituisce l'etichetta corrente del robot.
	 *
	 * @return l'etichetta corrente
	 */
	public String getLabel() {
		return this.currentLabel;
	}

	/**
	 * Restituisce la velocità attuale.
	 *
	 * @return la velocità
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Restituisce la direzione attuale.
	 *
	 * @return la direzione
	 */
	public Direction getDirection() {
		return dir;
	}

	/**
	 * Imposta la direzione del robot.
	 *
	 * @param direction la nuova direzione
	 */
	public void setDirection(Direction direction) {
		this.dir = direction;
	}

	/**
	 * Ferma il robot azzerando direzione e velocità.
	 */
	public void stop() {
		this.dir = new Direction(0, 0);
		this.speed = 0;
	}

	/**
	 * Continua il movimento del robot mantenendo velocità e direzione.
	 */
	public void continueMovement() {
		this.move(speed, dir);
	}

	@Override
	public String toString() {
		return "Robot in posizione " + position;
	}
}

package it.unicam.cs.followme.commands;

import java.util.List;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.space.Coordinates;

/**
 * Comando che controlla se il robot si trova all'interno di un'area specifica.
 * Se il robot è contenuto nell'area identificata dall'etichetta, il flusso dei comandi salta
 * all'indice specificato (jumpIndex); altrimenti, salta al comando successivo (nextIndex).
 */
public class UntilCommand implements Command<Robot>, LoopInstructions {

	private final String label;
	private final List<Area> shapes;
	private final int jumpIndex;
	private int nextIndex;

	/**
	 * Costruttore.
	 *
	 * @param label     l'etichetta dell'area da verificare
	 * @param jumpIndex l'indice a cui saltare se il robot è all'interno dell'area
	 * @param shapes    la lista delle aree disponibili
	 */
	public UntilCommand(String label, int jumpIndex, List<Area> shapes) {
		this.label = label;
		this.jumpIndex = jumpIndex;
		this.shapes = shapes;
	}

	@Override
	public synchronized void execute(Robot robot) {
		System.out.println("Until " + label + " at position " + robot.getPosition());
		// Cerca l'area corrispondente all'etichetta (ignorando le differenze di maiuscole/minuscole)
		Area area = shapes.stream()
				.filter(s -> s.getLabel().equalsIgnoreCase(this.label))
				.findFirst()
				.orElse(null);

		if (area != null && checkInsideArea(area, robot)) {
			robot.getProgramExecutor().jumpTo(jumpIndex);
		} else {
			robot.getProgramExecutor().jumpTo(nextIndex);
		}
		robot.getProgramExecutor().increment();
	}

	/**
	 * Verifica se il robot è all'interno dell'area specificata.
	 *
	 * @param area  l'area da verificare
	 * @param robot il robot da controllare
	 * @return {@code true} se il robot è contenuto all'interno dell'area, {@code false} altrimenti
	 */
	private boolean checkInsideArea(Area area, Robot robot) {
		return area.containsRobot(robot.getPosition());
	}

	@Override
	public synchronized void setEndIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	@Override
	public synchronized int getJump() {
		return jumpIndex;
	}
}

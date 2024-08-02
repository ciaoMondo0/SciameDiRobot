package it.unicam.cs.followme.shapes;

import it.unicam.cs.followme.space.Coordinates;

public interface Area {
	
	boolean containsRobot(Coordinates coordinates); 
	String getLabel();
	Coordinates getCoordinates();
	

}

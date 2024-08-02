package it.unicam.cs.followme.list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import it.unicam.cs.followme.commands.Command;
import it.unicam.cs.followme.commands.FollowCommand;
import it.unicam.cs.followme.commands.MoveCommand;
import it.unicam.cs.followme.commands.UntilCommand;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Circle;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.space.Coordinates;
import it.unicam.cs.followme.space.Direction;

public class RobotTest {

	private Robot robot;
	private Direction directions;
	private Command commands;

	
	
	@BeforeEach
	public void setUp() {
		robot = new Robot(new Coordinates(10, 20));
	}

	@Test
	public void MoveRobot() throws InterruptedException {
		Robot robot = new Robot(new Coordinates(0, 0));
		ProgramExecutor programExecution = new ProgramExecutor();

		Direction dir = new Direction(1, 0); // Move right
		double speed = 1.0;
		MoveCommand moveCommand = new MoveCommand(dir, speed);

		moveCommand.execute(robot, programExecution);

		assertEquals(1.0, robot.getPosition().getX(), 0.01); // Check X coordinate
		assertEquals(0.0, robot.getPosition().getY(), 0.01);
	}

	@Test
	public void Stop() {
		robot.move(5.0, new Direction(1, 1));
		robot.stop();
		assertEquals(robot.getSpeed(), 0);

	}



	@Test
	public void UntilTest() {
		Circle circle = new Circle(0, 0, 100, "Circle");

		Robot robot = new Robot(new Coordinates(150, 0)); // Coordinates outside the circle

		List<Area> shapes = new ArrayList<>();
		shapes.add(circle);

		UntilCommand untilCommand = new UntilCommand("Circle", 0, shapes);

		Direction moveDirection = new Direction(-1, 0); // Move towards the circle
		double moveSpeed = 1.0;
		MoveCommand moveCommand = new MoveCommand(moveDirection, moveSpeed);

		ProgramExecutor programExecution = new ProgramExecutor();
		while (!circle.containsRobot(robot.getPosition())) {
			moveCommand.execute(robot, programExecution);
			untilCommand.execute(robot, programExecution);
		}

		assertTrue(circle.containsRobot(robot.getPosition()));
	}

}

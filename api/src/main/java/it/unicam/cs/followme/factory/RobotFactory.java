package it.unicam.cs.followme.factory;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.space.Coordinates;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utilità factory per creare una lista di robot.
 */
public class RobotFactory {

    public static List<Robot> createRobots(int count, ProgramExecutor<Robot> executor) {
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    Robot robot = new Robot(Coordinates.generateRandomCoordinates());
                    robot.setProgramExecutor(executor);
                    return robot;
                })
                .collect(Collectors.toList());
    }
}

package it.unicam.cs.followme.factory;

import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.shapes.Area;
import it.unicam.cs.followme.simulator.ParsingCommands;
import it.unicam.cs.followme.simulator.ProgramExecutor;
import it.unicam.cs.followme.simulator.RobotSwarm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Builder {
    public int robotCount = 10;
    public ExecutorService executor;
    public ProgramExecutor<Robot> programExecutor;
    public ParsingCommands commandParser;
    public List<Area> shapes = new ArrayList<>();

    public Builder withRobotCount(int count) {
        this.robotCount = count;
        return this;
    }

    public Builder withExecutor(ExecutorService executor) {
        this.executor = executor;
        return this;
    }

    public Builder withProgramExecutor(ProgramExecutor<Robot> programExecutor) {
        this.programExecutor = programExecutor;
        return this;
    }

    public Builder withCommandParser(ParsingCommands parser) {
        this.commandParser = parser;
        return this;
    }

    public Builder withShapes(List<Area> shapes) {
        this.shapes = shapes;
        return this;
    }

    public RobotSwarm build() {
        if (executor == null) {
            throw new IllegalStateException("ExecutorService must be provided.");
        }
        if (commandParser == null) {
            throw new IllegalStateException("A CommandParser must be provided.");
        }
        return new RobotSwarm(this);
    }
}

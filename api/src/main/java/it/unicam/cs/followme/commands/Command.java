package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.*;
import it.unicam.cs.followme.simulator.*;

public interface Command {
      void execute(Robot robot, ProgramExecutor programExecution);

}

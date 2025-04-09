package it.unicam.cs.followme.commands;

import it.unicam.cs.followme.entity.Robot;

/**
 * Interfaccia per i comandi eseguiti sui robot.
 *
 * @param <R> il tipo di robot su cui il comando opera
 */
public interface Command<R extends Robot> {
      /**
       * Esegue il comando sul robot.
       *
       * @param robot il robot su cui eseguire il comando
       */
      void execute(R robot);
}

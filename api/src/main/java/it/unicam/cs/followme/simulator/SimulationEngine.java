package it.unicam.cs.followme.simulator;

import it.unicam.cs.followme.entity.Robot;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Esegue il ciclo di simulazione utilizzando l'executor fornito.
 */
public class SimulationEngine {

    private final ExecutorService executor;

    public SimulationEngine(ExecutorService executor) {
        this.executor = executor;
    }

    /**
     * Esegue i passi della simulazione fino al raggiungimento del tempo totale di simulazione.
     *
     * @param robots           la lista dei robot da simulare
     * @param programExecutor  il program executor che controlla il flusso dei comandi
     * @param timeStepSeconds  l'intervallo di tempo della simulazione (in secondi)
     * @param totalTimeSeconds il tempo totale della simulazione (in secondi)
     * @throws InterruptedException se la simulazione viene interrotta
     */
    public void run(List<Robot> robots, ProgramExecutor<Robot> programExecutor, double timeStepSeconds, double totalTimeSeconds)
            throws InterruptedException {
        double elapsedTime = 0.0;
        while (elapsedTime < totalTimeSeconds) {
            List<Callable<Void>> tasks = robots.stream()
                    .map(robot -> (Callable<Void>) () -> {
                        programExecutor.executeCommand(robot);
                        return null;
                    })
                    .toList();

            executor.invokeAll(tasks);
            // Pausa per l'intervallo di tempo della simulazione
            TimeUnit.MILLISECONDS.sleep((long) (timeStepSeconds * 1000));
            elapsedTime += timeStepSeconds;
        }
    }
}

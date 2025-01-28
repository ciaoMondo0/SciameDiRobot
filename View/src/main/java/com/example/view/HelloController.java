package com.example.view;

import it.unicam.cs.followme.simulator.RobotSwarm;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.management.timer.Timer;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class HelloController {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @FXML
    private Spinner<Integer> botCountSpinner;

    @FXML
    private Pane simulationPane;

    @FXML
    private Button startSimulationButton;



    private RobotSwarm robotSwarm;

    @FXML
    public void initialize() {
        botCountSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2));

        startSimulationButton.setOnAction(event -> startSimulation());
    }

    private void startSimulation() {
        int botCount = botCountSpinner.getValue();
        robotSwarm = new RobotSwarm(botCount);

        new Thread(() -> {
            try {
                robotSwarm.simulate(1, 50); // Modifica i parametri di simulazione se necessario
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Aggiorna la posizione dei robot nell'interfaccia grafica
        updateRobotPositions(simulationPane);

        // Mostra il pulsante per caricare i comandi dopo aver avviato la simulazione
        Platform.runLater(() -> {
            Button loadCommandsButton = new Button("Carica Comandi");

            loadCommandsButton.setLayoutX(10); // Posizione X
            loadCommandsButton.setLayoutY(10); // Posizione Y
            loadCommandsButton.setOnAction(event -> loadFile("Seleziona il file dei comandi", true));

            simulationPane.getChildren().add(loadCommandsButton);
            loadCommandsButton.toFront(); // Porta il pulsante in primo piano
            System.out.println("Pulsante 'Carica Comandi' aggiunto alla UI.");
        });
    }

    private void loadFile(String title, boolean isCommandFile) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                    robotSwarm.executeInstructions(selectedFile);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateRobotPositions(Pane pane) {
        AnimationTimer timer  = new AnimationTimer() {
            @Override
            public void handle(long now) {
                simulationPane.getChildren().removeIf(node -> node instanceof Circle || node instanceof Text);
                robotSwarm.getRobots().forEach(robot -> {
                    int id = 0;
                    double normalizedX = (robot.getPosition().getX() + 1) * WIDTH / 2;
                    double normalizedY = (robot.getPosition().getY() + 1) * HEIGHT / 2;

                   Circle circle = new javafx.scene.shape.Circle(normalizedX, normalizedY, 5, javafx.scene.paint.Color.BLUE);
                    simulationPane.getChildren().add(circle);

                   Text text = new javafx.scene.text.Text(normalizedX + 10, normalizedY, "ID: " + ( id + 1)  + " Label: " + robot.getLabel());
                    simulationPane.getChildren().add(text);
                });
            }
        };
        timer.start();
    }
}
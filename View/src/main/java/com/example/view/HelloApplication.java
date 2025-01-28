package com.example.view;

import it.unicam.cs.followme.simulator.RobotSwarm;
import it.unicam.cs.followme.entity.Robot;
import it.unicam.cs.followme.space.Coordinates;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private RobotSwarm robotSwarm;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carica il file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            VBox root = loader.load();

            // Ottieni il controller associato
            HelloController controller = loader.getController();

            // Configura il comportamento per i pulsanti di caricamento file e avvio simulazione
            configureControllerActions(controller);

            // Configura e mostra la scena
            Scene scene = new Scene(root, WIDTH, HEIGHT + 100); // Altezza extra per i bottoni
            primaryStage.setTitle("Simulazione Robot");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureControllerActions(HelloController controller) {
        // Pulsante per avviare la simulazione
        controller.setStartSimulationAction(botCount -> {
            robotSwarm = new RobotSwarm(botCount); // Inizializza RobotSwarm con il numero di robot scelto
            startSimulation(controller); // Avvia la simulazione e aggiorna la UI
        });
    }

    private void loadFile(String title, boolean isCommandsFile) {
        if (robotSwarm == null) {
            System.out.println("RobotSwarm non è stato inizializzato. Avvia prima la simulazione.");
            return; // Esci dal metodo se robotSwarm non è inizializzato
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                    robotSwarm.executeInstructions(selectedFile); // Passa solo il file comandi

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startSimulation(HelloController controller) {
        new Thread(() -> {
            try {
                robotSwarm.simulate(1, 50); // Modifica i parametri di simulazione se necessario
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Aggiorna la posizione dei robot nell'interfaccia grafica
        updateRobotPositions(controller.getSimulationPane());

        // Mostra il pulsante per caricare i comandi dopo aver avviato la simulazione
        Platform.runLater(() -> {
            Pane buttonContainer = controller.getSimulationPane();
            Button loadCommandsButton = new Button("Carica Comandi");

            loadCommandsButton.setLayoutX(10); // Posizione X
            loadCommandsButton.setLayoutY(10); // Posizione Y
            loadCommandsButton.setOnAction(event -> loadFile("Seleziona il file dei comandi", true));

            buttonContainer.getChildren().add(loadCommandsButton);
            loadCommandsButton.toFront(); // Porta il pulsante in primo piano
            System.out.println("Pulsante 'Carica Comandi' aggiunto alla UI.");
        });
    }

    private void updateRobotPositions(Pane simulationPane) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                simulationPane.getChildren().removeIf(node -> node instanceof Circle || node instanceof Text); // Rimuovi solo cerchi e testi
                List<Robot> robots = robotSwarm.getRobots();
                for (int i = 0; i < robots.size(); i++) {
                    Robot robot = robots.get(i);
                    Coordinates position = robot.getPosition();

                    // Normalizza le coordinate per mantenerle all'interno dello schermo
                    double normalizedX = (position.getX() + 1) * WIDTH / 2;
                    double normalizedY = (position.getY() + 1) * HEIGHT / 2;

                    // Crea il cerchio rappresentante il robot
                    Circle circle = new Circle(normalizedX, normalizedY, 5, Color.BLUE);
                    simulationPane.getChildren().add(circle);

                    String label = robot.getLabel();
                    // Aggiungi il testo con l'ID del robot
                    Text text = new Text(normalizedX + 10, normalizedY, "ID: " + (i + 1) + " Label: " + label);

                    simulationPane.getChildren().add(text);
                }
            }
        };
        timer.start();
    }
    public static void main(String[] args) {
        launch();
    }
}


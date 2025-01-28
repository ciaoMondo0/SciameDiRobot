package com.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public class HelloController {

    @FXML
    private Spinner<Integer> botCountSpinner;

    @FXML
    private Pane simulationPane;

    @FXML
    private Button startSimulationButton;

    @FXML

    private Button loadInstructions;

    private Consumer<Integer> startSimulationAction;

    @FXML
    public void initialize() {
        // Initialize the Spinner with default values
        botCountSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2));

        // Set button action to trigger simulation
        startSimulationButton.setOnAction(event -> {
            if (startSimulationAction != null) {
                startSimulationAction.accept(botCountSpinner.getValue());
            }
        });
    }

    public void setStartSimulationAction(Consumer<Integer> action) {
        this.startSimulationAction = action;
    }

    public Pane getSimulationPane() {
        return simulationPane;
    }
}

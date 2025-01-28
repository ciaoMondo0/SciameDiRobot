package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carica il file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            VBox root = loader.load();

            // Configura e mostra la scena
            Scene scene = new Scene(root, WIDTH, HEIGHT + 100); // Altezza extra per i bottoni
            primaryStage.setTitle("Simulazione Robot");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
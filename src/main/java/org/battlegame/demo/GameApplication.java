package org.battlegame.demo;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    /**
     * Clase punto de entrada, desde aquí llega el Stage principal y se le configura usando la clase Launcher
     * @param stage Ventana principal inicial del programa
     * @throws IOException Errores de lectura etc etc
     */
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane borderPane = new BorderPane();

        Launcher launcher = new Launcher(stage, borderPane);

        launcher.preRun();
        launcher.run();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
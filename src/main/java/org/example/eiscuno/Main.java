package org.example.eiscuno;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.eiscuno.view.GameUnoStage;
import java.io.IOException;

/**
 * Main class for the EISC Uno application.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class Main extends Application {

    /**
     * Main method to launch the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application.
     *
     * @param primaryStage the primary stage for the application.
     * @throws IOException if an error occurs while loading the stage.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        GameUnoStage.getInstance();
    }
}

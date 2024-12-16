package org.example.eiscuno.model.machine;

import javafx.application.Platform;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;
import java.io.IOException;

/**
 * This class represents a thread that continuously checks the game state to determine if the game has ended.
 * It periodically checks whether the human player or the machine has won and calls the appropriate method
 * in the GameUnoController to handle victory or defeat.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class ThreadEndGame extends Thread {

    private GameUnoController gameUnoController;
    private GameUno gameUno;

    /**
     * Constructs a new ThreadEndGame instance.
     *
     * @param gameUnoController the controller responsible for managing the game state and UI updates
     * @param gameUno the object containing the game logic
     */
    public ThreadEndGame(GameUnoController gameUnoController, GameUno gameUno) {
        this.gameUnoController = gameUnoController;
        this.gameUno = gameUno;
    }

    /**
     * Contains the logic for continuously checking the game state.
     * Sleeps for 500 milliseconds between each check to avoid excessive CPU usage.
     * If a win condition is detected, calls the corresponding method in GameUnoController
     * using Platform.runLater to ensure UI updates occur on the JavaFX application thread.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                // Checks if the human player has won.
                if (gameUno.didHumanWin()) {
                    Platform.runLater(() -> {
                        try {
                            gameUnoController.win(); // Calls win() method to handle victory.
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                // Checks if the machine has won.
                else if (gameUno.didMachineWin()) {
                    Platform.runLater(() -> {
                        try {
                            gameUnoController.lose(); // Calls lose() method to handle defeat.
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the GameUnoController instance for this thread.
     *
     * @param gameUnoController the controller to set
     */
    public void setGameUnoController(GameUnoController gameUnoController) {
        this.gameUnoController = gameUnoController;
    }

    /**
     * Sets the GameUno instance for this thread.
     *
     * @param gameUno the game logic object to set
     */
    public void setGameUno(GameUno gameUno) {
        this.gameUno = gameUno;
    }
}

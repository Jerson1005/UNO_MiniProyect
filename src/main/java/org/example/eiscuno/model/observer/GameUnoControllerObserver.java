package org.example.eiscuno.model.observer;

import javafx.application.Platform;
import org.example.eiscuno.controller.GameUnoController;

/**
 * Represents an observer that listens to events from the Uno game.
 * Updates the corresponding methods in GameUnoController using JavaFX's Platform
 * to ensure updates occur on the UI thread.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class GameUnoControllerObserver implements EventListener {

    /**
     * Controller responsible for managing the game logic and user interface interactions.
     * It facilitates communication between the game model and the graphical interface.
     *
     * @serial
     * @since 1.0
     * @see GameUnoController
     */
    private GameUnoController gameUnoController;

    /**
     * Updates the player turn state in the associated GameUnoController.
     *
     * @param playerHasPlayed {@code true} if the player has played, {@code false} otherwise.
     */
    @Override
    public void updatePlayerTurn(boolean playerHasPlayed) {
        gameUnoController.setPlayerHasPlayed(playerHasPlayed);
    }

    /**
     * Updates the machine player's cards in the associated GameUnoController.
     * Uses Platform.runLater() to ensure the UI update is done on the JavaFX main thread.
     */
    @Override
    public void updateCardsMachinePlayer() {
        Platform.runLater(() -> gameUnoController.printCardsMachinePlayer());
    }

    /**
     * Updates the human player's cards in the associated GameUnoController.
     * Uses Platform.runLater() to ensure the UI update is done on the JavaFX main thread.
     */
    @Override
    public void updateCardsHumanPlayer() {
        Platform.runLater(() -> gameUnoController.printCardsHumanPlayer());
    }

    /**
     * Sets the instance of GameUnoController for this observer.
     *
     * @param gameUnoController the GameUnoController instance to set.
     */
    public void setGameUnoController(GameUnoController gameUnoController) {
        this.gameUnoController = gameUnoController;
    }
}

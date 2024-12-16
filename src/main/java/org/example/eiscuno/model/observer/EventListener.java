package org.example.eiscuno.model.observer;

/**
 * This interface represents an event listener for the Uno game.
 * It defines methods for handling various game events, such as player turns and card updates.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public interface EventListener {

    /**
     * Called to update the listener about the player's turn state.
     *
     * @param playerHasPlayed {@code true} if the player has played, {@code false} otherwise.
     */
    void updatePlayerTurn(boolean playerHasPlayed);

    /**
     * Called to update the listener about the machine player's cards.
     */
    void updateCardsMachinePlayer();

    /**
     * Called to update the listener about the human player's cards.
     */
    void updateCardsHumanPlayer();
}

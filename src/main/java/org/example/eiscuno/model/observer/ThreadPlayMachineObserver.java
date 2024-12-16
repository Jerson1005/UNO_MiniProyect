package org.example.eiscuno.model.observer;

import org.example.eiscuno.model.machine.ThreadPlayMachine;

/**
 * Represents an observer for the ThreadPlayMachine class.
 * This observer listens to events related to the players' turns and updates the ThreadPlayMachine accordingly.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class ThreadPlayMachineObserver implements EventListener {

    private ThreadPlayMachine threadPlayMachine;

    /**
     * Updates the ThreadPlayMachine when the player turn state changes.
     *
     * @param playerHasPlayed {@code true} if the player has played a card, {@code false} otherwise.
     */
    @Override
    public void updatePlayerTurn(boolean playerHasPlayed) {
        threadPlayMachine.setHasPlayerPlayed(playerHasPlayed);
    }

    /**
     * Updates the observer with new cards for the machine player.
     * This method is not implemented as it is not necessary in this observer.
     */
    @Override
    public void updateCardsMachinePlayer() {
        // No action needed for this method in the context of ThreadPlayMachineObserver.
    }

    /**
     * Updates the observer with new cards for the human player.
     * This method is not implemented as it is not necessary in this observer.
     */
    @Override
    public void updateCardsHumanPlayer() {
        // No action needed for this method in the context of ThreadPlayMachineObserver.
    }

    /**
     * Sets the instance of ThreadPlayMachine for this observer.
     *
     * @param threadPlayMachine the ThreadPlayMachine instance to set.
     */
    public void setThreadPlayMachine(ThreadPlayMachine threadPlayMachine) {
        this.threadPlayMachine = threadPlayMachine;
    }
}

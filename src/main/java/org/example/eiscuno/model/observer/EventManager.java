package org.example.eiscuno.model.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the event listeners and notifies them about the Uno game events.
 * It allows adding and removing listeners, as well as notifying them about player turns
 * and updates to the human and machine player's cards.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class EventManager {

    /**
     * List of event listeners registered to observe and handle events.
     * Each listener in the list will respond to specific events triggered during the game.
     *
     * @serial
     * @since 1.0
     * @see EventListener
     */
    private List<EventListener> listeners = new ArrayList<>();


    /**
     * Adds an event listener to be notified about game events.
     *
     * @param eventListener The listener to be added.
     */
    public void addListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    /**
     * Removes an event listener from the list of listeners.
     *
     * @param eventListener The listener to be removed.
     */
    public void removeListener(EventListener eventListener) {
        listeners.remove(eventListener);
    }

    /**
     * Notifies all registered listeners about the player turn update.
     *
     * @param playerHasPlayed {@code true} if the player has played, {@code false} otherwise.
     */
    public void notifyListenersPlayerTurnUpdate(boolean playerHasPlayed) {
        for (EventListener eventListener : listeners) {
            eventListener.updatePlayerTurn(playerHasPlayed);
        }
    }

    /**
     * Notifies all registered listeners about the update to the human player's cards.
     */
    public void notifyListenersCardsHumanPlayerUpdate() {
        for (EventListener eventListener : listeners) {
            eventListener.updateCardsHumanPlayer();
        }
    }

    /**
     * Notifies all registered listeners about the update to the machine player's cards.
     */
    public void notifyListenersCardsMachinePlayerUpdate() {
        for (EventListener eventListener : listeners) {
            eventListener.updateCardsMachinePlayer();
        }
    }
}

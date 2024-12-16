package org.example.eiscuno.model.player;

import org.example.eiscuno.model.card.Card;
import java.util.ArrayList;

/**
 * Interface representing a player in the Uno game.
 * Provides methods to interact with the player's hand of cards.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public interface IPlayer {

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to be added to the player's hand.
     */
    void addCard(Card card);

    /**
     * Retrieves a card from the player's hand based on its index.
     *
     * @param index The index of the card to retrieve.
     * @return The card at the specified index in the player's hand.
     */
    Card getCard(int index);

    /**
     * Retrieves all the cards currently in the player's hand.
     *
     * @return An ArrayList containing all the cards in the player's hand.
     */
    ArrayList<Card> getCardsPlayer();

    /**
     * Removes a card from the player's hand based on its index.
     *
     * @param index The index of the card to remove.
     */
    void removeCard(int index);
}

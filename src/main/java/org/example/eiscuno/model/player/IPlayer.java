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

    /**
     * Sets the protected status of the player regarding the "UNO" state.
     * This method is used to mark whether the player is protected after declaring "UNO."
     *
     * @param protectedByUno {@code true} if the player is protected, {@code false} otherwise.
     * @since 1.0
     */
    void setProtectedByUno(boolean protectedByUno);

    /**
     * Checks whether the player is protected by the "UNO" state.
     * This indicates if the player has successfully declared "UNO" when they had only one card.
     *
     * @return {@code true} if the player is protected by "UNO," {@code false} otherwise.
     * @since 1.0
     */
    boolean isProtectedByUno();

}

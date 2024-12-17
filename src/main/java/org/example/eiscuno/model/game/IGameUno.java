package org.example.eiscuno.model.game;

import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.exception.UnoException;

/**
 * Interface that represents the functionality of the Uno game.
 * Defines the main functions required to run the game.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public interface IGameUno {

    /**
     * Sets the current color of the table card.
     * @param Color the color to set.
     */
    void SetCurrentTableCardColor(String Color);

    /**
     * Retrieves the deck of cards used in the game.
     * @return the deck of cards.
     */
    Deck getDeck();

    /**
     * Refills the deck of cards when empty.
     */
    void refillDeckOfCards();

    /**
     * Takes a card for the specified player.
     * @param playerWhoTakes the identifier of the player who takes the card.
     */
    void takeCardPlayer(String playerWhoTakes);

    /**
     * Gets a random color from the set of colors.
     * @return a random color.
     */
    String getRandomColor();

    /**
     * Checks if the given card can be played on the current card.
     * @param card the card to check.
     * @param currentCard the current card on the table.
     * @return true if the card can be played, false otherwise.
     */
    boolean cardCanBePlayed(Card card, Card currentCard);

    /**
     * Makes the specified player eat a number of cards.
     * @param playerWhoEats the player who will eat the cards.
     * @param numberOfCards the number of cards to be eaten.
     */
    void eatCard(String playerWhoEats, int numberOfCards);

    /**
     * Checks if the given card is a numeric card.
     * @param card the card to check.
     * @return true if the card is numeric, false otherwise.
     */
    boolean isNumericCard(Card card);

    /**
     * Initializes the start card for the game.
     */
    void initializeStartCard();

    /**
     * Starts the Uno game.
     */
    void startGame();

    /**
     * Plays a card in the game, adding it to the table.
     * @param card the card to play.
     * @param playerWhoPlays the identifier of the player who plays the card.
     * @throws UnoException if an error occurs while playing the card.
     */
    void playCard(Card card, String playerWhoPlays) throws UnoException;

    /**
     * Handles the action when a player shouts "UNO".
     * @param playerWhoSang the identifier of the player who sang "UNO".
     */
    void haveSungOne(String playerWhoSang);

    /**
     * Retrieves the current visible cards for the human player starting from a specified position.
     * @param posInitCardToShow the initial position to start showing cards.
     * @return an array of visible cards for the human player.
     */
    Card[] getCurrentVisibleCardsHumanPlayer(int posInitCardToShow);

    /**
     * Retrieves the current visible cards for the machine player.
     * @return an array of visible cards for the machine player.
     */
    Card[] getCurrentVisibleCardsMachinePlayer();

    /**
     * Checks if the human player won the game.
     * @return true if the human player won, false otherwise.
     */
    Boolean didHumanWin();

    /**
     * Checks if the machine player won the game.
     * @return true if the machine player won, false otherwise.
     */
    Boolean didMachineWin();
}

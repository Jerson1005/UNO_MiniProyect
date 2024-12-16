package org.example.eiscuno.model.table;

import org.example.eiscuno.model.card.Card;
import java.util.ArrayList;

/**
 * Represents the table in the Uno game where cards are played.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class Table {

    private ArrayList<Card> cardsTable;

    /**
     * Constructs a new Table object with no cards.
     */
    public Table() {
        this.cardsTable = new ArrayList<Card>();
    }

    /**
     * Adds a card to the table.
     *
     * @param card The card to be added to the table.
     */
    public void addCardOnTheTable(Card card) {
        this.cardsTable.add(card);
    }

    /**
     * Retrieves the current card on the table.
     *
     * @return The current card on the table.
     * @throws IndexOutOfBoundsException if there are no cards on the table.
     */
    public Card getCurrentCardOnTheTable() throws IndexOutOfBoundsException {
        if (cardsTable.isEmpty()) {
            throw new IndexOutOfBoundsException("There are no cards on the table.");
        }
        return this.cardsTable.get(this.cardsTable.size() - 1);
    }

    /**
     * Checks if the table has no cards.
     *
     * @return true if there are no cards on the table, false otherwise.
     */
    public boolean isEmpty() {
        return cardsTable.isEmpty();
    }

    /**
     * Retrieves all the cards on the table.
     *
     * @return A list of all the cards currently on the table.
     */
    public ArrayList<Card> getCardsTable() {
        return cardsTable;
    }

    /**
     * Sets the initial card on the table.
     *
     * @param card The card to be placed as the starting card on the table.
     */
    public void setStartCard(Card card) {
        this.cardsTable.add(card);
    }
}

package org.example.eiscuno.model.game;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.exception.UnoException;
import org.example.eiscuno.model.observer.EventManager;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;

import java.util.ArrayList;
import java.util.Random;

/**
 * The GameUno class manages the core logic for the Uno game.
 * It handles the game setup, turns, card effects, and win/lose conditions.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class GameUno implements IGameUno {

    /**
     * Manages events and notifies listeners about game state updates.
     *
     * @serial
     * @since 1.0
     */
    private EventManager eventManager;

    /**
     * Represents the human player in the game.
     * This player interacts with the game interface and makes moves manually.
     *
     * @serial
     * @since 1.0
     */
    private Player humanPlayer;

    /**
     * Represents the machine (AI) player in the game.
     * This player makes automated moves based on game logic.
     *
     * @serial
     * @since 1.0
     */
    private Player machinePlayer;

    /**
     * The deck of cards used in the game.
     * This object holds and manages the cards to be played or drawn.
     *
     * @serial
     * @since 1.0
     * @see Deck
     */
    private Deck deck;

    /**
     * Represents the table where cards are played during the game.
     * This includes the current card on the table.
     *
     * @serial
     * @since 1.0
     * @see Table
     */
    private Table table;

    /**
     * The current color of the card on the table.
     * This is updated whenever a special card (e.g., WILD) sets a new color.
     *
     * @serial
     * @since 1.0
     */
    private String currentTableCardColor;

    /**
     * The controller responsible for managing the game's interface and user interactions.
     * It links the model with the view.
     *
     * @serial
     * @since 1.0
     * @see GameUnoController
     */
    private GameUnoController controller;


    /**
     * Constructs a GameUno instance, initializing the players, deck, table, and event manager.
     *
     * @param eventManager  The event manager for notifying game events.
     * @param humanPlayer   The human player.
     * @param machinePlayer The machine player.
     * @param deck          The deck of cards used in the game.
     * @param table         The table where cards are played.
     * @param controller    The game controller managing the UI interactions.
     * @since 1.0
     */
    public GameUno(EventManager eventManager, Player humanPlayer, Player machinePlayer, Deck deck, Table table, GameUnoController controller) {
        this.eventManager = eventManager;
        this.humanPlayer = humanPlayer;
        this.machinePlayer = machinePlayer;
        this.deck = deck;
        this.table = table;
        currentTableCardColor = null;
        this.controller = controller;
    }

    /**
     * Initializes the starting card on the table, ensuring it is numeric (0-9).
     * If the table is empty, the first numeric card is drawn from the deck.
     *
     * @since 1.0
     */
    @Override
    public void initializeStartCard() {
        if (table.isEmpty()) {
            Card startCard;
            do {
                startCard = deck.takeCard();
            } while (!isNumericCard(startCard));
            table.addCardOnTheTable(startCard);
            currentTableCardColor = startCard.getColor();
            System.out.println("Carta inicial (numérica): " + startCard.getColor() + " " + startCard.getValue());
        }
    }

    /**
     * Verifies if a given card has a numeric value (0-9).
     *
     * @param card The card to verify.
     * @return {@code true} if the card is numeric, {@code false} otherwise.
     * @since 1.0
     */
    @Override
    public boolean isNumericCard(Card card) {
        String value = card.getValue();
        return value.matches("[0-9]");
    }

    /**
     * Starts the game by distributing cards to the human and machine players.
     * Each player initially receives 5 cards.
     *
     * @since 1.0
     */
    @Override
    public void startGame() {
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                humanPlayer.addCard(this.deck.takeCard());
            } else {
                machinePlayer.addCard(this.deck.takeCard());
            }
        }
    }

    /**
     * Distributes a specific number of cards to the specified player.
     *
     * @param playerWhoEats The player receiving the cards ("MACHINE_PLAYER" or "HUMAN_PLAYER").
     * @param numberOfCards The number of cards to distribute.
     * @since 1.0
     */
    @Override
    public void eatCard(String playerWhoEats, int numberOfCards) {
        if (deck.size() < numberOfCards) {
            refillDeckOfCards();
        }

        if (playerWhoEats.equals("MACHINE_PLAYER")) {
            for (int i = 0; i < numberOfCards; i++) {
                machinePlayer.addCard(this.deck.takeCard());
            }
        } else {
            for (int i = 0; i < numberOfCards; i++) {
                humanPlayer.addCard(this.deck.takeCard());
            }
        }
    }

    /**
     * Plays a card and places it on the table if it meets the game rules.
     *
     * @param card           The card to be played.
     * @param playerWhoPlays The player playing the card ("HUMAN_PLAYER" or "MACHINE_PLAYER").
     * @throws UnoException if the card cannot be placed on the table.
     * @since 1.0
     */
    @Override
    public void playCard(Card card, String playerWhoPlays) throws UnoException {
        if (!didHumanWin() && !didMachineWin()) {
            if (table.isEmpty()) {
                this.table.addCardOnTheTable(card);
                applySpecialCardEffect(card, playerWhoPlays);
            } else {
                Card currentCard = this.table.getCurrentCardOnTheTable();
                if (cardCanBePlayed(card, currentCard)) {
                    this.table.addCardOnTheTable(card);
                    applySpecialCardEffect(card, playerWhoPlays);
                } else {
                    throw new UnoException("Can't place this card");
                }
            }
        }
    }

    /**
     * Determines whether a card can be played based on its value or color.
     *
     * @param card        The card the player wants to play.
     * @param currentCard The current card on the table.
     * @return {@code true} if the card can be played; {@code false} otherwise.
     * @since 1.0
     */
    @Override
    public boolean cardCanBePlayed(Card card, Card currentCard) {
        return card.getColor().equals("NON_COLOR")
                || currentTableCardColor.equals("NON_COLOR")
                || card.getValue().equals(currentCard.getValue())
                || card.getColor().equals(currentTableCardColor != null ? currentTableCardColor : currentCard.getColor());
    }

    /**
     * Applies the special effect of the played card (e.g., "+2", "+4", "SKIP", "WILD").
     *
     * @param card           The card being played.
     * @param playerWhoPlays The player playing the card ("HUMAN_PLAYER" or "MACHINE_PLAYER").
     * @throws UnoException if an error occurs while applying the card's effect.
     * @since 1.0
     */
    public void applySpecialCardEffect(Card card, String playerWhoPlays) throws UnoException {
        String playerWhoEats;

        if (playerWhoPlays.equals("HUMAN_PLAYER")) {
            playerWhoEats = "MACHINE_PLAYER";

            switch (card.getValue()) {
                case "+2":
                    currentTableCardColor = card.getColor();
                    eatCard(playerWhoEats, 2);
                    eventManager.notifyListenersPlayerTurnUpdate(true);
                    eventManager.notifyListenersCardsMachinePlayerUpdate();
                    break;

                case "+4":
                    eatCard(playerWhoEats, 4);
                    eventManager.notifyListenersCardsMachinePlayerUpdate();
                    controller.showColorSelectionPanel();
                    break;

                case "SKIP":
                case "REVERSE":
                    currentTableCardColor = card.getColor();
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    break;

                case "WILD":
                    controller.showColorSelectionPanel();
                    break;

                default:
                    currentTableCardColor = card.getColor();
                    eventManager.notifyListenersPlayerTurnUpdate(true);
                    break;
            }
        } else {
            playerWhoEats = "HUMAN_PLAYER";

            switch (card.getValue()) {
                case "+2":
                    currentTableCardColor = card.getColor();
                    eatCard(playerWhoEats, 2);
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    eventManager.notifyListenersCardsHumanPlayerUpdate();
                    break;

                case "+4":
                    currentTableCardColor = getRandomColor();
                    controller.colorMaquina(currentTableCardColor);
                    eatCard(playerWhoEats, 4);
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    eventManager.notifyListenersCardsHumanPlayerUpdate();
                    break;

                case "SKIP":
                case "REVERSE":
                    currentTableCardColor = card.getColor();
                    eventManager.notifyListenersPlayerTurnUpdate(true);
                    break;

                case "WILD":
                    currentTableCardColor = getRandomColor();
                    controller.colorMaquina(currentTableCardColor);
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    break;

                default:
                    currentTableCardColor = card.getColor();
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    break;
            }
        }
    }

    /**
     * Generates a random color for the WILD or +4 cards.
     *
     * @return A randomly selected color: "RED", "BLUE", "GREEN", or "YELLOW".
     * @since 1.0
     */
    @Override
    public String getRandomColor() {
        String[] colors = {"RED", "BLUE", "GREEN", "YELLOW"};
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }

    /**
     * Handles the logic when a player calls "UNO".
     *
     * @param playerWhoSang The player who called "UNO".
     * @since 1.0
     */
    @Override
    public void haveSungOne(String playerWhoSang) {

        boolean machinePlayerProtectedByUno = this.machinePlayer.isProtectedByUno();

        boolean humanPlayerProtectedByUno = this.humanPlayer.isProtectedByUno();

        if (playerWhoSang.equals("HUMAN_PLAYER")) {
            System.out.println("Se cubrio");
            if (machinePlayer.getCardsPlayer().size() == 1 && !machinePlayerProtectedByUno) {

                machinePlayer.addCard(this.deck.takeCard());

                eventManager.notifyListenersCardsMachinePlayerUpdate();

            } else if (humanPlayer.getCardsPlayer().size() == 1) {

                this.humanPlayer.setProtectedByUno(true);

            } else {
                controller.setNotificationText("No puedes cantar UNO");
                System.out.println("Can't sing UNO.");
            }
        } else {

            if (!humanPlayerProtectedByUno) {
                System.out.println("No se cubrio");

                humanPlayer.addCard(this.deck.takeCard());

                eventManager.notifyListenersCardsHumanPlayerUpdate();
            }
        }
    }

    /**
     * Allows the specified player to draw a card from the deck.
     * Resets the "UNO" protection status of the player.
     *
     * @param playerWhoTakes The player drawing the card ("HUMAN_PLAYER" or "MACHINE_PLAYER").
     * @since 1.0
     */
    @Override
    public void takeCardPlayer(String playerWhoTakes) {

        if (playerWhoTakes.equals("HUMAN_PLAYER")) {

            this.humanPlayer.addCard(this.deck.takeCard());

            this.humanPlayer.setProtectedByUno(false);
        } else {

            this.machinePlayer.addCard(this.deck.takeCard());

            this.machinePlayer.setProtectedByUno(false);
        }
    }

    /**
     * Retrieves the visible cards of the human player starting from a specific position.
     *
     * @param posInitCardToShow The starting position of the cards to display.
     * @return An array of currently visible cards for the human player.
     * @since 1.0
     */
    @Override
    public Card[] getCurrentVisibleCardsHumanPlayer(int posInitCardToShow) {

        int totalCards = this.humanPlayer.getCardsPlayer().size();

        int numVisibleCards = Math.min(5, totalCards - posInitCardToShow);

        Card[] cards = new Card[numVisibleCards];

        for (int i = 0; i < numVisibleCards; i++) {

            cards[i] = this.humanPlayer.getCard(posInitCardToShow + i);

        }

        return cards;
    }

    /**
     * Retrieves the visible cards of the machine player.
     *
     * @return An array of currently visible cards for the machine player.
     * @since 1.0
     */
    @Override
    public Card[] getCurrentVisibleCardsMachinePlayer() {

        int totalCards = this.machinePlayer.getCardsPlayer().size();

        int numVisibleCards = Math.min(5, totalCards);

        Card[] cards = new Card[numVisibleCards];

        for (int i = 0; i < numVisibleCards; i++) {

            cards[i] = this.machinePlayer.getCard(i);

        }

        return cards;
    }

    /**
     * Checks whether the human player has won the game.
     *
     * @return {@code true} if the human player has no cards left; {@code false} otherwise.
     * @since 1.0
     */
    @Override
    public Boolean didHumanWin() {
        return humanPlayer.getCardsPlayer().isEmpty();
    }

    /**
     * Checks whether the machine player has won the game.
     *
     * @return {@code true} if the machine player has no cards left; {@code false} otherwise.
     * @since 1.0
     */
    @Override
    public Boolean didMachineWin() {
        return machinePlayer.getCardsPlayer().isEmpty();
    }

    /**
     * Refills the deck with cards from the table, excluding the last played card.
     *
     * @since 1.0
     */
    @Override
    public void refillDeckOfCards() {
        System.out.println("Deck has been refilled.");
        ArrayList<Card> allCardsInTable = table.getCardsTable();
        ArrayList<Card> allCardsInTableButLastOne = new ArrayList<>(allCardsInTable.subList(0, allCardsInTable.size() - 1));
        deck.refillDeck(allCardsInTableButLastOne);
    }

    /**
     * Retrieves the deck of cards currently used in the game.
     *
     * @return The deck of cards.
     * @since 1.0
     */
    @Override
    public Deck getDeck() {
        return deck;
    }

    /**
     * Sets the current color of the card on the table.
     *
     * @param Color The new color to set for the table card.
     * @since 1.0
     */
    @Override
    public void SetCurrentTableCardColor(String Color) {
        this.currentTableCardColor = Color;
    }
}

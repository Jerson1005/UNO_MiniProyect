package org.example.eiscuno.model.machine;

import javafx.scene.image.ImageView;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.exception.UnoException;
import org.example.eiscuno.model.observer.EventManager;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 *
 * This class manages the logic of the machine player in the game, running in a separate thread.
 */
public class ThreadPlayMachine extends Thread {

    /**
     * Manages and notifies event listeners within the UNO game.
     * It facilitates communication between components of the game logic.
     *
     * @serial
     * @since 1.0
     * @see EventManager
     */
    private EventManager eventManager;

    /**
     * Represents the main game logic of UNO.
     * This class controls the rules, player turns, and game flow.
     *
     * @serial
     * @since 1.0
     * @see GameUno
     */
    private GameUno gameUno;

    /**
     * Represents the machine player in the UNO game.
     * It holds the cards and manages the automated gameplay logic.
     *
     * @serial
     * @since 1.0
     * @see Player
     */
    private Player machinePlayer;

    /**
     * Displays the current card on the table in the game.
     * This image view shows the visual representation of the top card on the table.
     *
     * @serialField
     * @since 1.0
     * @see ImageView
     */
    private ImageView tableImageView;

    /**
     * Indicates whether the player has already played their turn.
     * This flag is used for synchronization in multi-threaded gameplay scenarios.
     *
     * @serial
     * @since 1.0
     * @see GameUnoController
     */
    private volatile boolean hasPlayerPlayed;

    /**
     * The controller that manages the interactions between the UI and the game logic.
     * It coordinates user inputs and updates the game state.
     *
     * @serial
     * @since 1.0
     * @see GameUnoController
     */
    private GameUnoController controller;


    /**
     * Constructs a new thread for machine player.
     * @param eventManager The event manager handling game events.
     * @param gameUno The game logic for Uno.
     * @param machinePlayer The machine player.
     * @param tableImageView The ImageView representing the current card on the table.
     * @param controller The controller for managing the game interface.
     */
    public ThreadPlayMachine(EventManager eventManager, GameUno gameUno, Player machinePlayer, ImageView tableImageView, GameUnoController controller) {
        this.eventManager = eventManager;
        this.gameUno = gameUno;
        this.machinePlayer = machinePlayer;
        this.tableImageView = tableImageView;
        this.controller = controller;
        this.hasPlayerPlayed = false;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (hasPlayerPlayed) {
                controller.setNotificationText("Turno de la máquina...");
                controller.setborderPane("-fx-border-color: transparent; -fx-border-width: 1px;", "-fx-border-color: #FFD700; -fx-border-width: 5px;");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                    return;
                }
                Random random = new Random();
                double probability = 0.2;
                boolean takeCard = random.nextDouble() < probability;

                if (takeCard) {
                    machineTakeCard();
                } else {
                    putCardOnTheTable();
                }

                eventManager.notifyListenersCardsMachinePlayerUpdate();
                controller.setNotificationText("Tu turno...");
                controller.setborderPane("-fx-border-color: #FFD700; -fx-border-width: 5px;", "-fx-border-color: transparent; -fx-border-width: 1px;");
            }
        }
    }

    /**
     * Simulates the machine player placing a card on the table.
     */
    public void putCardOnTheTable() {
        ArrayList<Card> cardsMachinePlayer = machinePlayer.getCardsPlayer();
        boolean validPlacement = false;

        for (int cardIndex = 0; cardIndex < cardsMachinePlayer.size(); cardIndex++) {
            try {
                Card card = machinePlayer.getCardsPlayer().get(cardIndex);
                gameUno.playCard(card, "MACHINE_PLAYER");
                System.out.println("La máquina descartó una carta.");
                machinePlayer.removeCard(cardIndex);
                tableImageView.setImage(card.getImage());
                validPlacement = true;
                break;
            } catch (UnoException ignored) {
            }
        }

        if (!validPlacement) {
            machineTakeCard();
        }
    }

    /**
     * Simulates the machine player taking a card from the deck.
     */
    public void machineTakeCard() {
        boolean cardTaken = false;

        while (!cardTaken) {
            try {
                gameUno.takeCardPlayer("MACHINE_PLAYER");
                System.out.println("La máquina comió una carta.");
                hasPlayerPlayed = false;
                eventManager.notifyListenersPlayerTurnUpdate(hasPlayerPlayed);
                cardTaken = true;
            } catch (IllegalStateException ignored) {
            }
        }
    }

    /**
     * Sets whether the human player has played their turn.
     * @param hasPlayerPlayed Indicates if the human player has played.
     */
    public void setHasPlayerPlayed(boolean hasPlayerPlayed) {
        this.hasPlayerPlayed = hasPlayerPlayed;
    }
}

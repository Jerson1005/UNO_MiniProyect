package org.example.eiscuno.model.machine;

import org.example.eiscuno.model.game.GameUno;

/**
 * Class that handles the refill of the deck of cards in the Uno game.
 * This thread checks if the deck is empty and refills it as needed.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class ThreadRefillDeck extends Thread {

    /**
     * Manages the core game logic of UNO.
     * This object controls the gameplay flow, rules, player actions, and turn management.
     *
     * @serial
     * @since 1.0
     * @see GameUno
     */
    private GameUno gameUno;


    /**
     * Constructor for ThreadRefillDeck.
     * @param gameUno an instance of GameUno.
     */
    public ThreadRefillDeck(GameUno gameUno) {
        this.gameUno = gameUno;
    }

    /**
     * Runs the thread. Continuously checks if the deck is empty and refills it if necessary.
     * The process repeats indefinitely, checking the deck status every 2 seconds.
     * If interrupted, a RuntimeException is thrown.
     */
    @Override
    public void run() {
        while (true) {
            if (gameUno.getDeck().isEmpty()) {
                gameUno.refillDeckOfCards();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

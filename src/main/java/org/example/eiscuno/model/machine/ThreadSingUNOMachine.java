package org.example.eiscuno.model.machine;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;

import java.util.ArrayList;

/**
 * Class that checks if the machine player needs to call "UNO".
 * Implements the {@link Runnable} interface and is intended to be run by a thread.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class ThreadSingUNOMachine implements Runnable {

    private ArrayList<Card> cardsPlayer;
    private ArrayList<Card> cardsMachine;
    private Player machinePlayer;
    private GameUno gameUno;
    private GameUnoController controller;
    private Player player;

    /**
     * Constructor for ThreadSingUNOMachine.
     * @param cardsPlayer the human player's cards.
     * @param machinePlayer the machine player.
     * @param gameUno the instance of GameUno.
     * @param controller the instance of GameUnoController.
     */
    public ThreadSingUNOMachine(ArrayList<Card> cardsPlayer, Player machinePlayer, GameUno gameUno, GameUnoController controller) {
        this.gameUno = gameUno;
        this.machinePlayer = machinePlayer;
        this.cardsPlayer = cardsPlayer;
        this.cardsMachine = machinePlayer.getCardsPlayer();
        this.controller = controller;
    }

    /**
     * Runs the thread, checking if the machine player needs to call "UNO".
     * The process repeats indefinitely:
     * It waits for a random duration between 0 and 5 seconds,
     * checks if the machine player has only one card, and if so, protects the machine player with "UNO".
     * It also checks if the human player has only one card and notifies the game instance.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hasOneCardTheMachinePlayer();
            hasOneCardTheHumanPlayer();
        }
    }

    /**
     * Checks if the human player has only one card.
     * If so, simulates the machine player calling "UNO" and notifies the game instance.
     */
    public void hasOneCardTheHumanPlayer() {
        System.out.println("Cantidad cartas: " + cardsPlayer.size());
        if (cardsPlayer.size() == 1) {
            if (!controller.getStateUno().equals("HUMAN_PLAYER")) {
                System.out.println("ENTRO ");
                controller.setButtonUno(true);

                try {
                    long delay = 2000 + (long) (Math.random() * 2000);
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                controller.setButtonUno(false);
                gameUno.haveSungOne(controller.getStateUno());
                System.out.println("Estado: " + controller.getStateUno());
            }
        } else {
            controller.SetStateUno("");
        }
    }

    /**
     * Checks if the machine player has only one card.
     * If so, protects the machine player by setting the "UNO" flag to true.
     */
    public void hasOneCardTheMachinePlayer() {
        if (cardsMachine.size() == 1) {
            this.machinePlayer.setProtectedByUno(true);
        }
    }
}

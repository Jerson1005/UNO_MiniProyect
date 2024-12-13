package org.example.eiscuno.model.machine;
// paquete org.example.eiscuno.model.machine
// Define el paquete donde se encuentra esta clase, relacionado con la lógica del jugador de máquina en el juego.

import javafx.scene.image.ImageView;
// importar javafx.scene.image.ImageView
// Importa la clase ImageView de JavaFX, utilizada para mostrar imágenes en la interfaz.

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.exception.UnoException;
// importar org.example.eiscuno.model.exception.UnoException
// Importa la clase UnoException para manejar excepciones específicas del juego Uno.

import org.example.eiscuno.model.observer.EventManager;
// importar org.example.eiscuno.model.observer.EventManager
// Importa EventManager, que maneja la gestión de eventos y notificaciones en el juego.

import org.example.eiscuno.model.card.Card;
// importar org.example.eiscuno.model.card.Card
// Importa la clase Card, que representa una carta en el juego.

import org.example.eiscuno.model.game.GameUno;
// importar org.example.eiscuno.model.game.GameUno
// Importa GameUno, que contiene la lógica principal del juego Uno.

import org.example.eiscuno.model.player.Player;
// importar org.example.eiscuno.model.player.Player
// Importa Player, que representa a un jugador en el juego.

import java.util.ArrayList;
// importar java.util.ArrayList
// Importa ArrayList, una estructura de datos para almacenar listas dinámicas de elementos.

import java.util.Random;
// importar java.util.Random
// Importa Random para generar números aleatorios, utilizado en las decisiones de la máquina.

public class ThreadPlayMachine extends Thread {
// public class ThreadPlayMachine extiende Thread
// Define una clase que extiende la clase Thread para manejar la lógica del jugador de máquina en un hilo separado.

    private EventManager eventManager;
    // private EventManager eventManager
    // Declara una instancia de EventManager para gestionar eventos relacionados con el juego.

    private GameUno gameUno;
    // private GameUno gameUno
    // Declara una instancia de GameUno, que contiene la lógica principal del juego.

    private Player machinePlayer;
    // private Player machinePlayer
    // Declara una instancia de Player que representa al jugador de máquina.

    private ImageView tableImageView;
    // private ImageView tableImageView
    // Declara un ImageView para mostrar la imagen de la carta actual en la mesa.

    private volatile boolean hasPlayerPlayed;
    // private volatile boolean hasPlayerPlayed
    // Declara una bandera que indica si el jugador humano ha jugado su turno, sincronizada entre hilos.

    private GameUnoController controller;
// Declara una referencia al controlador GameUnoController.

    public ThreadPlayMachine(EventManager eventManager, GameUno gameUno, Player machinePlayer, ImageView tableImageView, GameUnoController controller) {
        this.eventManager = eventManager;
        this.gameUno = gameUno;
        this.machinePlayer = machinePlayer;
        this.tableImageView = tableImageView;
        this.controller = controller; // Inicializa el controlador
        this.hasPlayerPlayed = false;
    }


    @Override
    public void run() {
        // Método principal del hilo que ejecuta la lógica de juego del jugador de máquina.
        while (!Thread.currentThread().isInterrupted()) {
            // Bucle que verifica continuamente el estado del turno del jugador, con posibilidad de interrupción.
            if (hasPlayerPlayed) {
                // Comprueba si el jugador humano ya ha jugado su turno.
               controller.setNotificationText("Turno de la máquina...");
                controller.setborderPane("-fx-border-color: transparent; -fx-border-width: 1px;","-fx-border-color: #FFD700; -fx-border-width: 5px;");
                //controller.visualShift(true);
                try {
                    Thread.sleep(2000);
                    // Pausa el hilo durante 2 segundos antes de ejecutar la acción de la máquina.
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restablece el estado de interrupción
                    e.printStackTrace();
                    return; // Sale del método si el hilo es interrumpido
                }
                Random random = new Random();
                // Crea un generador de números aleatorios.
                double probability = 0.2;
                // Define una probabilidad del 20% para tomar una carta.
                boolean takeCard = random.nextDouble() < probability;
                // Decide aleatoriamente si el jugador de máquina debe tomar una carta.

                if (takeCard) {
                    machineTakeCard();
                    // Llama al método para que la máquina tome una carta.
                } else {
                    putCardOnTheTable();
                    // Llama al método para que la máquina coloque una carta en la mesa.
                }

                eventManager.notifyListenersCardsMachinePlayerUpdate();
                controller.setNotificationText("Tu turno...");
                controller.setborderPane("-fx-border-color: #FFD700; -fx-border-width: 5px;","-fx-border-color: transparent; -fx-border-width: 1px;");
               // controller.visualShift(false);
                // Notifica a los observadores que las cartas del jugador de máquina han sido actualizadas.
            }
        }
    }

    private void putCardOnTheTable() {
        // private void putCardOnTheTable()
        // Método privado que simula al jugador de máquina colocando una carta en la mesa.
        ArrayList<Card> cardsMachinePlayer = machinePlayer.getCardsPlayer();
        // ArrayList<Card> cardsMachinePlayer = machinePlayer.getCardsPlayer()
        // Obtiene las cartas actuales del jugador de máquina.
        boolean validPlacement = false;
        // boolean validPlacement = false
        // Bandera que indica si una carta válida ha sido colocada.

        for (int cardIndex = 0; cardIndex < cardsMachinePlayer.size(); cardIndex++) {
            // for (int cardIndex = 0; cardIndex < cardsMachinePlayer.size(); cardIndex++)
            // Itera sobre las cartas del jugador de máquina.
            try {
                Card card = machinePlayer.getCardsPlayer().get(cardIndex);
                // Card card = machinePlayer.getCardsPlayer().get(cardIndex)
                // Obtiene una carta específica de la mano del jugador de máquina.
                gameUno.playCard(card, "MACHINE_PLAYER");
                // gameUno.playCard(card, "MACHINE_PLAYER")
                // Intenta jugar la carta en la mesa.
                System.out.println("Machine player placed a card.");
                // System.out.println("Machine player placed a card.")
                // Imprime un mensaje indicando que la máquina colocó una carta.
                machinePlayer.removeCard(cardIndex);
                // machinePlayer.removeCard(cardIndex)
                // Elimina la carta de la mano del jugador de máquina.
                tableImageView.setImage(card.getImage());
                // tableImageView.setImage(card.getImage())
                // Actualiza la imagen en la mesa para mostrar la carta jugada.
                validPlacement = true;
                // validPlacement = true
                // Marca que se ha realizado una jugada válida.
                break;
                // break
                // Sale del bucle después de colocar una carta.
            } catch (UnoException ignored) {
                // catch (UnoException ignored)
                // Ignora las excepciones si una carta no puede ser jugada.
            }
        }

        if (!validPlacement) {
            machineTakeCard();
            // machineTakeCard()
            // Si no se puede colocar una carta, la máquina toma una carta.
        }
    }

    public void machineTakeCard() {
        // public void machineTakeCard()
        // Método público que simula al jugador de máquina tomando una carta del mazo.
        boolean cardTaken = false;
        // boolean cardTaken = false
        // Bandera para controlar si la carta ha sido tomada con éxito.

        while (!cardTaken) {
            // while(!cardTaken)
            // Repite hasta que se tome una carta correctamente.
            try {
                gameUno.takeCardPlayer("MACHINE_PLAYER");
                // gameUno.takeCardPlayer("MACHINE_PLAYER")
                // Intenta tomar una carta del mazo para el jugador de máquina.
                System.out.println("Machine player took a card.");
                // System.out.println("Machine player took a card.")
                // Imprime un mensaje indicando que la máquina tomó una carta.
                hasPlayerPlayed = false;
                // hasPlayerPlayed = false
                // Marca que el jugador humano aún no ha jugado su turno.
                eventManager.notifyListenersPlayerTurnUpdate(hasPlayerPlayed);
                // eventManager.notifyListenersPlayerTurnUpdate(hasPlayerPlayed)
                // Notifica a los observadores que el turno del jugador humano está pendiente.
                cardTaken = true;
                // cardTaken = true
                // Marca que la carta ha sido tomada con éxito.
            } catch (IllegalStateException ignored) {
                // catch (IllegalStateException ignored)
                // Ignora excepciones que indiquen que el mazo está temporalmente ocupado.
            }
        }
    }

    public void setHasPlayerPlayed(boolean hasPlayerPlayed) {
        // public void setHasPlayerPlayed(boolean hasPlayerPlayed)
        // Método público para establecer si el jugador humano ha jugado su turno.
        this.hasPlayerPlayed = hasPlayerPlayed;
        // this.hasPlayerPlayed = hasPlayerPlayed
        // Actualiza la bandera que indica si el jugador humano ha jugado.
    }
}

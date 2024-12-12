package org.example.eiscuno.model.machine;
// Paquete que contiene la clase ThreadSingUNOMachine, dentro del modelo del juego de Uno.

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;
// Importa la clase Card para usarla en la manipulación de las cartas en el juego.

import org.example.eiscuno.model.game.GameUno;
// Importa la clase GameUno que representa la lógica principal del juego de Uno.

import org.example.eiscuno.model.player.Player;
// Importa la clase Player, que representa a un jugador en el juego.

import java.util.ArrayList;
// Importa la clase ArrayList para manejar listas dinámicas de cartas.


// Esta clase representa una tarea que verifica si el jugador máquina debe cantar "UNO".
// Implementa la interfaz {@link Runnable} y está destinada a ser ejecutada por un hilo.
public class ThreadSingUNOMachine implements Runnable {
    // Define la clase ThreadSingUNOMachine que implementa la interfaz Runnable para ser ejecutada por un hilo.

    private ArrayList<Card> cardsPlayer;
    // Declara una lista de cartas para el jugador humano.

    private ArrayList<Card> cardsMachine;
    // Declara una lista de cartas para el jugador máquina.

    private Player machinePlayer;
    // Declara una variable de tipo Player para representar al jugador máquina.

    private GameUno gameUno;
    // Declara una variable de tipo GameUno que representa la instancia del juego de Uno.
     private GameUnoController controller;
     private Player player;


    // Constructor de la clase ThreadSingUNOMachine.
    // Recibe las cartas del jugador humano, el jugador máquina y la instancia del juego de Uno.
    public ThreadSingUNOMachine(ArrayList<Card> cardsPlayer, Player machinePlayer, GameUno gameUno, GameUnoController controller) {
        this.gameUno = gameUno;
        // Asigna la instancia del juego de Uno a la variable gameUno.

        this.machinePlayer = machinePlayer;
        // Asigna el jugador máquina a la variable machinePlayer.

        this.cardsPlayer = cardsPlayer;
        // Asigna las cartas del jugador humano a la variable cardsPlayer.

        this.cardsMachine = machinePlayer.getCardsPlayer();
        // Asigna las cartas del jugador máquina a la variable cardsMachine, obteniéndolas desde el jugador.
        this.controller=controller;

    }


//     * Ejecuta el hilo, comprobando continuamente si el jugador máquina debe cantar "UNO".
//     * El proceso se repite indefinidamente:
//   Espera una duración aleatoria entre 0 y 5 segundos.
//   Comprueba si el jugador máquina tiene solo una carta.
//   Si el jugador máquina tiene una carta, canta "UNO" y notifica la instancia del juego.
    @Override
    public void run() {
        // Sobreescribe el método run de la interfaz Runnable para definir el comportamiento del hilo.

        while (true) {
            // Inicia un bucle infinito que ejecuta el código dentro del hilo de manera continua.

            try {
                Thread.sleep(2000);
                // Hace que el hilo espere una duración aleatoria entre 0 y 5000 milisegundos (0 a 5 segundos).
            } catch (InterruptedException e) {
                e.printStackTrace();
                // Si el hilo es interrumpido, se imprime el error en la consola.
            }

            hasOneCardTheMachinePlayer();
            // Llama al método para comprobar si el jugador máquina tiene solo una carta.

            hasOneCardTheHumanPlayer();
            // Llama al método para comprobar si el jugador humano tiene solo una carta.
        }
    }


//     * Comprueba si el jugador humano tiene solo una carta.
//     * Si el jugador humano tiene una carta, simula que la máquina canta "UNO"
//     * y notifica la instancia del juego.

    private boolean unoButtonActivated = false; // Nueva bandera para controlar el estado del botón UNO

    private void hasOneCardTheHumanPlayer() {
        System.out.println("Cantidad de cartas: " + cardsPlayer.size());

        // Verificar si el jugador tiene solo una carta
        if (cardsPlayer.size() == 1) {
            // Si ya se activó el botón UNO en esta jugada, no hacer nada
            if (unoButtonActivated) {
                System.out.println("El botón UNO ya fue activado para esta carta.");
                return; // Evita reactivar el botón
            }

            // Si el estado UNO ya está marcado, también evitar duplicación
            if (controller.getStateUno().equals("HUMAN_PLAYER")) {
                System.out.println("El estado UNO ya ha sido activado previamente.");
                return;
            }

            // Proceder a activar el botón UNO
            System.out.println("ENTRO: Activando botón UNO.");
            controller.setButtonUno(true); // Mostrar el botón UNO
            unoButtonActivated = true; // Marcar que el botón ya fue activado en este estado

            // Esperar un tiempo aleatorio entre 2 y 4 segundos
            try {
                long delay = 2000 + (long) (Math.random() * 2000);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace(); // Manejo de error
            }

            // Ocultar el botón UNO después del retraso
            controller.setButtonUno(false);

            // Marcar que el jugador ha cantado UNO
            controller.SetStateUno("HUMAN_PLAYER");
            gameUno.haveSungOne(controller.getStateUno());

            // Mostrar el estado actual en consola
            System.out.println("Estado: " + controller.getStateUno());
        } else {
            // Si el jugador tiene más de una carta, reiniciar estado
            if (unoButtonActivated) {
                System.out.println("Reiniciando el estado del botón UNO.");
            }
            controller.SetStateUno(""); // Limpiar el estado UNO
            unoButtonActivated = false; // Resetear la bandera para futuras jugadas
        }
    }



    // Comprueba si el jugador máquina tiene solo una carta.
    // Si el jugador máquina tiene una carta, simula que la máquina canta "UNO"
    //para protegerse y notifica la instancia del juego.

    private void hasOneCardTheMachinePlayer() {
        if (cardsMachine.size() == 1) {
            // Si el jugador máquina tiene solo una carta...

            this.machinePlayer.setProtectedByUno(true);
            // Establece la propiedad de protección de "UNO" para el jugador máquina a true.
        }
    }
}

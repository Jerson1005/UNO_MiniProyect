package org.example.eiscuno.model.machine;
// Paquete que contiene la clase ThreadSingUNOMachine, dentro del modelo del juego de Uno.

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

    // Constructor de la clase ThreadSingUNOMachine.
    // Recibe las cartas del jugador humano, el jugador máquina y la instancia del juego de Uno.
    public ThreadSingUNOMachine(ArrayList<Card> cardsPlayer, Player machinePlayer, GameUno gameUno) {
        this.gameUno = gameUno;
        // Asigna la instancia del juego de Uno a la variable gameUno.

        this.machinePlayer = machinePlayer;
        // Asigna el jugador máquina a la variable machinePlayer.

        this.cardsPlayer = cardsPlayer;
        // Asigna las cartas del jugador humano a la variable cardsPlayer.

        this.cardsMachine = machinePlayer.getCardsPlayer();
        // Asigna las cartas del jugador máquina a la variable cardsMachine, obteniéndolas desde el jugador.
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
                Thread.sleep((long) (Math.random() * 5000));
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

    private void hasOneCardTheHumanPlayer() {
        if (cardsPlayer.size() == 1) {
            // Si el jugador humano tiene solo una carta...

            gameUno.haveSungOne("MACHINE_PLAYER");
            // Llama al método haveSungOne de GameUno para notificar que la máquina ha cantado "UNO".
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

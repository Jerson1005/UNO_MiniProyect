package org.example.eiscuno.model.observer;
// Paquete que contiene la interfaz EventListener, utilizada para manejar eventos del juego de Uno.


// Esta interfaz representa un oyente de eventos para el juego de Uno.
// Define métodos para manejar varios eventos del juego, como los turnos de los jugadores y la actualización de cartas.
public interface EventListener {
    // Define la interfaz EventListener que contiene los métodos para actualizar los eventos del juego.


//    Se llama para actualizar al oyente sobre el estado del turno del jugador.
//     @param playerHasPlayed {@code true} si el jugador ha jugado, {@code false} si no lo ha hecho.

    void updatePlayerTurn(boolean playerHasPlayed);
    // Este método se utiliza para notificar al oyente si el jugador ha jugado o no.


//    Se llama para actualizar al oyente sobre las cartas del jugador máquina.

    void updateCardsMachinePlayer();
    // Este método se utiliza para notificar al oyente sobre las cartas del jugador máquina.


//     Se llama para actualizar al oyente sobre las cartas del jugador humano.

    void updateCardsHumanPlayer();
    // Este método se utiliza para notificar al oyente sobre las cartas del jugador humano.
}

package org.example.eiscuno.model.player;
//paquete org.example.eiscuno.modelo.jugador
// Define el paquete donde se encuentra esta interfaz.

import org.example.eiscuno.model.card.Card;
//importar org.example.eiscuno.modelo.carta.Carta
// Importa la clase `Card`, que representa una carta del juego Uno.

import java.util.ArrayList;
//importar java.util.ArrayList
// Importa la clase `ArrayList`, utilizada para manejar una lista de cartas en la mano del jugador.

//Interfaz que representa a un jugador en el juego de Uno.
// Proporciona métodos para interactuar con la mano de cartas del jugador.
public interface IPlayer {
    //pública interfaz IJugador
    // Define una interfaz llamada `IPlayer` que declara métodos para operaciones en la mano de cartas de un jugador.

    // Agrega una carta a la mano del jugador.
    //
    // @param card La carta que se agregará a la mano del jugador.
    void addCard(Card card);
    //vacío agregarCarta(Carta carta)
    // Declara un método para agregar una carta a la mano del jugador.

    // Recupera una carta de la mano del jugador basada en su índice.
    //
    // @param index El índice de la carta a recuperar.
    // @return La carta en el índice especificado de la mano del jugador.
    Card getCard(int index);
    //Carta obtenerCarta(int índice)
    // Declara un método que devuelve una carta específica de la mano del jugador, dada su posición (índice).

    // Recupera todas las cartas que actualmente posee el jugador.
    //
    // @return Un ArrayList que contiene todas las cartas en la mano del jugador.
    ArrayList<Card> getCardsPlayer();
    //ArrayList<Carta> obtenerCartasJugador()
    // Declara un método que devuelve todas las cartas en la mano del jugador en forma de lista.

    // Elimina una carta de la mano del jugador basada en su índice.
    //
    // @param index El índice de la carta a eliminar.
    void removeCard(int index);
    //vacío eliminarCarta(int índice)
    // Declara un método para eliminar una carta de la mano del jugador en una posición específica (índice).
}

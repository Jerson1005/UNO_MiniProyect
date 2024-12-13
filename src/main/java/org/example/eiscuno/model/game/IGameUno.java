package org.example.eiscuno.model.game;
// paquete org.example.eiscuno.modelo.juego;
// Define el paquete al que pertenece esta interfaz, que organiza las clases e interfaces relacionadas con la lógica del juego Uno.

import org.example.eiscuno.model.card.Card;
// importa org.example.eiscuno.modelo.carta.Carta;
// Importa la clase `Card` para representar cartas dentro de las funcionalidades del juego.

import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.exception.UnoException;
// importa org.example.eiscuno.modelo.excepción.UnoExcepción;
// Importa la clase `UnoException` para manejar excepciones específicas del juego Uno.


// Interfaz que representa la funcionalidad del juego Uno.

public interface IGameUno {
// interfaz pública IGameUno;
// Define una interfaz para estructurar las funciones principales del juego Uno.

    void SetCurrentTableCardColor(String Color);

    Deck getDeck();

    void refillDeckOfCards();

    void takeCardPlayer(String playerWhoTakes);

    String getRandomColor();



    boolean cardCanBePlayed(Card card, Card currentCard);

    void eatCard(String playerWhoEats, int numberOfCards);

    boolean isNumericCard(Card card);

    void initializeStartCard();
    // Inicia el juego de Uno.
    void startGame();
    // void iniciarJuego();
    // Define un método para iniciar el juego Uno.


    // Juega una carta en el juego, agregándola a la mesa.
    // @param carta la carta que se va a jugar.
    void playCard(Card card, String playerWhoPlays) throws UnoException;
    // void jugarCarta(Carta carta, String jugadorQueJuega) lanza UnoExcepción;
    // Define un método que permite jugar una carta, especificando el jugador que la juega, y lanza una excepción si ocurre un problema.


    // Maneja la acción cuando un jugador grita "Uno".
    // @param jugadorQueCantó el identificador del jugador que gritó "Uno".
    void haveSungOne(String playerWhoSang);
    // void haCantadoUno(String jugadorQueCantó);
    // Define un método para registrar y manejar la acción de gritar "Uno" por parte de un jugador.


    // Recupera las cartas visibles actuales del jugador humano a partir de una posición específica.
    // @param posInicialCartaAMostrar la posición inicial de las cartas que se mostrarán.
    // @return un arreglo de cartas que son visibles actualmente para el jugador humano.
    Card[] getCurrentVisibleCardsHumanPlayer(int posInitCardToShow);
    // Carta[] obtenerCartasVisiblesJugadorHumano(int posInicialCartaAMostrar);
    // Define un método que devuelve un arreglo con las cartas visibles para el jugador humano desde una posición específica.


    // Recupera las cartas visibles actuales del jugador máquina.
    // @return un arreglo de cartas que son visibles actualmente para el jugador humano.
    Card[] getCurrentVisibleCardsMachinePlayer();
    // Carta[] obtenerCartasVisiblesJugadorMáquina();
    // Define un método que devuelve un arreglo con las cartas visibles para el jugador máquina.

    // Verifica si el Jugador Humano ganó.
    // @return verdadero si ganó, falso de lo contrario.
    Boolean didHumanWin();
    // Booleano ganóJugadorHumano();
    // Define un método que verifica si el jugador humano ganó.


    // Verifica si el Jugador Máquina ganó.
    // @return verdadero si ganó, falso de lo contrario.
    Boolean didMachineWin();
    // Booleano ganóJugadorMáquina();
    // Define un método que verifica si el jugador máquina ganó.
}

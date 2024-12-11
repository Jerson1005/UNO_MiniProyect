package org.example.eiscuno.model.player;
//paquete org.example.eiscuno.modelo.jugador
// Define el paquete donde se encuentra la clase `Player`.

import org.example.eiscuno.model.card.Card;
//importar org.example.eiscuno.modelo.carta.Carta
// Importa la clase `Card`, que representa una carta en el juego Uno.

import java.util.ArrayList;
//importar java.util.ArrayList
// Importa la clase `ArrayList`, utilizada para manejar una lista dinámica de cartas.

//Representa un jugador en el juego de Uno.
public class Player implements IPlayer {
    //pública clase Jugador implementa IJugador
    // Declara una clase pública `Player` que implementa la interfaz `IPlayer`.

    private ArrayList<Card> cardsPlayer;
    //privada ArrayList<Carta> cartasJugador
    // Declara una lista de cartas que representa la mano de cartas del jugador.

    private String typePlayer;
    //privada Cadena tipoJugador
    // Declara una cadena que indica el tipo de jugador (por ejemplo, humano o máquina).

    private volatile boolean isProtectedByUno;
    //privada volátil booleano estáProtegidoPorUno
    // Declara una variable booleana (volátil) que indica si el jugador está protegido por UNO.

    //Construye un nuevo objeto Jugador con una mano vacía de cartas.
    public Player(String typePlayer) {
        //pública Jugador(Cadena tipoJugador)
        // Constructor que recibe el tipo de jugador como parámetro.

        this.cardsPlayer = new ArrayList<Card>();
        //este.cartasJugador = nueva ArrayList<Carta>()
        // Inicializa la lista de cartas del jugador como una lista vacía.

        this.typePlayer = typePlayer;
        //este.tipoJugador = tipoJugador
        // Asigna el tipo de jugador al atributo correspondiente.

        this.isProtectedByUno = false;
        //este.estáProtegidoPorUno = falso
        // Inicializa el estado de protección del jugador como falso (no protegido).
    }

    //Agrega una carta a la mano del jugador.
    //
    // @param card La carta que se agregará a la mano del jugador.
    @Override
    public void addCard(Card card) {
        //sobrescribir vacío agregarCarta(Carta carta)
        // Implementa el método `addCard` de la interfaz `IPlayer`.

        cardsPlayer.add(card);
        //cartasJugador.agregar(carta)
        // Agrega una carta a la lista de cartas del jugador.
    }

    //Recupera todas las cartas actualmente en posesión del jugador.
    //
    // @return una ArrayList que contiene todas las cartas en la mano del jugador.
    @Override
    public ArrayList<Card> getCardsPlayer() {
        //sobrescribir ArrayList<Carta> obtenerCartasJugador()
        // Implementa el método `getCardsPlayer` de la interfaz `IPlayer`.

        return cardsPlayer;
        //retornar cartasJugador
        // Devuelve la lista completa de cartas del jugador.
    }

    //Elimina una carta de la mano del jugador basada en su índice.
    //
    // @param index el índice de la carta a eliminar.
    @Override
    public void removeCard(int index) {
        //sobrescribir vacío eliminarCarta(int índice)
        // Implementa el método `removeCard` de la interfaz `IPlayer`.

        cardsPlayer.remove(index);
        //cartasJugador.eliminar(índice)
        // Elimina la carta de la lista usando su índice.
    }

    //Recupera una carta de la mano del jugador basada en su índice.
    //
    // @param index el índice de la carta a recuperar.
    // @return la carta en el índice especificado de la mano del jugador.
    @Override
    public Card getCard(int index) {
        //sobrescribir Carta obtenerCarta(int índice)
        // Implementa el método `getCard` de la interfaz `IPlayer`.

        return cardsPlayer.get(index);
        //retornar cartasJugador.obtener(índice)
        // Devuelve la carta ubicada en la posición especificada por el índice.
    }

    //Establece el estado de protección por UNO para el jugador.
    //
    // @param protectedByUno verdadero si el jugador está protegido por UNO, falso de lo contrario.
    public void setProtectedByUno(boolean protectedByUno) {
        //pública vacío establecerProtegidoPorUno(booleano protegidoPorUno)
        // Define un método público para asignar el estado de protección por UNO.

        isProtectedByUno = protectedByUno;
        //estáProtegidoPorUno = protegidoPorUno
        // Asigna el valor recibido al atributo `isProtectedByUno`.
    }

    //Verifica si el jugador está protegido por UNO.
    //
    // @return verdadero si el jugador está protegido por UNO, falso de lo contrario.
    public boolean isProtectedByUno() {
        //pública booleano estáProtegidoPorUno()
        // Define un método público que devuelve el estado de protección del jugador.

        return isProtectedByUno;
        //retornar estáProtegidoPorUno
        // Devuelve el valor del atributo `isProtectedByUno`.
    }
}

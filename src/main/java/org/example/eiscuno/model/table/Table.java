package org.example.eiscuno.model.table;
//paquete org.example.eiscuno.modelo.mesa
// Define el paquete donde se encuentra la clase `Table`.

import org.example.eiscuno.model.card.Card;
//importar org.example.eiscuno.modelo.carta.Carta
// Importa la clase `Card`, que representa una carta en el juego Uno.

import java.util.ArrayList;
//importar java.util.ArrayList
// Importa la clase `ArrayList`, que permite almacenar una lista dinámica de cartas.

//Representa la mesa en el juego de Uno donde se juegan las cartas.
public class Table {
    //pública clase Mesa
    // Define la clase `Table` que representa la mesa del juego Uno.

    private ArrayList<Card> cardsTable;
    //privada ArrayList<Carta> cartasMesa
    // Declara una lista de cartas que representa las cartas en la mesa.

    //Construye un nuevo objeto Mesa sin cartas.
    public Table() {
        //pública Mesa()
        // Constructor que inicializa una nueva instancia de la clase `Table`.

        this.cardsTable = new ArrayList<Card>();
        //este.cartasMesa = nueva ArrayList<Carta>()
        // Inicializa la lista `cardsTable` como una lista vacía.
    }

    //Agrega una carta a la mesa.
    //
    // @param card La carta que se agregará a la mesa.
    public void addCardOnTheTable(Card card) {
        //pública vacío agregarCartaEnLaMesa(Carta carta)
        // Método que agrega una carta a la lista `cardsTable`.

        this.cardsTable.add(card);
        //este.cartasMesa.agregar(carta)
        // Agrega la carta recibida a la lista de cartas en la mesa.
    }

    //Recupera la carta actual en la mesa.
    //
    // @return la carta actualmente en la mesa.
    // @throws IndexOutOfBoundsException si no hay cartas en la mesa.
    public Card getCurrentCardOnTheTable() throws IndexOutOfBoundsException {
        //pública Carta obtenerCartaActualEnLaMesa() lanza IndexOutOfBoundsException
        // Método que devuelve la última carta en la lista `cardsTable`.

        if (cardsTable.isEmpty()) {
            //si (cartasMesa.estáVacía())
            // Verifica si la lista de cartas en la mesa está vacía.

            throw new IndexOutOfBoundsException("There are no cards on the table.");
            //lanzar nueva IndexOutOfBoundsException("No hay cartas en la mesa.")
            // Lanza una excepción indicando que no hay cartas en la mesa.
        }
        return this.cardsTable.get(this.cardsTable.size() - 1);
        //retornar este.cartasMesa.obtener(este.cartasMesa.tamaño() - 1)
        // Devuelve la última carta de la lista (la carta actual en la mesa).
    }

    //Verifica si la mesa no tiene cartas.
    //
    // @return verdadero si no hay cartas en la mesa, falso de lo contrario.
    public boolean isEmpty() {
        //pública booleano estáVacía()
        // Método que devuelve si la lista `cardsTable` está vacía o no.

        return cardsTable.isEmpty();
        //retornar cartasMesa.estáVacía()
        // Devuelve `true` si la lista no contiene cartas, de lo contrario devuelve `false`.
    }

    //Recupera todas las cartas en la mesa.
    //
    // @return una lista con todas las cartas en la mesa.
    public ArrayList<Card> getCardsTable() {
        //pública ArrayList<Carta> obtenerCartasMesa()
        // Método que devuelve todas las cartas almacenadas en la mesa.

        return cardsTable;
        //retornar cartasMesa
        // Devuelve la lista completa de cartas en la mesa.
    }

    //Establece la carta inicial de la mesa.
    //
    // @param card La carta que se colocará como inicial en la mesa.
    public void setStartCard(Card card) {
        //pública vacío establecerCartaInicial(Carta carta)
        // Método que establece la carta inicial de la mesa.

        this.cardsTable.add(card);
        //este.cartasMesa.agregar(carta)
        // Agrega la carta inicial a la lista de cartas en la mesa.
    }
}

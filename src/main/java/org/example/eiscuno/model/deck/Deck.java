package org.example.eiscuno.model.deck;
// paquete org.example.eiscuno.modelo.baraja;
// Define el paquete al que pertenece esta clase, que organiza las clases relacionadas con la baraja de cartas en el juego.

import org.example.eiscuno.model.unoenum.EISCUnoEnum;
// importar org.example.eiscuno.modelo.unoenum.EISCUnoEnum;
// Importa la enumeración `EISCUnoEnum`, que contiene los valores representativos de las cartas de Uno.

import org.example.eiscuno.model.card.Card;
// importar org.example.eiscuno.modelo.carta.Carta;
// Importa la clase `Card`, que representa una carta del juego Uno.

import java.util.ArrayList;
// importar java.util.ArrayList;
// Importa la clase `ArrayList` para manejar colecciones de cartas.

import java.util.Collections;
// importar java.util.Collections;
// Importa la clase `Collections` para realizar operaciones como mezclar las cartas.

import java.util.Stack;
// importar java.util.Stack;
// Importa la clase `Stack`, que se utiliza como la estructura de datos para almacenar las cartas.

// Representa una baraja de cartas de Uno.
public class Deck {
    private Stack<Card> deckOfCards;
    // pila<Carta> barajaDeCartas;
    // Una pila que contiene todas las cartas de la baraja.

    // Construye una nueva baraja de cartas de Uno y la inicializa.
    public Deck() {
        deckOfCards = new Stack<>();
        // barajaDeCartas = nueva Pila<>();
        // Inicializa la pila de cartas.

        initializeDeck();
        // inicializarBaraja();
        // Llama al método para agregar e inicializar las cartas en la baraja.
    }

    // Inicializa la baraja con cartas basadas en los valores de EISCUnoEnum.
    private void initializeDeck() {
        for (EISCUnoEnum cardEnum : EISCUnoEnum.values()) {
            // para cada EISCUnoEnum cartaEnum en EISCUnoEnum.valores() {
            // Itera sobre todos los valores de la enumeración `EISCUnoEnum`.

            if (cardEnum.name().startsWith("GREEN_") ||
                    cardEnum.name().startsWith("YELLOW_") ||
                    cardEnum.name().startsWith("BLUE_") ||
                    cardEnum.name().startsWith("RED_") ||
                    cardEnum.name().startsWith("SKIP_") ||
                    cardEnum.name().startsWith("REVERSE_") ||
                    cardEnum.name().startsWith("TWO_WILD_DRAW_") ||
                    cardEnum.name().equals("FOUR_WILD_DRAW") ||
                    cardEnum.name().equals("WILD")) {
                // si el nombre de la cartaEnum comienza con colores o acciones específicas
                // Verifica que el nombre del valor de la enumeración coincide con las categorías deseadas.

                Card card = new Card(cardEnum.getFilePath(), getCardValue(cardEnum.name()), getCardColor(cardEnum.name()));
                // Carta carta = nueva Carta(cartaEnum.obtenerRutaArchivo(), obtenerValorCarta(cartaEnum.nombre()), obtenerColorCarta(cartaEnum.nombre()));
                // Crea una nueva instancia de `Card` con la ruta de archivo, valor y color obtenidos.

                deckOfCards.push(card);
                // barajaDeCartas.apilar(carta);
                // Añade la carta creada a la pila.

                System.out.println(card.getValue() + " " + card.getColor());
                // sistema.imprimir(carta.obtenerValor() + " " + carta.obtenerColor());
                // Imprime el valor y color de la carta creada para verificación.
            }
        }
        Collections.shuffle(deckOfCards);
        // Collections.mezclar(barajaDeCartas);
        // Mezcla las cartas de la baraja para aleatorizarlas.
    }

    // Extrae el valor del nombre de una carta.
    private String getCardValue(String name) {
        if (name.endsWith("0")) {
            return "0";
        } else if (name.endsWith("1")) {
            return "1";
        } else if (name.endsWith("2")) {
            return "2";
        } else if (name.endsWith("3")) {
            return "3";
        } else if (name.endsWith("4")) {
            return "4";
        } else if (name.endsWith("5")) {
            return "5";
        } else if (name.endsWith("6")) {
            return "6";
        } else if (name.endsWith("7")) {
            return "7";
        } else if (name.endsWith("8")) {
            return "8";
        } else if (name.endsWith("9")) {
            return "9";
        } else if (name.contains("REVERSE")) {
            return "REVERSE";
        } else if (name.contains("TWO_WILD_DRAW")) {
            return "+2";
        } else if (name.equals("FOUR_WILD_DRAW")) {
            return "+4";
        } else if (name.equals("WILD")) {
            return "WILD";
        } else if (name.contains("SKIP")) {
            return "SKIP";
        } else {
            return null;
        }
        // Extrae y devuelve el valor basado en el nombre de la carta.
    }

    // Extrae el color del nombre de una carta.
    private String getCardColor(String name) {
        if (name.contains("GREEN")) {
            return "GREEN";
        } else if (name.contains("YELLOW")) {
            return "YELLOW";
        } else if (name.contains("BLUE")) {
            return "BLUE";
        } else if (name.contains("RED")) {
            return "RED";
        } else {
            return "NON_COLOR";
        }
        // Devuelve el color de la carta o "SIN_COLOR" si no tiene un color específico.
    }

    // Toma una carta de la parte superior de la baraja.
    public Card takeCard() {
        if (deckOfCards.isEmpty()) {
            throw new IllegalStateException("Deck is empty. Wait for it to be refilled.");
            // lanzar nueva ExcepciónEstadoIlegal("La baraja está vacía. Espere a que se recargue.");
            // Lanza una excepción si la baraja está vacía.
        }
        return deckOfCards.pop();
        // retornar barajaDeCartas.desapilar();
        // Retira y devuelve la carta superior de la baraja.
    }

    // Devuelve el número de cartas en la baraja.
    public int size() {
        return deckOfCards.size();
        // retornar barajaDeCartas.tamaño();
        // Devuelve el tamaño actual de la pila de cartas.
    }

    // Verifica si la baraja está vacía.
    public boolean isEmpty() {
        return deckOfCards.isEmpty();
        // retornar barajaDeCartas.estaVacía();
        // Devuelve verdadero si la pila no contiene cartas.
    }

    // Rellena la baraja con las cartas dadas y mezcla la baraja.
    public void refillDeck(ArrayList<Card> allCardsInTableButLastOne) {
        for (Card card : allCardsInTableButLastOne) {
            deckOfCards.push(card);
            // barajaDeCartas.apilar(carta);
            // Añade cada carta del listado a la pila de la baraja.
        }
        Collections.shuffle(deckOfCards);
        // Collections.mezclar(barajaDeCartas);
        // Mezcla las cartas de la baraja después de recargarla.
    }
}

package org.example.eiscuno.model.deck;
//paquete org.example.eiscuno.modelo.mazo
// Define el paquete donde se encuentra la clase `DeckTest`.

import javafx.application.Platform;
//importar javafx.aplicación.Platform
// Importa la clase `Platform` para ejecutar operaciones en el hilo principal de JavaFX.

import javafx.stage.Stage;
//importar javafx.escenario.Stage
// Importa la clase `Stage`, que representa una ventana principal en JavaFX.

import org.example.eiscuno.model.table.Table;
//importar org.example.eiscuno.modelo.mesa.Table
// Importa la clase `Table`, que representa la mesa donde se colocan las cartas.

import org.junit.jupiter.api.BeforeAll;
//importar org.junit.jupiter.api.BeforeAll
// Importa la anotación `BeforeAll` para ejecutar un método antes de todos los tests.

import org.junit.jupiter.api.BeforeEach;
//importar org.junit.jupiter.api.BeforeEach
// Importa la anotación `BeforeEach` para ejecutar un método antes de cada test.

import org.junit.jupiter.api.Test;
//importar org.junit.jupiter.api.Test
// Importa la anotación `Test` para definir un método de prueba.

import org.testfx.framework.junit5.ApplicationTest;
//importar org.testfx.framework.junit5.ApplicationTest
// Importa la clase base `ApplicationTest` para realizar pruebas en JavaFX.

import java.util.concurrent.CountDownLatch;
//importar java.util.concurrent.CountDownLatch
// Importa `CountDownLatch` para sincronizar el inicio de JavaFX en las pruebas.

import static org.junit.jupiter.api.Assertions.*;
//importar estático org.junit.jupiter.api.Assertions.*
// Importa métodos de aserción como `assertEquals` para verificar resultados.

//Esta clase contiene pruebas unitarias para la clase Deck.
// Verifica el comportamiento correcto del mazo y las funcionalidades de la mesa.
class DeckTest extends ApplicationTest {
    //clase DeckTest extiende ApplicationTest
    // Define una clase `DeckTest` que hereda de `ApplicationTest` para realizar pruebas JavaFX.

    private static Deck deck;
    //privada estática Deck mazo
    // Declara un objeto `Deck` (mazo) que se utilizará en las pruebas.

    private static Table table;
    //privada estática Table mesa
    // Declara un objeto `Table` (mesa) que se utilizará en las pruebas.

    //Inicializa el entorno de ejecución de JavaFX.
    // Este método se ejecuta una vez antes de todos los métodos de prueba en esta clase.
    @BeforeAll
    static void initJFX() throws Exception {
        //estática vacío initJFX() lanza Exception
        // Método que inicia el sistema JavaFX.

        CountDownLatch latch = new CountDownLatch(1);
        //CountDownLatch cerrojo = nuevo CountDownLatch(1)
        // Declara un cerrojo que se usa para sincronizar la inicialización.

        Platform.startup(latch::countDown);
        //Platform.startup(cerrojo::contarAbajo)
        // Inicia JavaFX y libera el cerrojo cuando la inicialización termina.

        latch.await();
        //cerrojo.esperar()
        // Espera a que el cerrojo se libere antes de continuar.
    }

    //Configura las condiciones iniciales antes de cada prueba.
    // Inicializa los objetos `deck` y `table`.
    @BeforeEach
    void setup() {
        //vacío configurar()
        // Método que se ejecuta antes de cada método de prueba.

        deck = new Deck();
        //mazo = nuevo Deck()
        // Crea una nueva instancia del mazo.

        table = new Table();
        //mesa = nueva Table()
        // Crea una nueva instancia de la mesa.
    }

    //Prueba que el mazo se vacía correctamente al tomar todas las cartas.
    // Asegura que después de tomar todas las cartas, el tamaño del mazo sea cero.
    @Test
    void deckShouldBeEmpty() {
        //vacío mazoDebeEstarVacío()
        // Método de prueba para verificar que el mazo se vacía correctamente.

        var deckSize = deck.size();
        //var tamañoMazo = mazo.tamaño()
        // Obtiene el tamaño inicial del mazo.

        for (int i = 0; i < deckSize; i++) {
            //para (int i = 0; i < tamañoMazo; i++)
            // Itera sobre todas las cartas del mazo.

            table.addCardOnTheTable(deck.takeCard());
            //mesa.agregarCartaEnLaMesa(mazo.tomarCarta())
            // Toma una carta del mazo y la agrega a la mesa.
        }

        assertEquals(0, deck.size());
        //assertEquals(0, mazo.tamaño())
        // Verifica que el tamaño del mazo ahora sea cero.

        deck.refillDeck(table.getCardsTable());
        //mazo.rellenarMazo(mesa.obtenerCartasMesa())
        // Rellena el mazo con las cartas que están en la mesa.
    }

    //Prueba que el mazo se rellena correctamente después de estar vacío.
    // Asegura que el tamaño del mazo se restaura a 54 cartas.
    @Test
    void deckShouldBeRefilled() {
        //vacío mazoDebeSerRellenado()
        // Método de prueba para verificar que el mazo se rellena correctamente.

        assertEquals(54, deck.size());
        //assertEquals(54, mazo.tamaño())
        // Verifica que el tamaño del mazo sea 54 cartas.
    }
}

package org.example.eiscuno.model.card;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card;

    @BeforeAll
    static void initToolkit() {
        // Inicializa el toolkit de JavaFX
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setup() {
        // Usa la ruta correcta de la imagen
        card = new Card("/org/example/eiscuno/cards-uno/5_red.png", "5", "red");
    }

    @Test
    void cardAttributesShouldBeCorrect() {
        // Verifica que la URL de la imagen contenga la ruta relativa esperada
        assertTrue(card.getImage().getUrl().contains("/org/example/eiscuno/cards-uno/5_red.png"),
                "La URL de la imagen no contiene la ruta esperada");

        // Verifica los demás atributos
        assertEquals("5", card.getValue());
        assertEquals("red", card.getColor());
    }
}

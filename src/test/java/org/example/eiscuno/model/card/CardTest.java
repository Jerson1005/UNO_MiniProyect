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
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setup() {
        card = new Card("/org/example/eiscuno/cards-uno/5_red.png", "5", "red");
    }

    @Test
    void cardAttributesShouldBeCorrect() {
        assertTrue(card.getImage().getUrl().contains("/org/example/eiscuno/cards-uno/5_red.png"),
                "La URL de la imagen no contiene la ruta esperada");

        assertEquals("5", card.getValue());
        assertEquals("red", card.getColor());
    }
}

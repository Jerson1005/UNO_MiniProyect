package org.example.eiscuno.model.player;

import javafx.application.Platform;
import org.example.eiscuno.model.card.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PlayerTest {

    private Player player;

    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setup() {
        player = new Player("human");
    }

    @Test
    void playerShouldBeInitializedCorrectly() {
        assertNotNull(player.getCardsPlayer());
        assertTrue(player.getCardsPlayer().isEmpty());
        assertFalse(player.isProtectedByUno());
    }

    @Test
    void shouldAddCardToPlayerHand() {
        Card card = new Card("/org/example/eiscuno/cards-uno/5_red.png", "5", "red");
        player.addCard(card);

        List<Card> cards = player.getCardsPlayer();
        assertEquals(1, cards.size());
        assertEquals(card, cards.get(0));
    }

    @Test
    void shouldRemoveCardFromPlayerHand() {
        Card card = new Card("/org/example/eiscuno/cards-uno/5_red.png", "5", "red");
        player.addCard(card);
        player.removeCard(0);

        assertTrue(player.getCardsPlayer().isEmpty());
    }

    @Test
    void shouldSetAndCheckProtectedByUno() {
        player.setProtectedByUno(true);
        assertTrue(player.isProtectedByUno());

        player.setProtectedByUno(false);
        assertFalse(player.isProtectedByUno());
    }
}

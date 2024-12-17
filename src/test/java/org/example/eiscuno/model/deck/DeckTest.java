package org.example.eiscuno.model.deck;

import javafx.application.Platform;

import javafx.stage.Stage;


import org.example.eiscuno.model.table.Table;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest extends ApplicationTest {

    private static Deck deck;

    private static Table table;

    @BeforeAll
    static void initJFX() throws Exception {

        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(latch::countDown);

        latch.await();
    }

    @BeforeEach
    void setup() {

        deck = new Deck();

        table = new Table();
    }

    @Test
    void deckShouldBeEmpty() {

        var deckSize = deck.size();

        for (int i = 0; i < deckSize; i++) {

            table.addCardOnTheTable(deck.takeCard());
        }

        assertEquals(0, deck.size());

        deck.refillDeck(table.getCardsTable());
    }

    @Test
    void deckShouldBeRefilled() {

        assertEquals(54, deck.size());
    }
}

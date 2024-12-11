package org.example.eiscuno.model.observer;
// Paquete que contiene la clase EventManager, que se encarga de gestionar los oyentes de eventos del juego de Uno.

import java.util.ArrayList;
import java.util.List;
// Importa las clases necesarias: ArrayList y List, para manejar una lista dinámica de oyentes de eventos.


// Esta clase gestiona los oyentes de eventos y les notifica sobre los eventos del juego de Uno.
public class EventManager {
    // Define la clase EventManager, que gestiona la lista de oyentes y las notificaciones.

    private List<EventListener> listeners = new ArrayList<>();
    // private List<EventListener> listeners = new ArrayList<>();
    // Crea una lista privada que almacenará los oyentes de eventos (EventListener).
    // Esta lista se inicializa como un ArrayList vacío.


    // Añade un oyente de eventos para que sea notificado sobre los eventos del juego.
    public void addListener(EventListener eventListener) {
        // public void addListener(EventListener eventListener) {
        // Define un método público para añadir un oyente de eventos a la lista de oyentes.

        listeners.add(eventListener);
        // listeners.add(eventListener);
        // Añade el oyente de eventos a la lista de oyentes.
    }


    // Elimina un oyente de eventos de la lista de oyentes.
    public void removeListener(EventListener eventListener) {
        // public void removeListener(EventListener eventListener) {
        // Define un método público para eliminar un oyente de eventos de la lista de oyentes.

        listeners.remove(eventListener);
        // listeners.remove(eventListener);
        // Elimina el oyente de eventos de la lista de oyentes.
    }


    // Notifica a todos los oyentes registrados sobre la actualización del turno del jugador.
    public void notifyListenersPlayerTurnUpdate(boolean playerHasPlayed) {
        // public void notifyListenersPlayerTurnUpdate(boolean playerHasPlayed) {
        // Define un método público para notificar a todos los oyentes sobre la actualización del turno del jugador.

        for (EventListener eventListener : listeners) {
            // for (EventListener eventListener : listeners) {
            // Recorre la lista de oyentes de eventos.

            eventListener.updatePlayerTurn(playerHasPlayed);
            // eventListener.updatePlayerTurn(playerHasPlayed);
            // Llama al método updatePlayerTurn de cada oyente, pasando el estado del turno del jugador.
        }
    }


    // Notifica a todos los oyentes registrados sobre una actualización de las cartas del jugador humano.
    public void notifyListenersCardsHumanPlayerUpdate() {
        // public void notifyListenersCardsHumanPlayerUpdate() {
        // Define un método público para notificar a todos los oyentes sobre la actualización de las cartas del jugador humano.

        for (EventListener eventListener : listeners) {
            // for (EventListener eventListener : listeners) {
            // Recorre la lista de oyentes de eventos.

            eventListener.updateCardsHumanPlayer();
            // eventListener.updateCardsHumanPlayer();
            // Llama al método updateCardsHumanPlayer de cada oyente para notificar sobre las cartas del jugador humano.
        }
    }


    // Notifica a todos los oyentes registrados sobre una actualización de las cartas del jugador máquina.
    public void notifyListenersCardsMachinePlayerUpdate() {
        // public void notifyListenersCardsMachinePlayerUpdate() {
        // Define un método público para notificar a todos los oyentes sobre la actualización de las cartas del jugador máquina.

        for (EventListener eventListener : listeners) {
            // for (EventListener eventListener : listeners) {
            // Recorre la lista de oyentes de eventos.

            eventListener.updateCardsMachinePlayer();
            // eventListener.updateCardsMachinePlayer();
            // Llama al método updateCardsMachinePlayer de cada oyente para notificar sobre las cartas del jugador máquina.
        }
    }
}

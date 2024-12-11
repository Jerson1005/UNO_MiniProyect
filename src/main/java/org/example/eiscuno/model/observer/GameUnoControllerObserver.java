package org.example.eiscuno.model.observer;

//paquete org.example.eiscuno.modelo.observador
// Define el paquete donde se encuentra la clase.

import javafx.application.Platform;
//importar javafx.aplicación.Plataforma
// Importa la clase Platform de JavaFX, utilizada para ejecutar tareas en el hilo principal de la interfaz de usuario.

import org.example.eiscuno.controller.GameUnoController;
//importar org.example.eiscuno.controlador.ControladorJuegoUno
// Importa la clase GameUnoController, que maneja la lógica del juego Uno en la interfaz de usuario.

//Representa un observador que escucha eventos del juego Uno.
// Actualiza los métodos correspondientes en GameUnoController usando Platform de JavaFX para actualizaciones en la interfaz.
public class GameUnoControllerObserver implements EventListener {
    //publica clase GameUnoControladorObservador implementa EventListener
    // Define una clase pública que implementa la interfaz EventListener, utilizada para observar eventos en el juego Uno.

    private GameUnoController gameUnoController;
    //privada JuegoUnoControlador juegoUnoControlador
    // Declara una variable privada para referenciar una instancia de GameUnoController.

    // Actualiza el estado del turno del jugador en el GameUnoController asociado.
    //
    // @param playerHasPlayed {@code true} si el jugador ha jugado, {@code false} de lo contrario
    @Override
    public void updatePlayerTurn(boolean playerHasPlayed) {
        //sobrescribir vacío actualizarTurnoJugador(booleano jugadorHaJugado)
        // Implementa el método de la interfaz EventListener que actualiza el turno del jugador.

        gameUnoController.setPlayerHasPlayed(playerHasPlayed);
        //juegoUnoControlador.establecerJugadorHaJugado(jugadorHaJugado)
        // Llama al método setPlayerHasPlayed en GameUnoController para actualizar si el jugador ha jugado o no.
    }

    // Actualiza las cartas del jugador máquina en el GameUnoController asociado.
    // Utiliza Platform.runLater() para asegurar que las actualizaciones de la interfaz se realicen en el hilo principal de JavaFX.
    @Override
    public void updateCardsMachinePlayer() {
        //sobrescribir vacío actualizarCartasJugadorMáquina()
        // Implementa el método de la interfaz EventListener que actualiza las cartas del jugador máquina.

        Platform.runLater(() -> gameUnoController.printCardsMachinePlayer());
        //Plataforma.ejecutarMásTarde(() -> juegoUnoControlador.imprimirCartasJugadorMáquina())
        // Utiliza Platform.runLater para asegurar que printCardsMachinePlayer() se ejecute en el hilo principal de JavaFX.
    }

    // Actualiza las cartas del jugador humano en el GameUnoController asociado.
    // Utiliza Platform.runLater() para asegurar que las actualizaciones de la interfaz se realicen en el hilo principal de JavaFX.
    @Override
    public void updateCardsHumanPlayer() {
        //sobrescribir vacío actualizarCartasJugadorHumano()
        // Implementa el método de la interfaz EventListener que actualiza las cartas del jugador humano.

        Platform.runLater(() -> gameUnoController.printCardsHumanPlayer());
        //Plataforma.ejecutarMásTarde(() -> juegoUnoControlador.imprimirCartasJugadorHumano())
        // Utiliza Platform.runLater para asegurar que printCardsHumanPlayer() se ejecute en el hilo principal de JavaFX.
    }

    // Establece la instancia de GameUnoController para este observador.
    //
    // @param gameUnoController la instancia de GameUnoController a establecer
    public void setGameUnoController(GameUnoController gameUnoController) {
        //pública vacío establecerJuegoUnoControlador(JuegoUnoControlador juegoUnoControlador)
        // Define un método público para asignar una instancia de GameUnoController.

        this.gameUnoController = gameUnoController;
        //este.juegoUnoControlador = juegoUnoControlador
        // Asigna la instancia de GameUnoController proporcionada al campo gameUnoController de esta clase.
    }
}

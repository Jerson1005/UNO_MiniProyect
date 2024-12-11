package org.example.eiscuno.model.observer;
//paquete org.example.eiscuno.modelo.observador
// Define el paquete donde se encuentra esta clase.

import org.example.eiscuno.model.machine.ThreadPlayMachine;
//importar org.example.eiscuno.modelo.máquina.HiloJuegoMáquina
// Importa la clase ThreadPlayMachine, que maneja la lógica de juego de la máquina.

//Representa un observador para la clase ThreadPlayMachine.
// Este observador escucha eventos relacionados con los turnos de los jugadores y actualiza el ThreadPlayMachine en consecuencia.
public class ThreadPlayMachineObserver implements EventListener {
    //publica clase HiloJuegoMáquinaObservador implementa EventListener
    // Define una clase pública que implementa la interfaz EventListener, permitiéndole actuar como observador.

    private ThreadPlayMachine threadPlayMachine;
    //privada HiloJuegoMáquina hiloJuegoMáquina
    // Declara una variable privada para referenciar una instancia de ThreadPlayMachine.

    // Actualiza el ThreadPlayMachine cuando cambia el estado del turno del jugador.
    //
    // @param playerHasPlayed verdadero si el jugador ha jugado una carta, falso de lo contrario
    @Override
    public void updatePlayerTurn(boolean playerHasPlayed) {
        //sobrescribir vacío actualizarTurnoJugador(booleano jugadorHaJugado)
        // Implementa el método de la interfaz EventListener que actualiza el turno del jugador.

        threadPlayMachine.setHasPlayerPlayed(playerHasPlayed);
        //hiloJuegoMáquina.establecerJugadorHaJugado(jugadorHaJugado)
        // Llama al método setHasPlayerPlayed en ThreadPlayMachine para actualizar el estado del turno del jugador.
    }

    // Actualiza el observador con nuevas cartas para el jugador máquina.
    // Este método no está implementado porque no es necesario en este observador.
    @Override
    public void updateCardsMachinePlayer() {
        //sobrescribir vacío actualizarCartasJugadorMáquina()
        // Implementa el método de la interfaz EventListener, pero no realiza ninguna acción.
    }

    // Actualiza el observador con nuevas cartas para el jugador humano.
    // Este método no está implementado porque no es necesario en este observador.
    @Override
    public void updateCardsHumanPlayer() {
        //sobrescribir vacío actualizarCartasJugadorHumano()
        // Implementa el método de la interfaz EventListener, pero no realiza ninguna acción.
    }

    // Establece la instancia de ThreadPlayMachine para este observador.
    //
    // @param threadPlayMachine la instancia de ThreadPlayMachine a establecer
    public void setThreadPlayMachine(ThreadPlayMachine threadPlayMachine) {
        //pública vacío establecerHiloJuegoMáquina(HiloJuegoMáquina hiloJuegoMáquina)
        // Define un método público para asignar una instancia de ThreadPlayMachine a esta clase.

        this.threadPlayMachine = threadPlayMachine;
        //este.hiloJuegoMáquina = hiloJuegoMáquina
        // Asigna la instancia de ThreadPlayMachine proporcionada a la variable threadPlayMachine.
    }
}

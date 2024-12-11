package org.example.eiscuno.model.machine;
// Define el paquete para esta clase, relacionado con la lógica de la máquina en el juego.

import javafx.application.Platform;
// Importa Platform de JavaFX para ejecutar actualizaciones de UI en el hilo principal de la aplicación.

import org.example.eiscuno.controller.GameUnoController;
// Importa la clase GameUnoController, que gestiona el estado del juego y las actualizaciones de la interfaz.

import org.example.eiscuno.model.game.GameUno;
// Importa la clase GameUno, que contiene la lógica del juego.

import java.io.IOException;
// Importa IOException para manejar posibles errores de entrada/salida.


//  Esta clase representa un hilo que verifica continuamente el estado del juego
//  para determinar si ha terminado.
// Verifica periódicamente si el jugador humano o la máquina han ganado
// y llama al método correspondiente en GameUnoController para manejar la victoria o derrota.

public class ThreadEndGame extends Thread {
    GameUnoController gameUnoController;
    // Controlador que gestiona el estado del juego y actualiza la UI.
    GameUno gameUno;
    // Objeto que contiene la lógica del juego.


//      Constructor de la clase ThreadEndGame.
//      @param gameUnoController el controlador responsable de gestionar el estado del juego y las actualizaciones de la UI
//      @param gameUno el objeto que contiene la lógica del juego

    public ThreadEndGame(GameUnoController gameUnoController, GameUno gameUno) {
        this.gameUnoController = gameUnoController;
        this.gameUno = gameUno;
    }


//    El método `run` contiene la lógica para verificar continuamente el estado del juego.
//    Duerme 500 milisegundos entre cada verificación para evitar un uso excesivo de la CPU.
//     Si se detecta una condición de victoria, llama al método correspondiente en GameUnoController
//    usando `Platform.runLater` para garantizar que las actualizaciones de la UI
//    se realicen en el hilo de la aplicación JavaFX.

    @Override
    public void run() {
        while (true) {
            try {
                // Pausa la ejecución del hilo durante 500 milisegundos.
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Imprime el error si ocurre una interrupción del hilo.
                e.printStackTrace();
            }
            try {
                // Verifica si el jugador humano ha ganado.
                if (gameUno.didHumanWin()) {
                    Platform.runLater(() -> {
                        try {
                            // Llama al método win() del controlador para manejar la victoria.
                            gameUnoController.win();
                        } catch (IOException e) {
                            // Lanza una excepción en caso de un error de entrada/salida.
                            throw new RuntimeException(e);
                        }
                    });
                }
                // Verifica si la máquina ha ganado.
                else if (gameUno.didMachineWin()) {
                    Platform.runLater(() -> {
                        try {
                            // Llama al método lose() del controlador para manejar la derrota.
                            gameUnoController.lose();
                        } catch (IOException e) {
                            // Lanza una excepción en caso de un error de entrada/salida.
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (Exception e) {
                // Imprime cualquier excepción no prevista.
                e.printStackTrace();
            }
        }
    }


     // Establece el controlador GameUnoController.
     // @param gameUnoController el controlador responsable de gestionar el estado del juego y las actualizaciones de la UI

    public void setGameUnoController(GameUnoController gameUnoController) {
        this.gameUnoController = gameUnoController;
    }


     //Establece el objeto GameUno.
     //@param gameUno el objeto que contiene la lógica del juego

    public void setGameUno(GameUno gameUno) {
        this.gameUno = gameUno;
    }
}

package org.example.eiscuno.model.machine;
// Paquete que contiene la clase ThreadRefillDeck, dentro del modelo del juego de Uno.

// Importa la clase GameUno desde el paquete org.example.eiscuno.model.game
import org.example.eiscuno.model.game.GameUno;

// Representa una carta de juego en el juego de Uno.
// Cada carta tiene una URL para su imagen, un valor (como número o carta especial) y un color.

public class ThreadRefillDeck extends Thread {
    // Definición de la clase ThreadRefillDeck que extiende de la clase Thread.

    private GameUno gameUno;
    // Declara una variable privada llamada gameUno de tipo GameUno para referirse al juego de Uno.

    // Constructor de la clase ThreadRefillDeck que toma una instancia de GameUno.
    public ThreadRefillDeck(GameUno gameUno) {
        this.gameUno = gameUno;
        // Asigna la instancia de GameUno al atributo gameUno de la clase ThreadRefillDeck.
    }


//    Ejecuta el hilo, comprobando continuamente si el mazo está vacío
//    y reponiéndolo si es necesario.
//    El proceso se repite indefinidamente:
//    Comprueba si el mazo está vacío.
//    Si está vacío, repone el mazo de cartas.
//    Espera 2 segundos antes de comprobar de nuevo.
//    Si se interrumpe durante el descanso, el hilo lanza una excepción RuntimeException.

    @Override
    public void run() {
        // Sobreescribe el método run de la clase Thread para definir el comportamiento del hilo.

        while (true) {
            // Inicia un bucle infinito para que el hilo siga ejecutándose constantemente.

            if (gameUno.getDeck().isEmpty()) {
                // Si el mazo de cartas del juego está vacío...

                gameUno.refillDeckOfCards();
                // Llama al método refillDeckOfCards de GameUno para rellenar el mazo con cartas.

            }

            try {
                Thread.sleep(2000);
                // Hace que el hilo espere durante 2000 milisegundos (2 segundos).
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido mientras espera...

                throw new RuntimeException(e);
                // Lanza una RuntimeException para indicar que ocurrió un error.
            }
        }
    }
}

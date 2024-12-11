package org.example.eiscuno;
// paquete org.example.eiscuno;
// Define el paquete al que pertenece esta clase, lo que organiza el proyecto en módulos lógicos.

import javafx.application.Application;
// importar javafx.aplicación.Aplicación;
// Importa la clase `Application` de JavaFX, la cual es necesaria para iniciar una aplicación gráfica.

import javafx.stage.Stage;
// importar javafx.escenario.Escenario;
// Importa la clase `Stage` de JavaFX, que representa la ventana principal de la aplicación.

import org.example.eiscuno.view.GameUnoStage;
// importar org.ejemplo.eiscuno.vista.EscenarioJuegoUno;
// Importa la clase `GameUnoStage` del proyecto, que representa la ventana principal del juego.

import java.io.IOException;
// importar java.io.ExcepciónDeEntradaSalida;
// Importa la excepción `IOException`, que se lanza cuando ocurre un error de entrada/salida, como fallos al cargar archivos.

// La clase principal de la aplicación EISC Uno.
public class Main extends Application {

    // El método principal de la aplicación.
    // @param args los argumentos de la línea de comandos.
    public static void main(String[] args) {
        launch(args);
        // lanzar(args);
        // Llama al método `launch` de `Application`, que inicia la aplicación JavaFX.
    }

    // Inicia la aplicación.
    // @param primaryStage el escenario principal de la aplicación.
    // @throws IOException si ocurre un error al cargar el escenario.
    @Override
    public void start(Stage primaryStage) throws IOException {
        GameUnoStage.getInstance();
        // EscenarioJuegoUno.obtenerInstancia();
        // Obtiene la instancia única (singleton) de la clase `GameUnoStage` y la muestra como la ventana principal.
    }
}

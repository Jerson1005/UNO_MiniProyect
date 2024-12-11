package org.example.eiscuno.view;
//paquete org.example.eiscuno.vista
// Define el paquete donde se encuentra la clase `GameUnoStage`.

import javafx.fxml.FXMLLoader;
//importar javafx.fxml.FXMLLoader
// Importa la clase `FXMLLoader`, que carga archivos FXML.

import javafx.scene.Parent;
//importar javafx.escena.Parent
// Importa la clase `Parent`, que representa el nodo raíz de la escena.

import javafx.scene.Scene;
//importar javafx.escena.Scene
// Importa la clase `Scene`, que representa el contenedor de la interfaz gráfica.

import javafx.scene.image.Image;
//importar javafx.escena.imagen.Image
// Importa la clase `Image`, que se usa para configurar íconos o imágenes.

import javafx.stage.Stage;
//importar javafx.escenario.Stage
// Importa la clase `Stage`, que representa la ventana principal de la aplicación.

import org.example.eiscuno.model.unoenum.EISCUnoEnum;
//importar org.example.eiscuno.modelo.unoenum.EISCUnoEnum
// Importa la enumeración `EISCUnoEnum`, utilizada para obtener rutas de imágenes.

import java.io.IOException;
//importar java.io.IOException
// Importa la clase `IOException`, que maneja errores de entrada/salida.

//Representa la ventana principal de la aplicación del juego Uno.
// Esta ventana muestra la interfaz del juego al usuario.
public class GameUnoStage extends Stage {
    //pública clase GameUnoStage extiende Stage
    // Declara una clase pública `GameUnoStage` que hereda de `Stage` (ventana principal).

    //Construye una nueva instancia de GameUnoStage.
    //
    // @throws IOException si ocurre un error al cargar el archivo FXML para la interfaz del juego.
    public GameUnoStage() throws IOException {
        //pública GameUnoStage() lanza IOException
        // Constructor de la clase `GameUnoStage` que puede lanzar una excepción de entrada/salida.

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/eiscuno/game-uno-view.fxml"));
        //FXMLLoader cargador = nuevo FXMLLoader(obtenerClase().obtenerRecurso("/org/example/eiscuno/game-uno-view.fxml"))
        // Crea un cargador `FXMLLoader` que obtiene el archivo FXML de la ruta especificada.

        Parent root;
        //Parent raíz
        // Declara un objeto `Parent` para almacenar el nodo raíz de la interfaz cargada.

        try {
            root = loader.load();
            //raíz = cargador.cargar()
            // Carga el archivo FXML y asigna el nodo raíz a `root`.
        } catch (IOException e) {
            //capturar (IOException e)
            // Captura cualquier error que ocurra al cargar el archivo FXML.

            throw new IOException("Error while loading FXML file", e);
            //lanzar nuevo IOException("Error al cargar el archivo FXML", e)
            // Lanza nuevamente la excepción con un mensaje personalizado.
        }

        Scene scene = new Scene(root);
        //Escena escena = nueva Escena(raíz)
        // Crea una nueva escena con el nodo raíz cargado.

        // Configurando la ventana (Stage)
        setTitle("EISC Uno");
        //establecerTítulo("EISC Uno")
        // Establece el título de la ventana principal.

        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource(EISCUnoEnum.FAVICON.getFilePath()))));
        //obtenerÍconos().agregar(
        //     nueva Imagen(
        //         Cadena.valorDe(obtenerClase().obtenerRecurso(EISCUnoEnum.FAVICON.obtenerRutaArchivo()))))
        // Configura el ícono de la ventana utilizando la ruta del archivo desde `EISCUnoEnum`.

        setScene(scene);
        //establecerEscena(escena)
        // Asigna la escena creada a la ventana principal.

        setResizable(false);
        //establecerRedimensionable(falso)
        // Desactiva la opción para redimensionar la ventana.

        show();
        //mostrar()
        // Muestra la ventana principal al usuario.
    }

    //Cierra la instancia de GameUnoStage.
    // Este método se usa para liberar recursos cuando la ventana del juego ya no es necesaria.
    public static void deleteInstance() {
        //pública estática vacío eliminarInstancia()
        // Método estático que cierra y elimina la instancia actual de `GameUnoStage`.

        GameUnoStageHolder.INSTANCE.close();
        //GameUnoStageHolder.INSTANCIA.cerrar()
        // Cierra la instancia actual de la ventana.

        GameUnoStageHolder.INSTANCE = null;
        //GameUnoStageHolder.INSTANCIA = nulo
        // Elimina la referencia a la instancia para liberar recursos.
    }

    //Obtiene la instancia singleton de GameUnoStage.
    //
    // @return la instancia singleton de GameUnoStage.
    // @throws IOException si ocurre un error al crear la instancia.
    public static GameUnoStage getInstance() throws IOException {
        //pública estática GameUnoStage obtenerInstancia() lanza IOException
        // Método estático que devuelve la instancia única (singleton) de `GameUnoStage`.

        return GameUnoStageHolder.INSTANCE != null ?
                GameUnoStageHolder.INSTANCE :
                (GameUnoStageHolder.INSTANCE = new GameUnoStage());
        //retornar GameUnoStageHolder.INSTANCIA != nulo ?
        //         GameUnoStageHolder.INSTANCIA :
        //         (GameUnoStageHolder.INSTANCIA = nueva GameUnoStage())
        // Si la instancia existe, la devuelve; de lo contrario, crea una nueva instancia y la devuelve.
    }

    //Clase contenedora para la instancia singleton de GameUnoStage.
    // Esta clase asegura la inicialización perezosa (lazy initialization) de la instancia singleton.
    private static class GameUnoStageHolder {
        //privada estática clase GameUnoStageHolder
        // Clase anidada que mantiene la instancia única de `GameUnoStage`.

        private static GameUnoStage INSTANCE;
        //privada estática GameUnoStage INSTANCIA
        // Variable estática que almacena la instancia singleton.
    }
}

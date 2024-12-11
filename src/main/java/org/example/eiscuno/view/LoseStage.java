package org.example.eiscuno.view;
//paquete org.example.eiscuno.vista
// Define el paquete donde se encuentra la clase `LoseStage`.

import javafx.fxml.FXMLLoader;
//importar javafx.fxml.FXMLLoader
// Importa la clase `FXMLLoader`, utilizada para cargar archivos FXML.

import javafx.scene.Parent;
//importar javafx.escena.Parent
// Importa la clase `Parent`, que representa el nodo raíz de la interfaz gráfica.

import javafx.scene.Scene;
//importar javafx.escena.Scene
// Importa la clase `Scene`, que representa el contenedor principal de la interfaz.

import javafx.scene.image.Image;
//importar javafx.escena.imagen.Image
// Importa la clase `Image`, que se utiliza para asignar imágenes o íconos.

import javafx.stage.Stage;
//importar javafx.escenario.Stage
// Importa la clase `Stage`, que representa una ventana principal en JavaFX.

import org.example.eiscuno.model.unoenum.EISCUnoEnum;
//importar org.example.eiscuno.modelo.unoenum.EISCUnoEnum
// Importa la enumeración `EISCUnoEnum` para acceder a rutas de imágenes.

import java.io.IOException;
//importar java.io.IOException
// Importa la clase `IOException` que maneja errores de entrada/salida.

//Representa una ventana que se muestra cuando el jugador pierde el juego.
public class LoseStage extends Stage {
    //pública clase LoseStage extiende Stage
    // Declara la clase `LoseStage` que hereda de `Stage`, representando una ventana de pérdida.

    //Construye una nueva instancia de LoseStage.
    //
    // @throws IOException si ocurre un error al cargar el archivo FXML para la interfaz gráfica.
    public LoseStage() throws IOException {
        //pública LoseStage() lanza IOException
        // Constructor que inicializa la ventana de pérdida.

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/eiscuno/lose-view.fxml"));
        //FXMLLoader cargador = nuevo FXMLLoader(obtenerClase().obtenerRecurso("/org/example/eiscuno/lose-view.fxml"))
        // Crea un cargador FXML para cargar el archivo de diseño `lose-view.fxml`.

        Parent root;
        //Parent raíz
        // Declara un nodo raíz para la interfaz cargada.

        try {
            root = loader.load();
            //raíz = cargador.cargar()
            // Carga el archivo FXML y asigna el nodo raíz a `root`.
        } catch (IOException e) {
            //capturar (IOException e)
            // Captura cualquier excepción que ocurra al cargar el archivo FXML.

            throw new IOException("Error while loading FXML file", e);
            //lanzar nuevo IOException("Error al cargar el archivo FXML", e)
            // Relanza la excepción con un mensaje de error.
        }

        Scene scene = new Scene(root);
        //Escena escena = nueva Escena(raíz)
        // Crea una nueva escena con el nodo raíz cargado.

        // Configuración de la ventana
        setTitle("Perdiste");
        //establecerTítulo("Perdiste")
        // Configura el título de la ventana.

        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource(EISCUnoEnum.FAVICON.getFilePath()))));
        //obtenerÍconos().agregar(
        //     nueva Imagen(
        //         Cadena.valorDe(obtenerClase().obtenerRecurso(EISCUnoEnum.FAVICON.obtenerRutaArchivo()))))
        // Configura el ícono de la ventana utilizando la ruta del archivo desde `EISCUnoEnum`.

        setScene(scene);
        //establecerEscena(escena)
        // Asigna la escena creada a la ventana.

        setResizable(false);
        //establecerRedimensionable(falso)
        // Evita que la ventana sea redimensionable.

        show();
        //mostrar()
        // Muestra la ventana al usuario.
    }

    //Cierra la instancia de LoseStage.
    // Este método se utiliza para liberar recursos cuando la ventana de pérdida ya no es necesaria.
    public static void deleteInstance() {
        //pública estática vacío eliminarInstancia()
        // Método estático que elimina y cierra la instancia actual de `LoseStage`.

        LoseStageHolder.INSTANCE.close();
        //LoseStageHolder.INSTANCIA.cerrar()
        // Cierra la instancia actual de la ventana.

        LoseStageHolder.INSTANCE = null;
        //LoseStageHolder.INSTANCIA = nulo
        // Elimina la referencia a la instancia.
    }

    //Obtiene la instancia singleton de LoseStage.
    //
    // @return la instancia singleton de LoseStage.
    // @throws IOException si ocurre un error al crear la instancia.
    public static LoseStage getInstance() throws IOException {
        //pública estática LoseStage obtenerInstancia() lanza IOException
        // Método estático que devuelve la instancia única (singleton) de `LoseStage`.

        return LoseStageHolder.INSTANCE != null ?
                LoseStageHolder.INSTANCE :
                (LoseStageHolder.INSTANCE = new LoseStage());
        //retornar LoseStageHolder.INSTANCIA != nulo ?
        //         LoseStageHolder.INSTANCIA :
        //         (LoseStageHolder.INSTANCIA = nueva LoseStage())
        // Si la instancia ya existe, la devuelve; de lo contrario, la crea y la asigna.
    }

    //Clase contenedora para la instancia singleton de LoseStage.
    // Esta clase asegura la inicialización perezosa (lazy initialization) de la instancia singleton.
    private static class LoseStageHolder {
        //privada estática clase LoseStageHolder
        // Clase estática interna que almacena la instancia singleton.

        private static LoseStage INSTANCE;
        //privada estática LoseStage INSTANCIA
        // Variable que almacena la instancia única de `LoseStage`.
    }
}

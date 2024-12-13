package org.example.eiscuno.controller;
//paquete org.example.eiscuno.controlador
// Define el paquete donde se encuentra la clase actual, relacionada con los controladores.

import javafx.application.Platform;
import javafx.event.ActionEvent;
//importar javafx.evento.ActionEvent
// Importa la clase `ActionEvent`, que representa eventos de acción en JavaFX.

import javafx.fxml.FXML;
//importar javafx.fxml.FXML
// Importa la anotación `@FXML` para enlazar variables y métodos con elementos del archivo FXML.

import javafx.scene.control.Button;
//importar javafx.escena.control.Button
// Importa la clase `Button`, que representa botones en la interfaz gráfica.

import javafx.scene.control.Label;

import javafx.scene.image.Image;
//importar javafx.escena.imagen.Image
// Importa la clase `Image`, que se utiliza para cargar imágenes.

import javafx.scene.image.ImageView;
//importar javafx.escena.imagen.ImageView
// Importa la clase `ImageView`, que se utiliza para mostrar imágenes.

import javafx.scene.input.MouseEvent;
//importar javafx.escena.entrada.MouseEvent
// Importa la clase `MouseEvent`, que representa eventos del ratón en JavaFX.

import javafx.scene.layout.*;
//importar javafx.escena.layout.*
// Importa todas las clases de diseño (layout) de JavaFX.

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
//importar javafx.escenario.Stage
// Importa la clase `Stage`, que representa una ventana principal en JavaFX.

import org.example.eiscuno.model.exception.UnoException;
//importar org.example.eiscuno.modelo.excepción.UnoException
// Importa la clase `UnoException`, una excepción personalizada relacionada con el juego.

import org.example.eiscuno.model.machine.ThreadEndGame;
//importar org.example.eiscuno.modelo.máquina.ThreadEndGame
// Importa la clase `ThreadEndGame`, que maneja el final del juego mediante un hilo.

import org.example.eiscuno.model.machine.ThreadRefillDeck;
//importar org.example.eiscuno.modelo.máquina.ThreadRefillDeck
// Importa la clase `ThreadRefillDeck`, que se encarga de rellenar el mazo de cartas mediante un hilo.

import org.example.eiscuno.model.observer.EventManager;
//importar org.example.eiscuno.modelo.observador.EventManager
// Importa la clase `EventManager`, que gestiona los eventos en el patrón observador.

import org.example.eiscuno.model.card.Card;
//importar org.example.eiscuno.modelo.carta.Card
// Importa la clase `Card`, que representa una carta en el juego.

import org.example.eiscuno.model.deck.Deck;
//importar org.example.eiscuno.modelo.mazo.Deck
// Importa la clase `Deck`, que representa el mazo de cartas del juego.

import org.example.eiscuno.model.game.GameUno;
//importar org.example.eiscuno.modelo.juego.GameUno
// Importa la clase `GameUno`, que maneja la lógica principal del juego Uno.

import org.example.eiscuno.model.machine.ThreadPlayMachine;
//importar org.example.eiscuno.modelo.máquina.ThreadPlayMachine
// Importa la clase `ThreadPlayMachine`, que maneja las jugadas automáticas de la máquina.

import org.example.eiscuno.model.machine.ThreadSingUNOMachine;
//importar org.example.eiscuno.modelo.máquina.ThreadSingUNOMachine
// Importa la clase `ThreadSingUNOMachine`, que maneja el aviso de "UNO" por parte de la máquina.

import org.example.eiscuno.model.observer.ThreadPlayMachineObserver;
//importar org.example.eiscuno.modelo.observador.ThreadPlayMachineObserver
// Importa la clase `ThreadPlayMachineObserver`, observador de los hilos de la máquina.

import org.example.eiscuno.model.player.Player;
//importar org.example.eiscuno.modelo.jugador.Player
// Importa la clase `Player`, que representa un jugador en el juego.

import org.example.eiscuno.model.table.Table;
//importar org.example.eiscuno.modelo.mesa.Table
// Importa la clase `Table`, que representa la mesa donde se colocan las cartas.

import org.example.eiscuno.model.unoenum.EISCUnoEnum;
//importar org.example.eiscuno.modelo.unoenum.EISCUnoEnum
// Importa la enumeración `EISCUnoEnum`, que contiene rutas a las imágenes del juego.

import org.example.eiscuno.model.observer.GameUnoControllerObserver;
//importar org.example.eiscuno.modelo.observador.GameUnoControllerObserver
// Importa la clase `GameUnoControllerObserver`, observador del controlador del juego.

//importar org.example.eiscuno.vista.LoseStage
// Importa la clase `LoseStage`, que muestra la ventana cuando el jugador pierde.

//importar org.example.eiscuno.vista.WinStage
// Importa la clase `WinStage`, que muestra la ventana cuando el jugador gana.

import org.example.eiscuno.view.GameUnoStage;
import org.example.eiscuno.view.LoseStage;
import org.example.eiscuno.view.WinStage;
//importar org.example.eiscuno.vista.GameUnoStage
// Importa la clase `GameUnoStage`, que representa la ventana principal del juego.

import java.io.IOException;
//importar java.io.IOException
// Importa la excepción `IOException`, que maneja errores relacionados con entrada y salida.
//Controlador para el juego de Uno.
public class GameUnoController {
    //pública clase GameUnoController
    // Declara la clase `GameUnoController` que actúa como controlador principal para la lógica y la interfaz del juego Uno.

    @FXML
    BorderPane gameBorderPane;
    // @FXML BorderPane gameBorderPane;
    // Pane de diseño principal que organiza la estructura de la interfaz del juego Uno.

    @FXML
    private Button backButton;
    // @FXML privada Button backButton;
    // Botón de retroceso en la interfaz gráfica.

    @FXML
    private GridPane gridPaneCardsMachine;
    // @FXML privada GridPane gridPaneCardsMachine;
    // GridPane que organiza y muestra las cartas de la máquina en la interfaz.

    @FXML
    private GridPane gridPaneCardsPlayer;
    // @FXML privada GridPane gridPaneCardsPlayer;
    // GridPane que organiza y muestra las cartas del jugador en la interfaz.

    @FXML
    private ImageView deckButtonImageView;
    // @FXML privada ImageView deckButtonImageView;
    // ImageView que muestra la imagen del mazo de cartas (botón para robar cartas).

    @FXML
    private ImageView exitButtonImageView;
    // @FXML privada ImageView exitButtonImageView;
    // ImageView que representa el botón de salida en la interfaz.

    @FXML
    private ImageView backButtonImageView;
    // @FXML privada ImageView backButtonImageView;
    // ImageView que representa la imagen del botón de retroceso.

    @FXML
    private ImageView nextButtonImageView;
    // @FXML privada ImageView nextButtonImageView;
    // ImageView que representa la imagen del botón siguiente.

    @FXML
    private ImageView unoButtonImageView;
    // @FXML privada ImageView unoButtonImageView;
    // ImageView que representa el botón "UNO" en la interfaz.

    @FXML
    private ImageView tableImageView;
    // @FXML privada ImageView tableImageView;
    // ImageView que muestra la imagen actual de la mesa donde se colocan las cartas.

    @FXML
    private Pane paneMachine;

    @FXML
    private Pane panePlayer;

    @FXML
    private Label LabelNotificacions;

    @FXML
    private Label numberMachineLetters;

    @FXML
    private Label numberPlayerLetters;

    @FXML
    private Button buttonUno;
    @FXML
    private Button buttonTakeCard;

    private EventManager eventManager;
    //privada EventManager eventManager;
    // Objeto `EventManager` que maneja los eventos relacionados con el patrón observador.

    private GameUnoControllerObserver gameUnoObserver;
    //privada GameUnoControllerObserver gameUnoObserver;
    // Observador que actualiza y sincroniza la lógica del controlador con el estado del juego.

    private ThreadPlayMachineObserver threadPlayMachineObserver;
    //privada ThreadPlayMachineObserver threadPlayMachineObserver;
    // Observador que monitorea las acciones automáticas de la máquina.

    private Player humanPlayer;
    //privada Player humanPlayer;
    // Objeto `Player` que representa al jugador humano en el juego.

    private Player machinePlayer;
    //privada Player machinePlayer;
    // Objeto `Player` que representa al jugador máquina en el juego.

    private Deck deck;
    //privada Deck deck;
    // Objeto `Deck` que representa el mazo de cartas del juego Uno.

    private Table table;
    //privada Table table;
    // Objeto `Table` que representa la mesa donde se colocan las cartas jugadas.

    private GameUno gameUno;
    //privada GameUno gameUno;
    // Objeto `GameUno` que maneja la lógica principal del juego Uno.

    private int posInitCardToShow;
    //privada int posInitCardToShow;
    // Variable que indica la posición inicial de la carta a mostrar en la interfaz.

    private boolean playerHasPlayed;
    //privada booleano playerHasPlayed;
    // Variable booleana que indica si el jugador ya realizó una jugada en su turno.

    private Stage stage;
    //privada Stage stage;
    // Objeto `Stage` que representa la ventana actual donde se muestra el juego.

    private ThreadSingUNOMachine threadSingUNOMachine;
    //privada ThreadSingUNOMachine threadSingUNOMachine;
    // Hilo que maneja el aviso automático de "UNO" por parte de la máquina.

    private ThreadPlayMachine threadPlayMachine;
    //privada ThreadPlayMachine threadPlayMachine;
    // Hilo que controla las jugadas automáticas de la máquina.

    private ThreadRefillDeck threadRefillDeck;
    //privada ThreadRefillDeck threadRefillDeck;
    // Hilo que rellena el mazo de cartas cuando se queda vacío.

    private ThreadEndGame threadEndGame;
    //privada ThreadEndGame threadEndGame;
    // Hilo que maneja la lógica para finalizar el juego.
    private String stateUno="";
    //Inicializa el controlador.

    @FXML
    private GridPane gridPaneColor;

    @FXML
    private Button buttonBlue;

    @FXML
    private Button buttonGreen;

    @FXML
    private Button buttonRed;

    @FXML
    private Button buttonYellow;

    @FXML
    void onHandleBlue(ActionEvent event) {

    }

    @FXML
    void onHandleGreen(ActionEvent event) {

    }

    @FXML
    void onHandleRed(ActionEvent event) {

    }

    @FXML
    void onHandleYellow(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        //pública vacío inicializar()
        // Método que inicializa el controlador. Es llamado automáticamente después de cargar el archivo FXML.

        //setVisuals();
        //establecerVisuales()
        // Configura los elementos visuales iniciales de la interfaz.

        // Aquí se invoca el turno del jugador
        panePlayer.setStyle("-fx-border-color: red; -fx-border-width: 5px;");
        paneMachine.setStyle("-fx-border-color: transparent; -fx-border-width: 1px;");
        setNotificationText("Tu turno...");
        buttonUno.setVisible(false);
       gridPaneColor.setVisible(false);
        initVariables();
        //inicializarVariables()
        // Inicializa las variables necesarias para el funcionamiento del juego.

        showInitialCardOnTable();

        this.gameUno.startGame();
        //este.juegoUno.iniciarJuego()
        // Llama al método `startGame` del objeto `gameUno` para iniciar la lógica principal del juego.

        //showInitialCardOnTable();

        printCardsHumanPlayer();
        //imprimirCartasJugadorHumano()
        // Muestra en la interfaz gráfica las cartas del jugador humano.

        printCardsMachinePlayer();
        //imprimirCartasJugadorMáquina()
        // Muestra en la interfaz gráfica las cartas del jugador máquina.

        threadPlayMachine = new ThreadPlayMachine(this.eventManager, this.gameUno, this.machinePlayer, this.tableImageView, this);
        //hiloJugarMáquina = nuevo ThreadPlayMachine(este.eventManager, este.juegoUno, este.jugadorMáquina, este.imagenMesa)
        // Crea una instancia de `ThreadPlayMachine` para manejar las jugadas automáticas de la máquina.


        threadPlayMachine.start();
        //hiloJugarMáquina.iniciar()
        // Inicia el hilo `ThreadPlayMachine` para realizar las jugadas de la máquina.

        threadSingUNOMachine = new ThreadSingUNOMachine(this.humanPlayer.getCardsPlayer(), this.machinePlayer, gameUno,this);
        //hiloCantarUNO = nuevo ThreadSingUNOMachine(este.jugadorHumano.obtenerCartasJugador(), este.jugadorMáquina, juegoUno)
        // Crea una instancia de `ThreadSingUNOMachine` para manejar el aviso automático de "UNO".

        Thread t = new Thread(threadSingUNOMachine, "ThreadSingUNO");
        //Thread t = nueva Thread(hiloCantarUNO, "ThreadSingUNO")
        // Crea un nuevo hilo `Thread` con el nombre "ThreadSingUNO" y le asigna la tarea `threadSingUNOMachine`.

        t.start();
        //t.iniciar()
        // Inicia el hilo `Thread` para monitorear si la máquina debe cantar "UNO".

        threadRefillDeck = new ThreadRefillDeck(this.gameUno);
        //hiloRellenarMazo = nuevo ThreadRefillDeck(este.juegoUno)
        // Crea una instancia de `ThreadRefillDeck` que se encarga de rellenar el mazo cuando está vacío.

        threadRefillDeck.start();
        //hiloRellenarMazo.iniciar()
        // Inicia el hilo `ThreadRefillDeck` para monitorear y rellenar el mazo de cartas.

        threadEndGame = new ThreadEndGame(this, this.gameUno);
        //hiloFinJuego = nuevo ThreadEndGame(este, este.juegoUno)
        // Crea una instancia de `ThreadEndGame` para manejar la lógica de finalización del juego.

        threadEndGame.start();
        //hiloFinJuego.iniciar()
        // Inicia el hilo `ThreadEndGame` para monitorear y finalizar el juego cuando sea necesario.

        gameUnoObserver.setGameUnoController(this);
        //observadorJuegoUno.establecerControladorJuegoUno(este)
        // Asigna el controlador `GameUnoController` al observador `gameUnoObserver`.

        threadPlayMachineObserver.setThreadPlayMachine(threadPlayMachine);
        //observadorJugarMáquina.establecerHiloJugarMáquina(hiloJugarMáquina)
        // Asigna el hilo `ThreadPlayMachine` al observador `threadPlayMachineObserver`.



    }


    // Método para colocar la carta inicial en la mesa
    private void showInitialCardOnTable() {
        // Coloca la carta inicial en la mesa
        gameUno.initializeStartCard(); // Toma una carta inicial de la baraja

        // Obtiene la carta actual de la mesa
        Card initialCard = table.getCurrentCardOnTheTable();

        // Actualiza el ImageView de la mesa con la imagen de la carta inicial
        tableImageView.setImage(initialCard.getImage());

        // Mensaje de confirmación

        System.out.println("Carta inicial colocada en la mesa " + initialCard.getColor() + " " + initialCard.getValue());
    }

    public void setNotificationText(String message) {
        Platform.runLater(() -> {
            // Asegura que la modificación se realiza en el hilo principal de JavaFX
            LabelNotificacions.setText(message);
        });
    }
    public void setButtonUno(boolean button) {
        Platform.runLater(() -> {
            this.buttonUno.setVisible(button);// Asegura que la modificación se realiza en el hilo principal de JavaFX

        });
    }


    //Inicializa las variables del juego.
    private void initVariables() {
        //privada vacío inicializarVariables()
        // Método privado que inicializa las variables necesarias para el funcionamiento del juego.

        this.eventManager = new EventManager();
        //este.eventManager = nuevo EventManager()
        // Crea una instancia de `EventManager` para manejar los eventos del juego.

        this.gameUnoObserver = new GameUnoControllerObserver();
        //este.observadorJuegoUno = nuevo GameUnoControllerObserver()
        // Crea una instancia de `GameUnoControllerObserver`, un observador para actualizar el controlador del juego.

        this.threadPlayMachineObserver = new ThreadPlayMachineObserver();
        //este.observadorHiloJugarMáquina = nuevo ThreadPlayMachineObserver()
        // Crea una instancia de `ThreadPlayMachineObserver`, un observador para monitorear las jugadas de la máquina.

        eventManager.addListener(this.gameUnoObserver);
        //eventManager.agregarEscucha(este.observadorJuegoUno)
        // Añade `gameUnoObserver` como un observador al `EventManager`.

        eventManager.addListener(this.threadPlayMachineObserver);
        //eventManager.agregarEscucha(este.observadorHiloJugarMáquina)
        // Añade `threadPlayMachineObserver` como un observador al `EventManager`.

        this.humanPlayer = new Player("HUMAN_PLAYER");
        //este.jugadorHumano = nuevo Player("HUMAN_PLAYER")
        // Crea una instancia de `Player` para representar al jugador humano, asignándole un nombre.

        this.machinePlayer = new Player("MACHINE_PLAYER");
        //este.jugadorMáquina = nuevo Player("MACHINE_PLAYER")
        // Crea una instancia de `Player` para representar a la máquina, asignándole un nombre.

        this.deck = new Deck();
        //este.mazo = nuevo Deck()
        // Crea una instancia de `Deck` que representa el mazo de cartas del juego.

        this.table = new Table();
        //este.mesa = nueva Table()
        // Crea una instancia de `Table` que representa la mesa donde se colocan las cartas jugadas.

        this.gameUno = new GameUno(this.eventManager, this.humanPlayer, this.machinePlayer, this.deck, this.table,this);
        //este.juegoUno = nuevo GameUno(este.eventManager, este.jugadorHumano, este.jugadorMáquina, este.mazo, este.mesa)
        // Crea una instancia de `GameUno` y le pasa los jugadores, el mazo, la mesa y el manejador de eventos.

        this.posInitCardToShow = 0;
        //este.posiciónInicialCartaMostrar = 0
        // Inicializa la posición de la carta inicial a mostrar en la interfaz como `0`.

        this.playerHasPlayed = false;
        //este.jugadorHaJugado = falso
        // Inicializa el estado del jugador para indicar que aún no ha jugado.
    }

    public void setborderPane(String messagePlayer, String messageMachine) {
        Platform.runLater(() -> {
            // Asegura que la modificación se realiza en el hilo principal de JavaFX
            panePlayer.setStyle(messagePlayer);
            paneMachine.setStyle(messageMachine);
        });
    }

    private volatile boolean shift;
    public void visualShift(  boolean shift) {
        this.shift = shift;
        if (panePlayer != null && paneMachine != null) {
            if (shift) {
                panePlayer.setStyle("-fx-border-color: transparent; -fx-border-width: 1px;");
                paneMachine.setStyle("-fx-border-color: red; -fx-border-width: 5px;");
            } else {
                panePlayer.setStyle("-fx-border-color: red; -fx-border-width: 5px;");
                paneMachine.setStyle("-fx-border-color: transparent; -fx-border-width: 1px;");
            }
        } else {
            System.out.println("Uno de los paneles es nulo.");
        }
    }

    //Imprime las cartas del jugador humano en el panel de la cuadrícula.
    public void printCardsHumanPlayer() {
        //pública vacío imprimirCartasJugadorHumano()
        // Método público que muestra las cartas del jugador humano en el `GridPane` correspondiente.

        this.gridPaneCardsPlayer.getChildren().clear();
        //este.cuadrículaCartasJugador.getChildren().clear()
        // Limpia todos los elementos actualmente visibles en el `GridPane` para actualizar la visualización.

        Card[] currentVisibleCardsHumanPlayer = this.gameUno.getCurrentVisibleCardsHumanPlayer(this.posInitCardToShow);
        //Carta[] cartasVisiblesJugadorHumano = este.juegoUno.obtenerCartasVisiblesJugadorHumano(este.posiciónInicialCartaMostrar)
        // Obtiene las cartas visibles del jugador humano, comenzando desde la posición inicial indicada.

        for (int i = 0; i < currentVisibleCardsHumanPlayer.length; i++) {
            //para (int i = 0; i < cartasVisiblesJugadorHumano.longitud; i++)
            // Itera sobre las cartas visibles del jugador humano.

            Card card = currentVisibleCardsHumanPlayer[i];
            //Carta carta = cartasVisiblesJugadorHumano[i]
            // Toma una carta específica del arreglo de cartas visibles.

            ImageView cardImageView = card.getCard();
            //ImageView imagenCarta = carta.obtenerCarta()
            // Obtiene la representación visual (`ImageView`) de la carta.

            cardImageView.setOnMouseClicked((MouseEvent event) -> {
                //imagenCarta.establecerOnMouseClicked((MouseEvent evento) -> {
                // Configura un evento de clic del ratón sobre la imagen de la carta.

                if (!playerHasPlayed) {
                    //si (!jugadorHaJugado)
                    // Verifica si el jugador aún no ha jugado su turno.

                    try {
                        gameUno.playCard(card, "HUMAN_PLAYER");
                        //juegoUno.jugarCarta(carta, "HUMAN_PLAYER")
                        // Llama al método `playCard` para jugar la carta seleccionada.

                        System.out.println("Human player placed a card.");
                        //System.out.println("El jugador humano colocó una carta.")
                        // Imprime un mensaje en la consola indicando que el jugador jugó una carta.

                        tableImageView.setImage(card.getImage());
                        //imagenMesa.establecerImagen(carta.obtenerImagen())
                        // Actualiza la imagen de la mesa con la carta jugada.

                        humanPlayer.removeCard(findPosCardsHumanPlayer(card));
                        //jugadorHumano.eliminarCarta(encontrarPosiciónCartaJugadorHumano(carta))
                        // Elimina la carta jugada de la mano del jugador humano.



                        printCardsHumanPlayer();
                        //imprimirCartasJugadorHumano()
                        // Actualiza la visualización de las cartas del jugador humano.

                    } catch (UnoException e) {
                        //capturar (UnoException e)
                        // Captura cualquier excepción que ocurra al intentar jugar la carta.
                        LabelNotificacions.setText("No puedo colocar esa carta");
                        System.out.println(e.getMessage());
                        //System.out.println(e.getMessage())
                        // Imprime el mensaje de error en la consola.
                    }
                } else {
                    LabelNotificacions.setText("No es tu turno");
                    System.out.println("It's not your turn.");
                    //System.out.println("No es tu turno.")
                    // Imprime un mensaje indicando que el jugador no puede jugar fuera de turno.
                }
            });

            this.gridPaneCardsPlayer.add(cardImageView, i, 0);
            //este.cuadrículaCartasJugador.agregar(imagenCarta, i, 0)
            // Agrega la imagen de la carta al `GridPane` en la posición `i` (columna) y la fila `0`.
        }
         numberPlayerLetters.setText("Cartas Jugador "+ humanPlayer.getCardsPlayer().size());
        styleNumberMachineLetters(numberPlayerLetters);
      //  System.out.println("Number of cards human player: " + humanPlayer.getCardsPlayer().size());
        //System.out.println("Número de cartas del jugador humano: " + jugadorHumano.obtenerCartasJugador().tamaño())
        // Imprime en la consola la cantidad actual de cartas que tiene el jugador humano.
    }

    //Imprime las cartas del jugador máquina en el panel de la cuadrícula.
    public void printCardsMachinePlayer() {
        //pública vacío imprimirCartasJugadorMáquina()
        // Método público que muestra las cartas del jugador máquina en el `GridPane` correspondiente.

        this.gridPaneCardsMachine.getChildren().clear();
        //este.cuadrículaCartasMáquina.getChildren().clear()
        // Limpia todos los elementos actualmente visibles en el `GridPane` de las cartas de la máquina.

        Card[] currentVisibleCardsMachinePlayer = this.gameUno.getCurrentVisibleCardsMachinePlayer();
        //Carta[] cartasVisiblesJugadorMáquina = este.juegoUno.obtenerCartasVisiblesJugadorMáquina()
        // Obtiene las cartas visibles del jugador máquina desde el objeto `gameUno`.

        for (int i = 0; i < currentVisibleCardsMachinePlayer.length; i++) {
            //para (int i = 0; i < cartasVisiblesJugadorMáquina.longitud; i++)
            // Itera sobre todas las cartas visibles del jugador máquina.

            ImageView cardImageView = new ImageView(new Image(String.valueOf(getClass().getResource(EISCUnoEnum.CARD_UNO.getFilePath()))));
            //ImageView imagenCarta = nueva ImageView(nueva Imagen(Cadena.valorDe(obtenerClase().obtenerRecurso(EISCUnoEnum.CARD_UNO.obtenerRutaArchivo()))))
            // Crea un `ImageView` con la imagen trasera de una carta UNO para representar las cartas de la máquina.

            cardImageView.setY(16);
            //imagenCarta.establecerY(16)
            // Establece la posición vertical (`Y`) de la imagen en 16 píxeles para su alineación.

            cardImageView.setFitHeight(90);
            //imagenCarta.establecerAlturaAjustada(90)
            // Ajusta la altura de la imagen de la carta a 90 píxeles.

            cardImageView.setFitWidth(70);
            //imagenCarta.establecerAnchoAjustado(70)
            // Ajusta el ancho de la imagen de la carta a 70 píxeles.

            this.gridPaneCardsMachine.add(cardImageView, i, 0);
            //este.cuadrículaCartasMáquina.agregar(imagenCarta, i, 0)
            // Agrega la imagen de la carta al `GridPane` en la posición `i` (columna) y fila `0`.
        }
        numberMachineLetters.setText("Cartas Oponente "+ machinePlayer.getCardsPlayer().size());
        styleNumberMachineLetters(numberMachineLetters);
      //  System.out.println("Number of cards machine player: " + machinePlayer.getCardsPlayer().size());
        //System.out.println("Número de cartas del jugador máquina: " + jugadorMáquina.obtenerCartasJugador().tamaño())
        // Imprime en la consola la cantidad actual de cartas que tiene el jugador máquina.
    }
    //Encuentra la posición de una carta específica en la mano del jugador humano.
//@param card la carta a encontrar
//@return la posición de la carta, o -1 si no se encuentra
    private Integer findPosCardsHumanPlayer(Card card) {
        //privada Entero encontrarPosiciónCartaJugadorHumano(Carta carta)
        // Método privado que busca la posición de una carta específica en la mano del jugador humano.

        for (int i = 0; i < this.humanPlayer.getCardsPlayer().size(); i++) {
            //para (int i = 0; i < este.jugadorHumano.obtenerCartasJugador().tamaño(); i++)
            // Itera sobre las cartas en la mano del jugador humano.

            if (this.humanPlayer.getCardsPlayer().get(i).equals(card)) {
                //si (este.jugadorHumano.obtenerCartasJugador().obtener(i).equals(carta))
                // Compara cada carta en la mano con la carta dada.

                return i;
                //retornar i
                // Retorna la posición de la carta si se encuentra.
            }
        }
        return -1;
        //retornar -1
        // Retorna -1 si la carta no se encuentra.
    }


    public void styleNumberMachineLetters(Label numberLetters) {
        // Cambiar el color del texto a rojo
        numberLetters.setTextFill(Color.RED);

        // Cambiar la fuente a una fuente gamer (asegúrate de que exista en el sistema)
        numberLetters.setFont(Font.font("Press Start 2P", FontWeight.BOLD, 18));

        // Opcional: Añadir un borde o sombra si es necesario
        numberLetters.setStyle("-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
    }



    //Maneja la acción del botón "Atrás" para mostrar el conjunto anterior de cartas.
//@param event el evento de acción
    @FXML
    void onHandleBack(ActionEvent event) {
        //@FXML vacío manejarAtrás(ActionEvent evento)
        // Método manejador para el botón "Atrás" que muestra cartas anteriores.

        if (this.posInitCardToShow > 0) {
            //si (este.posiciónInicialCartaMostrar > 0)
            // Verifica si hay un conjunto anterior de cartas para mostrar.

            this.posInitCardToShow--;
            //este.posiciónInicialCartaMostrar--
            // Decrementa la posición inicial para mostrar cartas anteriores.

            printCardsHumanPlayer();
            //imprimirCartasJugadorHumano()
            // Actualiza las cartas visibles del jugador humano.
        }
    }

    //Maneja la acción del botón "Siguiente" para mostrar el siguiente conjunto de cartas.
//@param event el evento de acción
    @FXML
    void onHandleNext(ActionEvent event) {
        //@FXML vacío manejarSiguiente(ActionEvent evento)
        // Método manejador para el botón "Siguiente" que muestra cartas posteriores.

        if (this.posInitCardToShow < this.humanPlayer.getCardsPlayer().size() - 5) {
            //si (este.posiciónInicialCartaMostrar < este.jugadorHumano.obtenerCartasJugador().tamaño() - 4)
            // Verifica si hay un conjunto siguiente de cartas para mostrar.

            this.posInitCardToShow++;
            //este.posiciónInicialCartaMostrar++
            // Incrementa la posición inicial para mostrar cartas posteriores.

            printCardsHumanPlayer();
            //imprimirCartasJugadorHumano()
            // Actualiza las cartas visibles del jugador humano.
        }
    }

    //Maneja la acción de tomar una carta.
//@param event el evento de acción
    @FXML
    void onHandleTakeCard(ActionEvent event) {
        //@FXML vacío manejarTomarCarta(ActionEvent evento)


        // Método manejador para que el jugador humano tome una carta.

        if (!playerHasPlayed) {
            //si (!jugadorHaJugado)
            // Verifica si el jugador no ha jugado aún en su turno.

            try {
                gameUno.takeCardPlayer("HUMAN_PLAYER");
                //juegoUno.tomarCartaJugador("HUMAN_PLAYER")
                // Llama al método para que el jugador humano tome una carta.

                printCardsHumanPlayer();
                //imprimirCartasJugadorHumano()
                // Actualiza la visualización de las cartas del jugador humano.
                LabelNotificacions.setText("Tamaste una Carta de la baraja");
                System.out.println("Human player took a card.");
                //System.out.println("El jugador humano tomó una carta.")
                // Mensaje informativo en la consola.

                playerHasPlayed = true;
                //jugadorHaJugado = verdadero
                // Actualiza el estado indicando que el jugador ya jugó su turno.

                threadPlayMachine.setHasPlayerPlayed(this.playerHasPlayed);
                //hiloJugarMáquina.establecerJugadorHaJugado(este.jugadorHaJugado)
                // Informa al hilo de la máquina que el jugador humano ha jugado.
            } catch (IllegalStateException e) {
                //capturar (IllegalStateException e)
                // Captura una excepción si ocurre un error.
                LabelNotificacions.setText("No puedo colocar esa Carta");
                System.out.println(e.getMessage());
                //System.out.println(e.getMessage())
                // Muestra el mensaje de error en la consola.

                playerHasPlayed = false;
                //jugadorHaJugado = falso
                // Restablece el estado si ocurre un error.
            }
        } else {
            LabelNotificacions.setText("No es tu turno");
            System.out.println("It's not your turn.");
            //System.out.println("No es tu turno.")
            // Mensaje informativo si el jugador intenta tomar una carta fuera de su turno.
        }
    }

    //Maneja la acción de decir "Uno".
//@param event el evento de acción
    @FXML
    void onHandleUno(ActionEvent event) {
        //@FXML vacío manejarUNO(ActionEvent evento)
        // Método manejador para que el jugador diga "UNO".
        stateUno= "HUMAN_PLAYER";

        //juegoUno.hanCantadoUNO("HUMAN_PLAYER")
        // Llama al método para registrar que el jugador humano dijo "UNO".
    }

    public void SetStateUno(String state) {
           this.stateUno = state;
    }

    public String getStateUno(){
               return stateUno;
    }

    //Maneja la acción de presionar el botón de salida.
//@param event el evento de acción
    @FXML
    void onExitButtonClick(ActionEvent event) {
        //@FXML vacío manejarClicBotónSalir(ActionEvent evento)
        // Método manejador para cerrar la ventana del juego.

        GameUnoStage.deleteInstance();
        //GameUnoStage.eliminarInstancia()
        // Cierra y elimina la instancia actual del juego.
    }

    //Cierra la ventana actual y abre la ventana de victoria.
//@throws IOException si ocurre un error al abrir la ventana de victoria
    public void win() throws IOException {
        //pública vacío ganar() lanza IOException
        // Método para manejar la condición de victoria del jugador.

        this.stage = (Stage) this.buttonUno.getScene().getWindow();
        //este.ventana = (Stage) este.botonAtras.obtenerEscena().obtenerVentana()
        // Obtiene la ventana actual desde el botón.

        this.stage.close();
       // GameUnoStage.deleteInstance();
        //este.ventana.cerrar()
        // Cierra la ventana actual.

        WinStage.getInstance();
        //WinStage.obtenerInstancia()
        // Abre la ventana de victoria.
    }

    //Cierra la ventana actual y abre la ventana de derrota.
//@throws IOException si ocurre un error al abrir la ventana de derrota
    public void lose() throws IOException {
        //pública vacío perder() lanza IOException
        // Método para manejar la condición de derrota del jugador.
        this.stage = (Stage) this.buttonUno.getScene().getWindow();
        //este.ventana = (Stage) este.botonAtras.obtenerEscena().obtenerVentana()
        // Obtiene la ventana actual desde el botón.

        this.stage.close();
        //este.ventana.cerrar()
        // Cierra la ventana actual.
       // GameUnoStage.deleteInstance();
        LoseStage.getInstance();
        //LoseStage.obtenerInstancia()
        // Abre la ventana de derrota.
    }

    //Establece el atributo jugadorHaJugado para indicar si el jugador ha realizado un movimiento.
//@param playerHasPlayed verdadero si el jugador realizó un movimiento, falso en caso contrario.
    public void setPlayerHasPlayed(boolean playerHasPlayed) {
        //pública vacío establecerJugadorHaJugado(booleano jugadorHaJugado)
        // Método que actualiza el estado del jugador indicando si realizó una jugada.

        this.playerHasPlayed = playerHasPlayed;
        //este.jugadorHaJugado = jugadorHaJugado
        // Asigna el valor recibido a la variable `playerHasPlayed`.
    }

    public Button getButtonUno() {
        return buttonUno;
    }

    private String currentTableCardColor; // Guarda el color seleccionado

    // Método para mostrar la selección de color
    public void showColorSelectionPanel() {
        // Configurar el GridPane para que sea visible
        
        gridPaneColor.setVisible(true);
        gridPaneCardsPlayer.setDisable(true);
        buttonTakeCard.setDisable(true);
        // Configurar los estilos de los botones
        buttonBlue.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonGreen.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonRed.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonYellow.setStyle("-fx-background-color: yellow; -fx-text-fill: black; -fx-font-weight: bold;");

        // Configurar eventos para cada botón
        buttonBlue.setOnAction(event -> handleColorSelection("BLUE"));
        buttonGreen.setOnAction(event -> handleColorSelection("GREEN"));
        buttonRed.setOnAction(event -> handleColorSelection("RED"));
        buttonYellow.setOnAction(event -> handleColorSelection("YELLOW"));

    }

    // Método para manejar la selección del color
    public void handleColorSelection(String color) {
        System.out.println("Color seleccionado: " + color);

        // Configura el color actual de la carta en la mesa
        currentTableCardColor = color;

        // Mostrar solo el botón seleccionado

        resetButtonVisibility(false);
        switch (color) {
            case "BLUE":
                buttonBlue.setVisible(true);
                break;
            case "GREEN":
                buttonGreen.setVisible(true);
                break;
            case "RED":
                buttonRed.setVisible(true);
                break;
            case "YELLOW":
                buttonYellow.setVisible(true);
                break;
        }

        // Esperar 2 segundos antes de ocultar el GridPane
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Pausa de 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Ocultar el GridPane en el hilo de la interfaz gráfica (JavaFX Application Thread)
            javafx.application.Platform.runLater(() -> {
                gridPaneColor.setVisible(false);
                resetButtonVisibility(true);
            });
        }).start();

        gameUno.SetCurrentTableCardColor(currentTableCardColor);
        eventManager.notifyListenersPlayerTurnUpdate(true);

        System.out.println("Color Oprimido" + currentTableCardColor);
        gridPaneCardsPlayer.setDisable(false);
        buttonTakeCard.setDisable(false);
    }

   public String getColorSelected() {
        return currentTableCardColor;
   }

    // Método auxiliar para restablecer la visibilidad de los botones
    private void resetButtonVisibility(boolean worth) {
        buttonBlue.setVisible(worth);
        buttonGreen.setVisible(worth);
        buttonRed.setVisible(worth);
        buttonYellow.setVisible(worth);
    }
    public void colorMaquina(String color) {
        buttonBlue.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonGreen.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonRed.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonYellow.setStyle("-fx-background-color: yellow; -fx-text-fill: black; -fx-font-weight: bold;");
        
        System.out.println("Color seleccionado: " + color);
        gridPaneColor.setVisible(true);
        resetButtonVisibility(false);
        switch (color) {
            case "BLUE":
                buttonBlue.setVisible(true);
                break;
            case "GREEN":
                buttonGreen.setVisible(true);
                break;
            case "RED":
                buttonRed.setVisible(true);
                break;
            case "YELLOW":
                buttonYellow.setVisible(true);
                break;
        }

        // Esperar 2 segundos antes de ocultar el GridPane
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Pausa de 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Ocultar el GridPane en el hilo de la interfaz gráfica (JavaFX Application Thread)
            javafx.application.Platform.runLater(() -> {
                gridPaneColor.setVisible(false);
                resetButtonVisibility(true);
            });
        }).start();

    }


}

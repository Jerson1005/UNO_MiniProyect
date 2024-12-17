package org.example.eiscuno.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.eiscuno.model.exception.UnoException;
import org.example.eiscuno.model.machine.ThreadEndGame;
import org.example.eiscuno.model.machine.ThreadRefillDeck;
import org.example.eiscuno.model.observer.EventManager;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.machine.ThreadPlayMachine;
import org.example.eiscuno.model.machine.ThreadSingUNOMachine;
import org.example.eiscuno.model.observer.ThreadPlayMachineObserver;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;
import org.example.eiscuno.model.unoenum.EISCUnoEnum;
import org.example.eiscuno.model.observer.GameUnoControllerObserver;
import org.example.eiscuno.view.GameUnoStage;
import org.example.eiscuno.view.LoseStage;
import org.example.eiscuno.view.WinStage;
import org.example.eiscuno.view.InstructionsStage;

import java.io.IOException;

/**
 * This class represents the main game controller for the "Uno" game.
 * It manages the interactions between the user and the game logic, including the handling of the user interface components.
 * It controls actions such as drawing cards, playing cards, and managing the player's turn.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class GameUnoController {

    @FXML
    private GridPane gridPaneCardsMachine;

    @FXML
    private GridPane gridPaneCardsPlayer;

    @FXML
    private ImageView deckButtonImageView;

    @FXML
    private ImageView tableImageView;

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

    private String currentTableCardColor;

    private EventManager eventManager;

    private GameUnoControllerObserver gameUnoObserver;

    private ThreadPlayMachineObserver threadPlayMachineObserver;

    private Player humanPlayer;

    private Player machinePlayer;

    private Deck deck;

    private Table table;

    private GameUno gameUno;

    private int posInitCardToShow;

    private boolean playerHasPlayed;

    private Stage stage;

    private ThreadSingUNOMachine threadSingUNOMachine;

    private ThreadPlayMachine threadPlayMachine;

    private ThreadRefillDeck threadRefillDeck;

    private ThreadEndGame threadEndGame;
    private String stateUno = "";

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
    BorderPane gameBorderPane;

    /**
     * Handles the event of opening the instructions window.
     *
     * @param event The action event triggered by the button.
     * @throws IOException If an error occurs while loading the instructions stage.
     * @since 1.0
     */
    @FXML
    void onInstructions(ActionEvent event) throws IOException {

        try {
            InstructionsStage.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Initializes the game controller.
     * Sets up the game visuals, initializes variables, and starts threads for the game logic.
     *
     * @since 1.0
     */
    @FXML
    public void initialize() {

        setVisuals();

        panePlayer.setStyle("-fx-border-color: #FFD700; -fx-border-width: 5px;");
        paneMachine.setStyle("-fx-border-color: transparent; -fx-border-width: 1px;");
        setNotificationText("Tu turno...");
        buttonUno.setVisible(false);
        gridPaneColor.setVisible(false);
        initVariables();

        showInitialCardOnTable();

        this.gameUno.startGame();

        printCardsHumanPlayer();

        printCardsMachinePlayer();

        threadPlayMachine = new ThreadPlayMachine(this.eventManager, this.gameUno, this.machinePlayer, this.tableImageView, this);

        threadPlayMachine.start();

        threadSingUNOMachine = new ThreadSingUNOMachine(this.humanPlayer.getCardsPlayer(), this.machinePlayer, gameUno, this);

        Thread t = new Thread(threadSingUNOMachine, "ThreadSingUNO");

        t.start();

        threadRefillDeck = new ThreadRefillDeck(this.gameUno);

        threadRefillDeck.start();

        threadEndGame = new ThreadEndGame(this, this.gameUno);

        threadEndGame.start();

        gameUnoObserver.setGameUnoController(this);

        threadPlayMachineObserver.setThreadPlayMachine(threadPlayMachine);

    }

    /**
     * Configures the visual elements of the game interface.
     * Sets the background image and assigns the deck image.
     *
     * @since 1.0
     */
    public void setVisuals() {

        String imageUrl = String.valueOf(getClass().getResource(EISCUnoEnum.BACKGROUND_UNO.getFilePath()));
        String style = "-fx-background-image: url('" + imageUrl + "'); " + "-fx-background-size: cover;";
        deckButtonImageView.setImage(new Image(String.valueOf(getClass().getResource(EISCUnoEnum.DECK_OF_CARDS.getFilePath()))));
        gameBorderPane.setStyle(style);

    }

    /**
     * Places the initial card on the table at the start of the game.
     * Retrieves the card from the table and updates the ImageView.
     *
     * @since 1.0
     */
    public void showInitialCardOnTable() {
        gameUno.initializeStartCard();

        Card initialCard = table.getCurrentCardOnTheTable();

        tableImageView.setImage(initialCard.getImage());

        System.out.println("Carta inicial colocada en la mesa " + initialCard.getColor() + " " + initialCard.getValue());
    }

    /**
     * Updates the text in the notification label.
     *
     * @param message The message to be displayed in the notification label.
     * @since 1.0
     */
    public void setNotificationText(String message) {
        Platform.runLater(() -> {
            LabelNotificacions.setText(message);
        });
    }

    /**
     * Controls the visibility of the UNO button.
     *
     * @param button True to make the button visible, false to hide it.
     * @since 1.0
     */
    public void setButtonUno(boolean button) {
        Platform.runLater(() -> {
            this.buttonUno.setVisible(button);
        });
    }

    /**
     * Initializes the game variables and sets up observers.
     * Configures the players, deck, table, and event management.
     *
     * @since 1.0
     */
    public void initVariables() {

        this.eventManager = new EventManager();

        this.gameUnoObserver = new GameUnoControllerObserver();

        this.threadPlayMachineObserver = new ThreadPlayMachineObserver();

        eventManager.addListener(this.gameUnoObserver);

        eventManager.addListener(this.threadPlayMachineObserver);

        this.humanPlayer = new Player("HUMAN_PLAYER");

        this.machinePlayer = new Player("MACHINE_PLAYER");

        this.deck = new Deck();

        this.table = new Table();

        this.gameUno = new GameUno(this.eventManager, this.humanPlayer, this.machinePlayer, this.deck, this.table, this);

        this.posInitCardToShow = 0;

        this.playerHasPlayed = false;
    }

    /**
     * Updates the visual border styles for the player and machine panes.
     *
     * @param messagePlayer  The CSS style for the player pane.
     * @param messageMachine The CSS style for the machine pane.
     * @since 1.0
     */
    public void setborderPane(String messagePlayer, String messageMachine) {
        Platform.runLater(() -> {
            panePlayer.setStyle(messagePlayer);
            paneMachine.setStyle(messageMachine);
        });
    }

    /**
     * Displays the human player's cards in the designated grid panel.
     * Clears any previous cards and adds the current set of visible cards.
     *
     * @since 1.0
     */
    public void printCardsHumanPlayer() {
        this.gridPaneCardsPlayer.getChildren().clear();

        Card[] currentVisibleCardsHumanPlayer = this.gameUno.getCurrentVisibleCardsHumanPlayer(this.posInitCardToShow);

        for (int i = 0; i < currentVisibleCardsHumanPlayer.length; i++) {
            Card card = currentVisibleCardsHumanPlayer[i];

            ImageView cardImageView = card.getCard();

            cardImageView.setOnMouseClicked((MouseEvent event) -> {
                if (!playerHasPlayed) {
                    try {
                        gameUno.playCard(card, "HUMAN_PLAYER");

                        System.out.println("El jugador descartó una carta.");

                        tableImageView.setImage(card.getImage());

                        humanPlayer.removeCard(findPosCardsHumanPlayer(card));

                        printCardsHumanPlayer();

                    } catch (UnoException e) {
                        LabelNotificacions.setText("Cannot place that card");
                        System.out.println(e.getMessage());
                    }
                } else {
                    LabelNotificacions.setText("It's not your turn");
                    System.out.println("It's not your turn.");
                }
            });

            this.gridPaneCardsPlayer.add(cardImageView, i, 0);
        }
        numberPlayerLetters.setText("Tus cartas: " + humanPlayer.getCardsPlayer().size());
        styleNumberMachineLetters(numberPlayerLetters);
    }

    /**
     * Displays the machine player's cards in the designated grid panel.
     * Clears previous cards and displays the back image of cards to the player.
     *
     * @since 1.0
     */
    public void printCardsMachinePlayer() {
        this.gridPaneCardsMachine.getChildren().clear();

        Card[] currentVisibleCardsMachinePlayer = this.gameUno.getCurrentVisibleCardsMachinePlayer();

        for (int i = 0; i < currentVisibleCardsMachinePlayer.length; i++) {
            ImageView cardImageView = new ImageView(new Image(String.valueOf(getClass().getResource(EISCUnoEnum.CARD_UNO.getFilePath()))));

            cardImageView.setY(16);

            cardImageView.setFitHeight(90);

            cardImageView.setFitWidth(70);

            this.gridPaneCardsMachine.add(cardImageView, i, 0);
        }
        numberMachineLetters.setText("Sus cartas: " + machinePlayer.getCardsPlayer().size());
        styleNumberMachineLetters(numberMachineLetters);
    }

    /**
     * Finds the position of a specific card in the human player's hand.
     *
     * @param card The card to locate in the player's hand.
     * @return The position of the card in the hand, or -1 if the card is not found.
     * @since 1.0
     */
    public Integer findPosCardsHumanPlayer(Card card) {
        for (int i = 0; i < this.humanPlayer.getCardsPlayer().size(); i++) {
            if (this.humanPlayer.getCardsPlayer().get(i).equals(card)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Applies a visual style to the label showing the number of opponent's cards.
     *
     * @param numberLetters The label that displays the number of cards.
     * @since 1.0
     */
    public void styleNumberMachineLetters(Label numberLetters) {
        // Change the text color to red
        numberLetters.setTextFill(Color.RED);

        // Change the font to a gamer font (make sure it's available on the system)
        numberLetters.setFont(Font.font("Press Start 2P", FontWeight.BOLD, 25));

        // Optional: Add a border or shadow if necessary
        numberLetters.setStyle("-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
    }

    /**
     * Handles the "Back" button action to display the previous set of cards.
     *
     * @param event The action event triggered by the "Back" button.
     * @since 1.0
     */
    @FXML
    void onHandleBack(ActionEvent event) {
        if (this.posInitCardToShow > 0) {
            this.posInitCardToShow--;
            printCardsHumanPlayer();
        }
    }

    /**
     * Handles the "Next" button action to display the next set of cards.
     *
     * @param event The action event triggered by the "Next" button.
     * @since 1.0
     */
    @FXML
    void onHandleNext(ActionEvent event) {
        if (this.posInitCardToShow < this.humanPlayer.getCardsPlayer().size() - 5) {
            this.posInitCardToShow++;
            printCardsHumanPlayer();
        }
    }

    /**
     * Handles the action of taking a card from the deck.
     * Ensures that the player can only take a card during their turn.
     *
     * @param event The action event triggered by the "Take Card" button.
     * @since 1.0
     */
    @FXML
    void onHandleTakeCard(ActionEvent event) {
        if (!playerHasPlayed) {
            try {
                gameUno.takeCardPlayer("HUMAN_PLAYER");
                printCardsHumanPlayer();
                LabelNotificacions.setText("Tomaste una Carta de la baraja");
                System.out.println("El jugador comió una carta.");
                playerHasPlayed = true;
                threadPlayMachine.setHasPlayerPlayed(this.playerHasPlayed);
            } catch (IllegalStateException e) {
                LabelNotificacions.setText("No puedo colocar esa Carta");
                System.out.println(e.getMessage());
                playerHasPlayed = false;
            }
        } else {
            LabelNotificacions.setText("No es tu turno");
            System.out.println("It's not your turn.");
        }
    }

    /**
     * Handles the action for the "UNO" button click event.
     * Sets the state indicating the human player has called "UNO".
     *
     * @param event The action event triggered by the "UNO" button.
     * @since 1.0
     */
    @FXML
    void onHandleUno(ActionEvent event) {
        stateUno = "HUMAN_PLAYER";
    }

    /**
     * Sets the current state indicating whether "UNO" has been called.
     *
     * @param state The state to set for UNO (e.g., "HUMAN_PLAYER").
     * @since 1.0
     */
    public void SetStateUno(String state) {
        this.stateUno = state;
    }

    /**
     * Retrieves the current state of the UNO call.
     *
     * @return A string indicating the state of UNO (e.g., "HUMAN_PLAYER").
     * @since 1.0
     */
    public String getStateUno() {
        return stateUno;
    }

    /**
     * Handles the action for the "Exit" button click event.
     * Closes the current game window and deletes its instance.
     *
     * @param event The action event triggered by the "Exit" button.
     * @since 1.0
     */
    @FXML
    void onExitButtonClick(ActionEvent event) {
        GameUnoStage.deleteInstance();
    }

    /**
     * Closes the current game window and opens the victory screen.
     *
     * @throws IOException If an error occurs while opening the victory stage.
     * @since 1.0
     */
    public void win() throws IOException {
        this.stage = (Stage) this.buttonUno.getScene().getWindow();
        this.stage.close();
        WinStage.getInstance();
    }

    /**
     * Closes the current game window and opens the defeat screen.
     *
     * @throws IOException If an error occurs while opening the defeat stage.
     * @since 1.0
     */
    public void lose() throws IOException {
        this.stage = (Stage) this.buttonUno.getScene().getWindow();
        this.stage.close();
        LoseStage.getInstance();
    }

    /**
     * Sets whether the player has played their turn.
     *
     * @param playerHasPlayed A boolean indicating if the player has made a move.
     * @since 1.0
     */
    public void setPlayerHasPlayed(boolean playerHasPlayed) {
        this.playerHasPlayed = playerHasPlayed;
    }

    /**
     * Displays the color selection panel for the user to choose a card color.
     * Disables certain components until a color is selected.
     *
     * @since 1.0
     */
    public void showColorSelectionPanel() {
        gridPaneColor.setVisible(true);
        gridPaneCardsPlayer.setDisable(true);
        buttonTakeCard.setDisable(true);
        buttonBlue.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonGreen.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonRed.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonYellow.setStyle("-fx-background-color: yellow; -fx-text-fill: black; -fx-font-weight: bold;");
        buttonBlue.setOnAction(event -> handleColorSelection("BLUE"));
        buttonGreen.setOnAction(event -> handleColorSelection("GREEN"));
        buttonRed.setOnAction(event -> handleColorSelection("RED"));
        buttonYellow.setOnAction(event -> handleColorSelection("YELLOW"));
    }

    /**
     * Handles the color selection event when the user clicks a color button.
     *
     * @param color The color selected by the user (e.g., "BLUE", "RED").
     * @since 1.0
     */
    public void handleColorSelection(String color) {
        System.out.println("Color seleccionado: " + color);
        currentTableCardColor = color;
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
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    /**
     * Retrieves the color selected by the user.
     *
     * @return The selected color as a {@link String}.
     * @since 1.0
     */
    public String getColorSelected() {
        return currentTableCardColor;
    }

    /**
     * Resets the visibility of color selection buttons.
     *
     * @param worth A boolean determining whether to make buttons visible or invisible.
     * @since 1.0
     */
    public void resetButtonVisibility(boolean worth) {
        buttonBlue.setVisible(worth);
        buttonGreen.setVisible(worth);
        buttonRed.setVisible(worth);
        buttonYellow.setVisible(worth);
    }

    /**
     * Simulates the machine's color selection process.
     * Highlights the selected color and temporarily displays the color selection panel.
     *
     * @param color The color selected by the machine (e.g., "BLUE", "RED").
     * @since 1.0
     */
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

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            javafx.application.Platform.runLater(() -> {
                gridPaneColor.setVisible(false);
                resetButtonVisibility(true);
            });
        }).start();
    }
}

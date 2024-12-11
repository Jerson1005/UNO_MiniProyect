package org.example.eiscuno.model.game;
//paquete org.example.eiscuno.modelo.juego
// Define el paquete donde se encuentra la clase `GameUno`.

import org.example.eiscuno.controller.GameUnoController;
//importar org.example.eiscuno.controlador.GameUnoController
// Importa el controlador principal del juego Uno.

import org.example.eiscuno.model.card.Card;
//importar org.example.eiscuno.modelo.carta.Card
// Importa la clase `Card`, que representa una carta del juego.

import org.example.eiscuno.model.deck.Deck;
//importar org.example.eiscuno.modelo.mazo.Deck
// Importa la clase `Deck`, que maneja el mazo de cartas.

import org.example.eiscuno.model.exception.UnoException;
//importar org.example.eiscuno.modelo.excepción.UnoException
// Importa la clase `UnoException`, que representa errores en el juego.

import org.example.eiscuno.model.observer.EventManager;
//importar org.example.eiscuno.modelo.observador.EventManager
// Importa el manejador de eventos para notificar cambios.

import org.example.eiscuno.model.player.Player;
//importar org.example.eiscuno.modelo.jugador.Player
// Importa la clase `Player`, que representa a un jugador del juego.

import org.example.eiscuno.model.table.Table;
//importar org.example.eiscuno.modelo.mesa.Table
// Importa la clase `Table`, que representa la mesa donde se colocan las cartas.

import java.io.IOException;
//importar java.io.IOException
// Importa la excepción `IOException` para manejar errores de entrada/salida.

import java.util.ArrayList;
//importar java.util.ArrayList
// Importa la clase `ArrayList`, que se utiliza para manejar listas de cartas.

//Representa un juego de Uno.
// Administra la lógica del juego, incluyendo jugadores, mazo, mesa y eventos del juego.
public class GameUno implements IGameUno {

    private EventManager eventManager;
//privada EventManager manejadorEventos;
// Maneja los eventos del juego, como el cambio de turnos o la interacción entre componentes.

    private Player humanPlayer;
//privada Player jugadorHumano;
// Representa al jugador humano en el juego.

    private Player machinePlayer;
//privada Player jugadorMáquina;
// Representa al jugador controlado por la inteligencia artificial (IA) en el juego.

    private Deck deck;
//privada Deck mazo;
// Representa el mazo de cartas del juego Uno, donde se toman las cartas.

    private Table table;
//privada Table mesa;
// Representa la mesa donde se colocan las cartas jugadas durante la partida.

    private String currentTableCardColor;
//privada String colorCartaActualMesa;
// Almacena el color actual de la carta que está en la parte superior de la mesa.

    //Construye una nueva instancia de GameUno con los parámetros especificados.
//@param eventManager el gestor de eventos para manejar los eventos del juego
//@param humanPlayer el jugador humano en el juego
//@param machinePlayer el jugador controlado por la IA en el juego
//@param deck el mazo de cartas de Uno
//@param table la mesa donde se colocan las cartas durante el juego
    public GameUno(EventManager eventManager, Player humanPlayer, Player machinePlayer, Deck deck, Table table) {
        //pública GameUno(EventManager manejadorEventos, Player jugadorHumano, Player jugadorMáquina, Deck mazo, Table mesa)
        // Constructor de la clase `GameUno` que inicializa los componentes del juego.

        this.eventManager = eventManager;
        //este.manejadorEventos = manejadorEventos
        // Asigna el gestor de eventos proporcionado al atributo `eventManager` de la clase.

        this.humanPlayer = humanPlayer;
        //este.jugadorHumano = jugadorHumano
        // Asigna el jugador humano proporcionado al atributo `humanPlayer` de la clase.

        this.machinePlayer = machinePlayer;
        //este.jugadorMáquina = jugadorMáquina
        // Asigna el jugador máquina proporcionado al atributo `machinePlayer` de la clase.

        this.deck = deck;
        //este.mazo = mazo
        // Asigna el mazo de cartas proporcionado al atributo `deck` de la clase.

        this.table = table;
        //este.mesa = mesa
        // Asigna la mesa proporcionada al atributo `table` de la clase.

        currentTableCardColor = null;
        //colorCartaActualMesa = nulo
        // Inicializa el color actual de la carta en la mesa como `null` (sin color asignado al inicio).
    }


    //Inicia el juego de Uno.
    @Override
    public void startGame() {
        //@Override pública vacío iniciarJuego()
        // Método sobrescrito que inicia el juego Uno distribuyendo cartas a los jugadores.

        for (int i = 0; i < 10; i++) {
            //para (int i = 0; i < 10; i++)
            // Itera 10 veces para repartir un total de 10 cartas.

            if (i < 5) {
                //si (i < 5)
                // Si el índice es menor que 5, las cartas se asignan al jugador humano.

                humanPlayer.addCard(this.deck.takeCard());
                //jugadorHumano.agregarCarta(este.mazo.tomarCarta())
                // Toma una carta del mazo y la agrega a la mano del jugador humano.

            } else {
                //sino
                // Si el índice es mayor o igual a 5, las cartas se asignan al jugador máquina.

                machinePlayer.addCard(this.deck.takeCard());
                //jugadorMáquina.agregarCarta(este.mazo.tomarCarta())
                // Toma una carta del mazo y la agrega a la mano del jugador máquina.
            }
        }
    }


    //Distribuye cartas del mazo a un jugador.
//@param playerWhoEats el jugador que recibe las cartas ("MACHINE_PLAYER" o "HUMAN_PLAYER")
//@param numberOfCards el número de cartas a distribuir
    public void eatCard(String playerWhoEats, int numberOfCards) {
        //pública vacío comerCarta(String jugadorQueCome, int númeroDeCartas)
        // Método que distribuye un número específico de cartas al jugador especificado.

        if (deck.size() < numberOfCards) {
            //si (mazo.tamaño() < númeroDeCartas)
            // Verifica si el mazo tiene menos cartas de las que se necesitan distribuir.

            refillDeckOfCards();
            //rellenarMazoDeCartas()
            // Llama al método `refillDeckOfCards` para rellenar el mazo si no hay suficientes cartas.
        }

        if (playerWhoEats.equals("MACHINE_PLAYER")) {
            //si (jugadorQueCome.equals("MACHINE_PLAYER"))
            // Comprueba si el jugador que recibirá las cartas es el jugador máquina.

            for (int i = 0; i < numberOfCards; i++) {
                //para (int i = 0; i < númeroDeCartas; i++)
                // Itera tantas veces como cartas se deben distribuir.

                machinePlayer.addCard(this.deck.takeCard());
                //jugadorMáquina.agregarCarta(este.mazo.tomarCarta())
                // Toma una carta del mazo y la agrega a la mano del jugador máquina.
            }
        } else {
            //sino
            // Si el jugador no es la máquina, se asume que es el jugador humano.

            for (int i = 0; i < numberOfCards; i++) {
                //para (int i = 0; i < númeroDeCartas; i++)
                // Itera tantas veces como cartas se deben distribuir.

                humanPlayer.addCard(this.deck.takeCard());
                //jugadorHumano.agregarCarta(este.mazo.tomarCarta())
                // Toma una carta del mazo y la agrega a la mano del jugador humano.
            }
        }
    }

    //Juega una carta en el juego, agregándola a la mesa.
//@param card la carta que se va a jugar
//@param playerWhoPlays el jugador que juega la carta
//@throws UnoException si la carta no puede ser colocada
    @Override
    public void playCard(Card card, String playerWhoPlays) throws UnoException {
        //@Override pública vacío jugarCarta(Carta carta, String jugadorQueJuega) lanza UnoException
        // Método sobrescrito que maneja la lógica de jugar una carta en la mesa del juego.

        if (!didHumanWin() && !didMachineWin()) {
            //si (!humanoGanó() && !máquinaGanó())
            // Verifica si ningún jugador ha ganado todavía. Si ya hay un ganador, no permite continuar.

            if (table.isEmpty()) {
                //si (mesa.estáVacía())
                // Comprueba si la mesa está vacía, es decir, si no hay ninguna carta jugada aún.

                this.table.addCardOnTheTable(card);
                //este.mesa.agregarCartaEnLaMesa(carta)
                // Agrega la carta jugada a la mesa.

                applySpecialCardEffect(card, playerWhoPlays);
                //aplicarEfectoCartaEspecial(carta, jugadorQueJuega)
                // Aplica el efecto especial de la carta, si corresponde (por ejemplo, saltar turno, reversa, etc.).
            } else {
                //sino
                // Si ya hay cartas en la mesa, se procede a validar si la carta se puede jugar.

                Card currentCard = this.table.getCurrentCardOnTheTable();
                //Carta cartaActual = este.mesa.obtenerCartaActualEnLaMesa()
                // Obtiene la carta que está actualmente en la cima de la mesa.

                if (cardCanBePlayed(card, currentCard)) {
                    //si (cartaPuedeSerJugada(carta, cartaActual))
                    // Comprueba si la carta jugada es válida comparándola con la carta actual en la mesa.

                    this.table.addCardOnTheTable(card);
                    //este.mesa.agregarCartaEnLaMesa(carta)
                    // Agrega la carta jugada a la mesa.

                    applySpecialCardEffect(card, playerWhoPlays);
                    //aplicarEfectoCartaEspecial(carta, jugadorQueJuega)
                    // Aplica los efectos especiales de la carta jugada.
                } else {
                    //sino
                    // Si la carta no puede ser jugada.

                    throw new UnoException("Can't place this card");
                    //lanzar nuevo UnoException("No se puede colocar esta carta")
                    // Lanza una excepción indicando que la carta no es válida en la jugada actual.
                }
            }
        }
    }


    //Verifica si una carta puede ser jugada.
//@param card la carta que se quiere jugar
//@param currentCard la carta actual en la mesa
//@return verdadero si la carta puede ser jugada, falso en caso contrario
    private boolean cardCanBePlayed(Card card, Card currentCard) {
        //privada booleano cartaPuedeSerJugada(Carta carta, Carta cartaActual)
        // Método que verifica si la carta seleccionada por el jugador puede ser jugada según las reglas del juego Uno.

        return card.getColor().equals("NON_COLOR")
                //retornar carta.obtenerColor().equals("NON_COLOR")
                // Devuelve verdadero si la carta jugada tiene un color especial "NON_COLOR" (comodín).

                || currentTableCardColor.equals("NON_COLOR")
                //|| colorCartaActualMesa.equals("NON_COLOR")
                // Devuelve verdadero si el color actual de la mesa es "NON_COLOR", lo que permite jugar cualquier carta.

                || card.getValue().equals(currentCard.getValue())
                //|| carta.obtenerValor().equals(cartaActual.obtenerValor())
                // Devuelve verdadero si el valor de la carta jugada coincide con el valor de la carta actual en la mesa.

                || card.getColor().equals(currentTableCardColor != null ? currentTableCardColor : currentCard.getColor());
        //|| carta.obtenerColor().equals(colorCartaActualMesa != null ? colorCartaActualMesa : cartaActual.obtenerColor())
        // Devuelve verdadero si el color de la carta jugada coincide con el color actual en la mesa.
        // Si `currentTableCardColor` es nulo, se utiliza el color de la carta actual en la mesa.
    }


    //Aplica los efectos especiales de jugar una carta y actualiza el estado del juego en consecuencia.
//@param card la carta que se está jugando
//@param playerWhoPlays el jugador que está jugando la carta ("HUMAN_PLAYER" o "MACHINE_PLAYER")
//@throws UnoException si ocurre un error durante la jugada de la carta
    private void applySpecialCardEffect(Card card, String playerWhoPlays) throws UnoException {
        //privada vacío aplicarEfectoCartaEspecial(Carta carta, String jugadorQueJuega) lanza UnoException
        // Método que aplica los efectos de las cartas especiales y actualiza el estado del juego.

        String playerWhoEats;
        //String jugadorQueCome;
        // Variable que define qué jugador recibirá los efectos negativos de la carta jugada.

        if (playerWhoPlays.equals("HUMAN_PLAYER")) {
            //si (jugadorQueJuega.equals("HUMAN_PLAYER"))
            // Comprueba si la carta fue jugada por el jugador humano.

            playerWhoEats = "MACHINE_PLAYER";
            //jugadorQueCome = "MACHINE_PLAYER"
            // Define que el jugador máquina será el afectado por los efectos de la carta.

            switch (card.getValue()) {
                //cambiar (carta.obtenerValor())
                // Evalúa el valor de la carta jugada.

                case "+2":
                    currentTableCardColor = card.getColor();
                    //caso "+2": colorCartaActualMesa = carta.obtenerColor()
                    // Actualiza el color actual de la mesa con el color de la carta jugada.

                    eatCard(playerWhoEats, 2);
                    //comerCarta(jugadorQueCome, 2)
                    // El jugador máquina toma 2 cartas adicionales del mazo.

                    eventManager.notifyListenersPlayerTurnUpdate(true);
                    //gestorEventos.notificarActualizaciónTurnoJugador(verdadero)
                    // Notifica que el turno del jugador humano continúa.

                    eventManager.notifyListenersCardsMachinePlayerUpdate();
                    //gestorEventos.notificarActualizaciónCartasJugadorMáquina()
                    // Actualiza la visualización de las cartas de la máquina.
                    break;

                case "+4":
                    currentTableCardColor = card.getColor();
                    //caso "+4": colorCartaActualMesa = carta.obtenerColor()
                    // Actualiza el color actual de la mesa con el color de la carta jugada.

                    eatCard(playerWhoEats, 4);
                    //comerCarta(jugadorQueCome, 4)
                    // El jugador máquina toma 4 cartas adicionales del mazo.

                    eventManager.notifyListenersPlayerTurnUpdate(true);
                    eventManager.notifyListenersCardsMachinePlayerUpdate();
                    break;

                case "SKIP", "REVERSE":
                    currentTableCardColor = card.getColor();
                    //caso "SKIP", "REVERSE": colorCartaActualMesa = carta.obtenerColor()
                    // Actualiza el color de la carta en la mesa.

                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    //gestorEventos.notificarActualizaciónTurnoJugador(falso)
                    // Notifica que el turno del jugador máquina se salta o invierte.
                    break;

                case "WILD":
                    eventManager.notifyListenersPlayerTurnUpdate(true);
                    //caso "WILD": gestorEventos.notificarActualizaciónTurnoJugador(verdadero)
                    // Notifica que el turno del jugador humano continúa después de jugar un comodín.
                    break;

                default:
                    currentTableCardColor = card.getColor();
                    //predeterminado: colorCartaActualMesa = carta.obtenerColor()
                    // Actualiza el color actual de la mesa para cartas normales.

                    eventManager.notifyListenersPlayerTurnUpdate(true);
                    //gestorEventos.notificarActualizaciónTurnoJugador(verdadero)
                    // Notifica que el turno continúa.
                    break;
            }
        } else {
            //sino
            // Si la carta fue jugada por el jugador máquina.

            playerWhoEats = "HUMAN_PLAYER";
            //jugadorQueCome = "HUMAN_PLAYER"
            // Define que el jugador humano será el afectado por los efectos de la carta.

            switch (card.getValue()) {
                case "+2":
                    currentTableCardColor = card.getColor();
                    eatCard(playerWhoEats, 2);
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    eventManager.notifyListenersCardsHumanPlayerUpdate();
                    break;

                case "+4":
                    currentTableCardColor = card.getColor();
                    eatCard(playerWhoEats, 4);
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    eventManager.notifyListenersCardsHumanPlayerUpdate();
                    break;

                case "SKIP", "REVERSE":
                    currentTableCardColor = card.getColor();
                    eventManager.notifyListenersPlayerTurnUpdate(true);
                    break;

                case "WILD":
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    break;

                default:
                    currentTableCardColor = card.getColor();
                    eventManager.notifyListenersPlayerTurnUpdate(false);
                    break;
            }
        }
    }


    //Maneja la acción cuando un jugador grita "Uno".
//@param playerWhoSang el identificador del jugador que gritó "Uno"
    @Override
    public void haveSungOne(String playerWhoSang) {
        //@Override pública vacío haberCantadoUno(String jugadorQueCantó)
        // Método que maneja la lógica cuando un jugador canta "UNO" en el juego.

        boolean machinePlayerProtectedByUno = this.machinePlayer.isProtectedByUno();
        //booleano máquinaProtegidaPorUno = este.jugadorMáquina.estáProtegidoPorUno()
        // Verifica si el jugador máquina ya está protegido por "UNO".

        boolean humanPlayerProtectedByUno = this.humanPlayer.isProtectedByUno();
        //booleano humanoProtegidoPorUno = este.jugadorHumano.estáProtegidoPorUno()
        // Verifica si el jugador humano ya está protegido por "UNO".

        if (playerWhoSang.equals("HUMAN_PLAYER")) {
            //si (jugadorQueCantó.equals("HUMAN_PLAYER"))
            // Comprueba si el jugador que cantó "UNO" es el jugador humano.

            if (machinePlayer.getCardsPlayer().size() == 1 && !machinePlayerProtectedByUno) {
                //si (jugadorMáquina.obtenerCartasJugador().tamaño() == 1 && !máquinaProtegidaPorUno)
                // Si el jugador máquina tiene exactamente una carta y no está protegido.

                machinePlayer.addCard(this.deck.takeCard());
                //jugadorMáquina.agregarCarta(este.mazo.tomarCarta())
                // Se penaliza al jugador máquina agregándole una carta del mazo.

                eventManager.notifyListenersCardsMachinePlayerUpdate();
                //gestorEventos.notificarActualizaciónCartasJugadorMáquina()
                // Notifica a los observadores para actualizar la visualización de las cartas de la máquina.

            } else if (humanPlayer.getCardsPlayer().size() == 1) {
                //sino si (jugadorHumano.obtenerCartasJugador().tamaño() == 1)
                // Si el jugador humano tiene exactamente una carta en la mano.

                this.humanPlayer.setProtectedByUno(true);
                //este.jugadorHumano.establecerProtegidoPorUno(verdadero)
                // Marca al jugador humano como protegido por "UNO".

            } else {
                System.out.println("Can't sing UNO.");
                //System.out.println("No puedes cantar UNO.")
                // Mensaje de error si el jugador no cumple las condiciones para cantar "UNO".
            }
        } else {
            //sino
            // Si el jugador que cantó "UNO" es la máquina.

            if (!humanPlayerProtectedByUno) {
                //si (!humanoProtegidoPorUno)
                // Verifica si el jugador humano no está protegido por "UNO".

                humanPlayer.addCard(this.deck.takeCard());
                //jugadorHumano.agregarCarta(este.mazo.tomarCarta())
                // Se penaliza al jugador humano agregándole una carta del mazo.

                eventManager.notifyListenersCardsHumanPlayerUpdate();
                //gestorEventos.notificarActualizaciónCartasJugadorHumano()
                // Notifica a los observadores para actualizar la visualización de las cartas del jugador humano.
            }
        }
    }


    //Toma una carta del mazo y la agrega a la mano del jugador especificado.
//También restablece el estado de protección Uno del jugador.
//@param playerWhoTakes el jugador que toma la carta ("HUMAN_PLAYER" o "MACHINE_PLAYER")
    public void takeCardPlayer(String playerWhoTakes) {
        //pública vacío tomarCartaJugador(String jugadorQueToma)
        // Método que permite a un jugador (humano o máquina) tomar una carta del mazo.

        if (playerWhoTakes.equals("HUMAN_PLAYER")) {
            //si (jugadorQueToma.equals("HUMAN_PLAYER"))
            // Verifica si el jugador que toma la carta es el jugador humano.

            this.humanPlayer.addCard(this.deck.takeCard());
            //este.jugadorHumano.agregarCarta(este.mazo.tomarCarta())
            // Toma una carta del mazo usando `takeCard()` y la agrega a la mano del jugador humano.

            this.humanPlayer.setProtectedByUno(false);
            //este.jugadorHumano.establecerProtegidoPorUno(falso)
            // Restablece el estado de protección Uno del jugador humano a `false`.
        } else {
            //sino
            // Si el jugador que toma la carta es la máquina.

            this.machinePlayer.addCard(this.deck.takeCard());
            //este.jugadorMáquina.agregarCarta(este.mazo.tomarCarta())
            // Toma una carta del mazo usando `takeCard()` y la agrega a la mano del jugador máquina.

            this.machinePlayer.setProtectedByUno(false);
            //este.jugadorMáquina.establecerProtegidoPorUno(falso)
            // Restablece el estado de protección Uno del jugador máquina a `false`.
        }
    }


    //Obtiene las cartas visibles actuales del jugador humano, comenzando desde una posición específica.
//@param posInitCardToShow la posición inicial de las cartas a mostrar
//@return un arreglo de cartas que son visibles actualmente para el jugador humano
    @Override
    public Card[] getCurrentVisibleCardsHumanPlayer(int posInitCardToShow) {
        //@Override pública Carta[] obtenerCartasVisiblesJugadorHumano(int posiciónInicialCartaMostrar)
        // Método que devuelve un subconjunto de cartas visibles del jugador humano, comenzando desde una posición dada.

        int totalCards = this.humanPlayer.getCardsPlayer().size();
        //int totalCartas = este.jugadorHumano.obtenerCartasJugador().tamaño()
        // Obtiene el número total de cartas que tiene el jugador humano.

        int numVisibleCards = Math.min(4, totalCards - posInitCardToShow);
        //int numCartasVisibles = Math.min(4, totalCartas - posiciónInicialCartaMostrar)
        // Calcula cuántas cartas se pueden mostrar, tomando el mínimo entre 4 cartas y las cartas restantes desde la posición inicial.

        Card[] cards = new Card[numVisibleCards];
        //Carta[] cartas = nueva Carta[numCartasVisibles]
        // Crea un arreglo de cartas con el tamaño igual al número de cartas visibles.

        for (int i = 0; i < numVisibleCards; i++) {
            //para (int i = 0; i < numCartasVisibles; i++)
            // Itera sobre el número de cartas visibles.

            cards[i] = this.humanPlayer.getCard(posInitCardToShow + i);
            //cartas[i] = este.jugadorHumano.obtenerCarta(posiciónInicialCartaMostrar + i)
            // Asigna al arreglo la carta obtenida desde la posición inicial más el índice actual.
        }

        return cards;
        //retornar cartas
        // Devuelve el arreglo de cartas visibles del jugador humano.
    }


    //Obtiene las cartas visibles actuales del jugador máquina.
//@return un arreglo de cartas que son visibles actualmente para el jugador humano
    @Override
    public Card[] getCurrentVisibleCardsMachinePlayer() {
        //@Override pública Carta[] obtenerCartasVisiblesJugadorMáquina()
        // Método que devuelve un subconjunto de cartas visibles del jugador máquina.

        int totalCards = this.machinePlayer.getCardsPlayer().size();
        //int totalCartas = este.jugadorMáquina.obtenerCartasJugador().tamaño()
        // Obtiene el número total de cartas que tiene el jugador máquina.

        int numVisibleCards = Math.min(4, totalCards);
        //int numCartasVisibles = Math.min(4, totalCartas)
        // Calcula cuántas cartas se pueden mostrar, tomando el mínimo entre 4 cartas y el total de cartas que tiene la máquina.

        Card[] cards = new Card[numVisibleCards];
        //Carta[] cartas = nueva Carta[numCartasVisibles]
        // Crea un arreglo de cartas con el tamaño igual al número de cartas visibles.

        for (int i = 0; i < numVisibleCards; i++) {
            //para (int i = 0; i < numCartasVisibles; i++)
            // Itera sobre el número de cartas visibles.

            cards[i] = this.machinePlayer.getCard(i);
            //cartas[i] = este.jugadorMáquina.obtenerCarta(i)
            // Asigna al arreglo la carta en la posición actual del jugador máquina.
        }

        return cards;
        //retornar cartas
        // Devuelve el arreglo de cartas visibles del jugador máquina.
    }

    //Verifica si el jugador humano ganó.
//@return verdadero si ganó, falso en caso contrario
    @Override
    public Boolean didHumanWin() {
        //@Override pública Booleano humanoGanó()
        // Método que verifica si el jugador humano ha ganado la partida.

        return humanPlayer.getCardsPlayer().isEmpty();
        //retornar jugadorHumano.obtenerCartasJugador().estáVacío()
        // Devuelve verdadero si la lista de cartas del jugador humano está vacía.
    }


    //Verifica si el jugador máquina ganó.
//@return verdadero si ganó, falso en caso contrario
    @Override
    public Boolean didMachineWin() {
        //@Override pública Booleano máquinaGanó()
        // Método que verifica si el jugador máquina ha ganado la partida.

        return machinePlayer.getCardsPlayer().isEmpty();
        //retornar jugadorMáquina.obtenerCartasJugador().estáVacío()
        // Devuelve verdadero si la lista de cartas del jugador máquina está vacía.
    }

    //Rellena el mazo de cartas agregando todas las cartas de la mesa de nuevo al mazo.
//Este método se llama cuando el mazo se queda sin cartas durante el juego.
    public void refillDeckOfCards() {
        //pública vacío rellenarMazoDeCartas()
        // Método que toma todas las cartas de la mesa, excepto la última, y las devuelve al mazo.

        System.out.println("Deck has been refilled.");
        //System.out.println("El mazo ha sido rellenado.")
        // Imprime un mensaje indicando que el mazo ha sido rellenado.

        ArrayList<Card> allCardsInTable = table.getCardsTable();
        //ArrayList<Carta> todasLasCartasEnLaMesa = mesa.obtenerCartasMesa()
        // Obtiene todas las cartas que están actualmente en la mesa.

        ArrayList<Card> allCardsInTableButLastOne = new ArrayList<>(allCardsInTable.subList(0, allCardsInTable.size() - 1));
        //ArrayList<Carta> todasLasCartasMenosLaÚltima = nueva ArrayList<>(todasLasCartasEnLaMesa.subLista(0, todasLasCartasEnLaMesa.tamaño() - 1))
        // Crea una nueva lista que contiene todas las cartas de la mesa, excepto la última.

        deck.refillDeck(allCardsInTableButLastOne);
        //mazo.rellenarMazo(todasLasCartasMenosLaÚltima)
        // Llama al método para rellenar el mazo con las cartas obtenidas de la mesa.
    }

    //Obtiene el mazo de cartas utilizado en el juego.
//@return el mazo de cartas
    public Deck getDeck() {
        //pública Deck obtenerMazo()
        // Método que devuelve el mazo de cartas utilizado en el juego.

        return deck;
        //retornar mazo
        // Devuelve el mazo de cartas.
    }

    //Obtiene el color de la carta actual en la mesa.
//@return el color de la carta
    public String getCurrentTableCardColor() {
        //pública String obtenerColorCartaActualMesa()
        // Método que devuelve el color actual de la carta que está en la cima de la mesa.

        return currentTableCardColor;
        //retornar colorCartaActualMesa
        // Devuelve el color actual de la carta superior en la mesa.
    }

}

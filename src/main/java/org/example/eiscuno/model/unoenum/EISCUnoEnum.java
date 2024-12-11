package org.example.eiscuno.model.unoenum;
//paquete org.example.eiscuno.modelo.unoenum
// Define el paquete donde se encuentra la enumeración `EISCUnoEnum`.

//Representa las diferentes rutas de archivo para las imágenes utilizadas en el juego EISC Uno.
public enum EISCUnoEnum {
    //pública enumeración EISCUnoEnum
    // Define una enumeración que contiene las rutas de archivos para las imágenes del juego.

    FAVICON("favicon.png"),
    //FAVICON("favicon.png")
    // Define una constante para la imagen del ícono principal.

    UNO("images/uno.png"),
    //UNO("images/uno.png")
    // Define una constante para la imagen del logo del juego Uno.

    BACKGROUND_UNO("images/background_uno.png"),
    //BACKGROUND_UNO("images/background_uno.png")
    // Define una constante para la imagen del fondo del juego Uno.

    BUTTON_UNO("images/button_uno.png"),
    //BUTTON_UNO("images/button_uno.png")
    // Define una constante para la imagen del botón principal.

    BUTTON_EXIT("images/button_exit.png"),
    //BUTTON_EXIT("images/button_exit.png")
    // Define una constante para la imagen del botón de salida.

    BUTTON_BACK("images/button_back.png"),
    //BUTTON_BACK("images/button_back.png")
    // Define una constante para la imagen del botón de retroceso.

    BUTTON_NEXT("images/button_next.png"),
    //BUTTON_NEXT("images/button_next.png")
    // Define una constante para la imagen del botón siguiente.

    CARD_UNO("cards-uno/card_uno.png"),
    //CARD_UNO("cards-uno/card_uno.png")
    // Define una constante para la imagen de una carta UNO.

    DECK_OF_CARDS("cards-uno/deck_of_cards.png"),
    //DECK_OF_CARDS("cards-uno/deck_of_cards.png")
    // Define una constante para la imagen del mazo de cartas UNO.

    WILD("cards-uno/wild.png"),
    //WILD("cards-uno/wild.png")
    // Define una constante para la carta de cambio de color (Wild).

    TWO_WILD_DRAW_BLUE("cards-uno/2_wild_draw_blue.png"),
    TWO_WILD_DRAW_GREEN("cards-uno/2_wild_draw_green.png"),
    TWO_WILD_DRAW_YELLOW("cards-uno/2_wild_draw_yellow.png"),
    TWO_WILD_DRAW_RED("cards-uno/2_wild_draw_red.png"),
    // Define constantes para las cartas Wild Draw 2 de diferentes colores.

    FOUR_WILD_DRAW("cards-uno/4_wild_draw.png"),
    // Define una constante para la carta Wild Draw 4.

    SKIP_BLUE("cards-uno/skip_blue.png"),
    SKIP_YELLOW("cards-uno/skip_yellow.png"),
    SKIP_GREEN("cards-uno/skip_green.png"),
    SKIP_RED("cards-uno/skip_red.png"),
    // Define constantes para las cartas de saltar turno (Skip) de diferentes colores.

    REVERSE_BLUE("cards-uno/reverse_blue.png"),
    REVERSE_YELLOW("cards-uno/reverse_yellow.png"),
    REVERSE_GREEN("cards-uno/reverse_green.png"),
    REVERSE_RED("cards-uno/reverse_red.png"),
    // Define constantes para las cartas de cambio de sentido (Reverse) de diferentes colores.

    GREEN_0("cards-uno/0_green.png"),
    GREEN_1("cards-uno/1_green.png"),
    GREEN_2("cards-uno/2_green.png"),
    GREEN_3("cards-uno/3_green.png"),
    GREEN_4("cards-uno/4_green.png"),
    GREEN_5("cards-uno/5_green.png"),
    GREEN_6("cards-uno/6_green.png"),
    GREEN_7("cards-uno/7_green.png"),
    GREEN_8("cards-uno/8_green.png"),
    GREEN_9("cards-uno/9_green.png"),
    // Define constantes para las cartas verdes numeradas del 0 al 9.

    YELLOW_0("cards-uno/0_yellow.png"),
    YELLOW_1("cards-uno/1_yellow.png"),
    YELLOW_2("cards-uno/2_yellow.png"),
    YELLOW_3("cards-uno/3_yellow.png"),
    YELLOW_4("cards-uno/4_yellow.png"),
    YELLOW_5("cards-uno/5_yellow.png"),
    YELLOW_6("cards-uno/6_yellow.png"),
    YELLOW_7("cards-uno/7_yellow.png"),
    YELLOW_8("cards-uno/8_yellow.png"),
    YELLOW_9("cards-uno/9_yellow.png"),
    // Define constantes para las cartas amarillas numeradas del 0 al 9.

    BLUE_0("cards-uno/0_blue.png"),
    BLUE_1("cards-uno/1_blue.png"),
    BLUE_2("cards-uno/2_blue.png"),
    BLUE_3("cards-uno/3_blue.png"),
    BLUE_4("cards-uno/4_blue.png"),
    BLUE_5("cards-uno/5_blue.png"),
    BLUE_6("cards-uno/6_blue.png"),
    BLUE_7("cards-uno/7_blue.png"),
    BLUE_8("cards-uno/8_blue.png"),
    BLUE_9("cards-uno/9_blue.png"),
    // Define constantes para las cartas azules numeradas del 0 al 9.

    RED_0("cards-uno/0_red.png"),
    RED_1("cards-uno/1_red.png"),
    RED_2("cards-uno/2_red.png"),
    RED_3("cards-uno/3_red.png"),
    RED_4("cards-uno/4_red.png"),
    RED_5("cards-uno/5_red.png"),
    RED_6("cards-uno/6_red.png"),
    RED_7("cards-uno/7_red.png"),
    RED_8("cards-uno/8_red.png"),
    RED_9("cards-uno/9_red.png");
    // Define constantes para las cartas rojas numeradas del 0 al 9.

    private final String filePath;
    //privada final Cadena rutaArchivo
    // Declara un atributo que almacena la ruta completa del archivo de la imagen.

    private static final String PATH = "/org/example/eiscuno/";
    //privada estática final Cadena RUTA = "/org/example/eiscuno/"
    // Define la ruta base común a todas las imágenes.

    //Constructor para la enumeración EISCUnoEnum.
    //
    // @param filePath la ruta del archivo de la imagen relativa al directorio base.
    EISCUnoEnum(String filePath) {
        //EISCUnoEnum(Cadena rutaArchivo)
        // Constructor que recibe la ruta relativa y construye la ruta completa.

        this.filePath = PATH + filePath;
        //este.rutaArchivo = RUTA + rutaArchivo
        // Combina la ruta base con la ruta específica del archivo.
    }

    //Obtiene la ruta completa del archivo de la imagen.
    //
    // @return la ruta completa del archivo de la imagen.
    public String getFilePath() {
        //pública Cadena obtenerRutaArchivo()
        // Método que devuelve la ruta completa del archivo.

        return filePath;
        //retornar rutaArchivo
        // Retorna la ruta completa del archivo de la imagen.
    }
}

package org.example.eiscuno.model.card;
// paquete org.example.eiscuno.modelo.carta;
// Define el paquete al que pertenece esta clase, que organiza las clases relacionadas con las cartas en el juego.

import javafx.scene.image.Image;
// importar javafx.escena.imagen.Imagen;
// Importa la clase `Image` de JavaFX, que representa una imagen en el programa.

import javafx.scene.image.ImageView;
// importar javafx.escena.imagen.VistaImagen;
// Importa la clase `ImageView` de JavaFX, que se utiliza para mostrar imágenes en la interfaz gráfica.

// Representa una carta de juego en el juego de Uno.
// Cada carta tiene una URL para su imagen, un valor (como número o carta especial) y un color.
public class Card {
    private String url; // La URL de la imagen de la carta.
    private String value; // El valor de la carta (por ejemplo, número o valor especial).
    private String color; // El color de la carta.
    private Image image; // El objeto `Image` de JavaFX que representa la imagen de la carta.
    private ImageView cardImageView; // El objeto `ImageView` de JavaFX para mostrar la imagen de la carta.

    // Construye un nuevo objeto de tipo Card con la URL, el valor y el color especificados.
    // @param url la URL de la imagen de la carta.
    // @param value el valor de la carta.
    // @param color el color de la carta.
    public Card(String url, String value, String color) {
        this.url = url;
        // este.url = url;
        // Asigna la URL proporcionada al atributo `url` de la carta.

        this.value = value;
        // este.valor = valor;
        // Asigna el valor proporcionado al atributo `value` de la carta.

        this.color = color;
        // este.color = color;
        // Asigna el color proporcionado al atributo `color` de la carta.

        this.image = new Image(String.valueOf(getClass().getResource(url)));
        // este.imagen = nueva Imagen(Cadena.valorDe(getClass().obtenerRecurso(url)));
        // Obtiene la ruta del recurso (URL) y crea una instancia de la imagen de la carta.

        this.cardImageView = createCardImageView();
        // este.vistaImagenCarta = crearVistaImagenCarta();
        // Crea y asigna un objeto `ImageView` con la imagen de la carta y configuraciones predefinidas.
    }

    // Crea un objeto `ImageView` de JavaFX para la imagen de la carta con configuraciones predefinidas.
    // @return el objeto `ImageView` para la imagen de la carta.
    private ImageView createCardImageView() {
        ImageView card = new ImageView(this.image);
        // VistaImagen carta = nueva VistaImagen(esta.imagen);
        // Crea un nuevo objeto `ImageView` basado en la imagen de la carta.

        card.setY(16);
        // carta.establecerY(16);
        // Ajusta la posición vertical inicial de la imagen.

        card.setFitHeight(90);
        // carta.establecerAlturaAjustada(90);
        // Configura la altura ajustada de la imagen.

        card.setFitWidth(70);
        // carta.establecerAnchuraAjustada(70);
        // Configura la anchura ajustada de la imagen.

        return card;
        // retornar carta;
        // Devuelve el objeto `ImageView` creado.
    }

    // Recupera el objeto `ImageView` de JavaFX para mostrar la imagen de la carta.
    // @return el objeto `ImageView` para la imagen de la carta.
    public ImageView getCard() {
        return cardImageView;
        // retornar vistaImagenCarta;
        // Devuelve el objeto `ImageView` asociado a la carta.
    }

    // Recupera el objeto `Image` de JavaFX que representa la imagen de la carta.
    // @return el objeto `Image` de la imagen de la carta.
    public Image getImage() {
        return image;
        // retornar imagen;
        // Devuelve la imagen asociada a la carta.
    }

    // Recupera el valor de la carta.
    // @return el valor de la carta.
    public String getValue() {
        return value;
        // retornar valor;
        // Devuelve el valor (número o especial) de la carta.
    }

    // Recupera el color de la carta.
    // @return el color de la carta.
    public String getColor() {
        return color;
        // retornar color;
        // Devuelve el color de la carta.
    }
}

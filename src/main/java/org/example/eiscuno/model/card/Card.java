package org.example.eiscuno.model.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a game card in the Uno game.
 * Each card has an image URL, a value (number or special card), and a color.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class Card {
    /**
     * URL representing the file path of the card image.
     *
     * @serial
     * @since 1.0
     */
    private String url;

    /**
     * Value of the card, such as a number (0-9) or action (e.g., "+2", "SKIP").
     *
     * @serial
     * @since 1.0
     */
    private String value;

    /**
     * Color of the card, such as "RED", "BLUE", "GREEN", or "YELLOW".
     * For special cards (e.g., WILD), this can be "NON_COLOR".
     *
     * @serial
     * @since 1.0
     */
    private String color;

    /**
     * Image instance representing the visual representation of the card.
     *
     * @serialField
     * @since 1.0
     */
    private Image image;

    /**
     * ImageView instance used to display the card in the user interface.
     *
     * @serialField
     * @since 1.0
     */
    private ImageView cardImageView;


    /**
     * Constructs a new Card object with the specified URL, value, and color.
     *
     * @param url the URL of the card's image.
     * @param value the value of the card.
     * @param color the color of the card.
     */
    public Card(String url, String value, String color) {
        this.url = url;
        this.value = value;
        this.color = color;
        this.image = new Image(String.valueOf(getClass().getResource(url)));
        this.cardImageView = createCardImageView();
    }

    /**
     * Creates an ImageView object for the card's image with predefined settings.
     *
     * @return the ImageView object for the card's image.
     */
    private ImageView createCardImageView() {
        ImageView card = new ImageView(this.image);
        card.setY(16);
        card.setFitHeight(90);
        card.setFitWidth(70);
        return card;
    }

    /**
     * Retrieves the ImageView object to display the card's image.
     *
     * @return the ImageView object for the card's image.
     */
    public ImageView getCard() {
        return cardImageView;
    }

    /**
     * Retrieves the Image object representing the card's image.
     *
     * @return the Image object of the card's image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Retrieves the value of the card.
     *
     * @return the value of the card.
     */
    public String getValue() {
        return value;
    }

    /**
     * Retrieves the color of the card.
     *
     * @return the color of the card.
     */
    public String getColor() {
        return color;
    }
}

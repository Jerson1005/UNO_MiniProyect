package org.example.eiscuno.model.exception;
// paquete org.example.eiscuno.modelo.excepción;
// Define el paquete al que pertenece esta clase, que organiza las clases relacionadas con excepciones personalizadas.

public class UnoException extends Exception {
// clase UnoException extiende Excepción;
// Define una clase `UnoException` que extiende de `Exception` para crear una excepción personalizada relacionada con el juego Uno.

    // Construye una nueva UnoException con un mensaje de error predeterminado.
    public UnoException() {
        super("This is a custom exception message.");
        // super("Este es un mensaje de excepción personalizado.");
        // Llama al constructor de la clase base `Exception` con un mensaje predeterminado.
    }

    // Construye una nueva UnoException con el mensaje de error especificado.
    public UnoException(String message) {
        super(message);
        // super(mensaje);
        // Llama al constructor de la clase base `Exception` con un mensaje específico proporcionado.
    }

    // Construye una nueva UnoException con el mensaje de error especificado y la causa.
    public UnoException(String message, Throwable cause) {
        super(message, cause);
        // super(mensaje, causa);
        // Llama al constructor de la clase base `Exception` con un mensaje y una causa proporcionados.
    }

    // Construye una nueva UnoException con la causa especificada.
    public UnoException(Throwable cause) {
        super(cause);
        // super(causa);
        // Llama al constructor de la clase base `Exception` con la causa proporcionada.
    }
}

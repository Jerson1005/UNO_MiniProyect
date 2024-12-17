package org.example.eiscuno.model.exception;

/**
 * Custom exception class for handling Uno game-specific errors.
 * This class extends the base {@link Exception} class to provide
 * more specific exception handling for the Uno game logic.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Camilo Vidales Lucumi
 * @version 1.0
 * @since 1.0
 */
public class UnoException extends Exception {

    /**
     * Constructs a new UnoException with a default error message.
     */
    public UnoException() {
        super("This is a custom exception message.");
    }

    /**
     * Constructs a new UnoException with the specified error message.
     * @param message the error message.
     */
    public UnoException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnoException with the specified error message and cause.
     * @param message the error message.
     * @param cause the cause of the exception (a Throwable).
     */
    public UnoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UnoException with the specified cause.
     * @param cause the cause of the exception (a Throwable).
     */
    public UnoException(Throwable cause) {
        super(cause);
    }
}

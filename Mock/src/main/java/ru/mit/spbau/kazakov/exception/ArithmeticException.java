package ru.mit.spbau.kazakov.exception;

/**
 * Thrown when unsupported arithmetic operation called.
 */
public class ArithmeticException extends Exception {
    public ArithmeticException(String message) {
        super(message);
    }
}

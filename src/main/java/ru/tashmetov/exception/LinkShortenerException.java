package ru.tashmetov.exception;

public class LinkShortenerException extends RuntimeException{

    /**
     * @param message Сообщение, описывающее причину исключения.
     */
    public LinkShortenerException(String message) {
        super(message);
    }

    /**
     * @param message Сообщение, описывающее причину исключения.
     * @param exception Исключение, которое стало причиной данного исключения.
     */
    public LinkShortenerException(String message, Exception exception) {
        super(message, exception);
    }
}

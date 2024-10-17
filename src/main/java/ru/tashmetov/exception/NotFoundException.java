package ru.tashmetov.exception;

public class NotFoundException extends LinkShortenerException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Exception e) {
        super(message, e);
    }

}
package com.vnazarenko.updater.exceptions;

/**
 * Класс исключение "Некорректный параметр"
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
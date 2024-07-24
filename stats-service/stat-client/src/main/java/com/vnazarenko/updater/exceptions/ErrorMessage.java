package com.vnazarenko.updater.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс с сообщением об ошибке
 */
public record ErrorMessage(String error) {
}
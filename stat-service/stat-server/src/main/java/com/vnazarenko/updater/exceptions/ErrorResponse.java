package com.vnazarenko.updater.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Класс с сообщением об ошибке
 */
@Builder
public record ErrorResponse(String message,
                            String reason,
                            String status,
                            String timestamp) {
}
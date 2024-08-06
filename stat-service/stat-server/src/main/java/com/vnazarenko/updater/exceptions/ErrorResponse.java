package com.vnazarenko.updater.exceptions;

import lombok.Builder;

/**
 * Класс с сообщением об ошибке
 */
@Builder
public record ErrorResponse(String message,
                            String reason,
                            String status,
                            String timestamp) {
}
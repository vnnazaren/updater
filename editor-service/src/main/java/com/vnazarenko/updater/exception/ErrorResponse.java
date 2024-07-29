package com.vnazarenko.updater.exception;

import lombok.Builder;

/**
 * Класс с сообщением об ошибке
 */
@Builder
public record ErrorResponse(String message,     // todo - оставить только нужное
                            String reason,
                            String status,
                            String timestamp) {
}
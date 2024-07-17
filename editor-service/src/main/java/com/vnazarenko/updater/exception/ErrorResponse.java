package com.vnazarenko.updater.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Класс с сообщением об ошибке
 */
@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {  // todo - оставить только нужное

    private String message;

    private String reason;

    private String status;

    private String timestamp;
}
package dev.imkun.kit.core.entity.code;

import dev.imkun.kit.core.service.Code;

/**
 * 默认错误码
 */
public enum  DefaultResponseCode implements Code {
    SUCCESS(200, "success"),
    FAILURE(500, "failure");

    private int status;
    private String message;

    DefaultResponseCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int status() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }
}

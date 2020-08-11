package dev.imkun.kit.core.service;

public interface Code {
    default int status() {
        return 0;
    }
    default String message() {
        return "success";
    }

}

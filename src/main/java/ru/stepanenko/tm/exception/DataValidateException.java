package ru.stepanenko.tm.exception;

import org.jetbrains.annotations.NotNull;

public class DataValidateException extends Exception {

    public DataValidateException(
            @NotNull final String message) {
        super("#"+message);
    }

}

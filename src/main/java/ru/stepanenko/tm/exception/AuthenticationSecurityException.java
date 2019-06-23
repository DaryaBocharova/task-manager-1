package ru.stepanenko.tm.exception;

import org.jetbrains.annotations.NotNull;

public class AuthenticationSecurityException extends Exception {

    public AuthenticationSecurityException(
            @NotNull final String message) {
        super("#"+message);
    }

}

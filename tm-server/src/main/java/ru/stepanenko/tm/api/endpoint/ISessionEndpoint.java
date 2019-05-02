package ru.stepanenko.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.entity.Session;
import ru.stepanenko.tm.exception.AuthenticationSecurityException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;

@WebService
public interface ISessionEndpoint {

    @WebMethod
    Session openSession(@WebParam(name = "login") @NotNull final String login,
                        @WebParam(name = "password") @NotNull final String password) throws AuthenticationSecurityException, IOException;

    @WebMethod
    boolean validateSession(@WebParam(name = "session") @Nullable final Session session) throws AuthenticationSecurityException;

    @WebMethod
    Session closeSession(@WebParam(name = "session") @NotNull final Session session) throws AuthenticationSecurityException;
}

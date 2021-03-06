package ru.stepanenko.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.stepanenko.tm.api.endpoint.ISessionEndpoint;
import ru.stepanenko.tm.api.service.ISessionService;
import ru.stepanenko.tm.api.service.IUserService;
import ru.stepanenko.tm.model.dto.SessionDTO;
import ru.stepanenko.tm.model.dto.UserDTO;
import ru.stepanenko.tm.exception.AuthenticationSecurityException;
import ru.stepanenko.tm.exception.DataValidateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Controller
@WebService
public class SessionEndpoint implements ISessionEndpoint {

    @NotNull
    private final IUserService userService;

    @NotNull
    private final ISessionService sessionService;

    @Autowired
    public SessionEndpoint(
            @NotNull final IUserService userService,
            @NotNull final ISessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Override
    @WebMethod
    public SessionDTO openSession(
            @WebParam(name = "login") @Nullable final String login,
            @WebParam(name = "password") @Nullable final String password
    ) throws AuthenticationSecurityException, DataValidateException {
        @NotNull UserDTO loggedUser = userService.authenticationUser(login, password);
        return sessionService.create(loggedUser);
    }

    @Override
    @WebMethod
    public void validateSession(
            @WebParam(name = "session") @Nullable final SessionDTO sessionDTO
    ) throws AuthenticationSecurityException, DataValidateException {
        sessionService.validate(sessionDTO);
    }

    @Override
    @WebMethod
    public void validateAdminSession(
            @WebParam(name = "session") @Nullable final SessionDTO sessionDTO
    ) throws AuthenticationSecurityException, DataValidateException {
        sessionService.validateAdmin(sessionDTO);
    }

    @Override
    @WebMethod
    public void closeSession(
            @WebParam(name = "session") @Nullable final SessionDTO sessionDTO
    ) throws AuthenticationSecurityException, DataValidateException {
        sessionService.validate(sessionDTO);
        sessionService.remove(sessionDTO.getId());
    }

    @WebMethod
    public SessionDTO findOneSession(
            @WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
            @WebParam(name = "id") @Nullable final String id
    ) throws AuthenticationSecurityException, DataValidateException {
        sessionService.validateAdmin(sessionDTO);
        return sessionService.findOne(id);
    }
}

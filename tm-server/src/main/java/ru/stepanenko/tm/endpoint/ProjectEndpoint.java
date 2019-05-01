package ru.stepanenko.tm.endpoint;

import com.sun.corba.se.spi.activation.EndPointInfo;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.stepanenko.tm.api.endpoint.IProjectEndpoint;
import ru.stepanenko.tm.api.service.IProjectService;
import ru.stepanenko.tm.api.service.IServiceLocator;
import ru.stepanenko.tm.api.service.ISessionService;
import ru.stepanenko.tm.entity.Project;
import ru.stepanenko.tm.entity.Session;
import ru.stepanenko.tm.exception.session.InvalidSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.Collection;

@WebService
@NoArgsConstructor
public class ProjectEndpoint implements IProjectEndpoint {

    @NotNull
    private IProjectService projectService;

    @NotNull
    private ISessionService sessionService;

    public ProjectEndpoint(@NotNull final IServiceLocator serviceLocator) {
        this.projectService = serviceLocator.getProjectService();
        this.sessionService = serviceLocator.getSessionService();
    }

    @Override
    @WebMethod
    public Project createProject(@WebParam(name = "session") @NotNull final Session session,
                          @WebParam(name = "name") @NotNull final String name,
                          @WebParam(name = "description") @NotNull final String description) throws InvalidSessionException{
        if(!sessionService.validate(session)) throw new InvalidSessionException("Session is invalid!");
        return projectService.create(name, description, session.getUserId());
    }

    @Override
    @WebMethod
    public Project editProject(@WebParam(name = "session") @NotNull final Session session,
                        @WebParam(name = "id") @NotNull final String id,
                        @WebParam(name = "name") @NotNull final String name,
                        @WebParam(name = "description") @NotNull final String description,
                        @WebParam(name = "status") @NotNull final String status) throws InvalidSessionException{
        if(!sessionService.validate(session)) throw new InvalidSessionException("Session is invalid!");
        return projectService.edit(id, name, description, status);
    }

    @Override
    @WebMethod
    public Collection<Project> findAllProjectByUserId(@WebParam(name = "session") @NotNull final Session session) throws InvalidSessionException{
        if(!sessionService.validate(session)) throw new InvalidSessionException("Session is invalid!");
        return projectService.findAllByUserId(session.getUserId());
    }

    @Override
    @WebMethod
    public void removeAllProjectByUserId(@WebParam(name = "session") @NotNull final Session session) throws InvalidSessionException{
        if(!sessionService.validate(session)) throw new InvalidSessionException("Session is invalid!");
        projectService.removeAllByUserId(session.getUserId());
    }

    @Override
    @WebMethod
    public Collection<Project> sortAllProjectByUserId(@WebParam(name = "session") @NotNull final Session session,
                                               @WebParam(name = "comparator") @NotNull final String comparator) throws InvalidSessionException{
        if(!sessionService.validate(session)) throw new InvalidSessionException("Session is invalid!");
        return projectService.sortAllByUserId(session.getUserId(), comparator);
    }

    @Override
    @WebMethod
    public Collection<Project> findAllProjectByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final Session session,
                                                                @WebParam(name = "name") @NotNull final String name,
                                                                @WebParam(name = "description") @NotNull final String description) throws InvalidSessionException{
        if(!sessionService.validate(session)) throw new InvalidSessionException("Session is invalid!");
        return projectService.findAllByPartOfNameOrDescription(name, description, session.getUserId());
    }
}
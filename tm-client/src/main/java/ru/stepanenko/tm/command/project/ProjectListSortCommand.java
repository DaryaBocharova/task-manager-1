package ru.stepanenko.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.api.service.ISessionService;
import ru.stepanenko.tm.api.service.ITerminalService;
import ru.stepanenko.tm.api.command.AbstractCommand;
import ru.stepanenko.tm.endpoint.*;

import javax.inject.Inject;

@NoArgsConstructor
public class ProjectListSortCommand implements AbstractCommand {

    @Inject
    @NotNull
    ProjectEndpoint projectEndpoint;

    @Inject
    @NotNull
    SessionEndpoint sessionEndpoint;

    @Inject
    @NotNull
    ISessionService sessionService;

    @Inject
    @NotNull
    ITerminalService terminalService;

    @Override
    public String getName() {
        return "project-list-sort";
    }

    @Override
    public String getDescription() {
        return "Sorted list project by: order, dateBegin, dateEnd or status";
    }

    @Override
    public void execute(
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception {
        @Nullable final SessionDTO currentSession = sessionService.getCurrentSession();
        sessionEndpoint.validateSession(currentSession);
        System.out.println("Please input how to sort list project(order, dateBegin, dateEnd, status)");
        @Nullable final String parameter = terminalService.nextLine();
        System.out.println("Project list sorted by " + parameter + " :");
        projectEndpoint.sortAllProjectByUserId(currentSession, parameter)
                .forEach(e -> System.out.println("id: " + e.getId() +
                        " name: " + e.getName() +
                        " description: " + e.getDescription() +
                        " data start: " + e.getDateBegin() +
                        " data end: " + e.getDateEnd() +
                        " status: " + e.getStatus()));
    }
}

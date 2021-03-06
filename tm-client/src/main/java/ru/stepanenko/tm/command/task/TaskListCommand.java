package ru.stepanenko.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stepanenko.tm.api.service.ISessionService;
import ru.stepanenko.tm.api.service.ITerminalService;
import ru.stepanenko.tm.api.command.ICommand;
import ru.stepanenko.tm.endpoint.*;

@Component
@NoArgsConstructor
public final class TaskListCommand implements ICommand {

    @NotNull
    @Autowired
    ProjectEndpoint projectEndpoint;

    @NotNull
    @Autowired
    TaskEndpoint taskEndpoint;

    @NotNull
    @Autowired
    SessionEndpoint sessionEndpoint;

    @NotNull
    @Autowired
    ISessionService sessionService;

    @NotNull
    @Autowired
    ITerminalService terminalService;

    @Override
    public String getName() {
        return "task-list";
    }

    @Override
    public String getDescription() {
        return "Show all tasks or selected task.";
    }

    @Override
    public void execute(
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception {
        @Nullable final SessionDTO currentSession = sessionService.getCurrentSession();
        sessionEndpoint.validateSession(currentSession);
        System.out.println("Please input project id or press ENTER for print all tasks: ");
        @Nullable final String id = terminalService.nextLine();
        if ("".equals(id)) {
            System.out.println("List of tasks:");
            taskEndpoint.findAllTaskByUserId(currentSession)
                    .forEach(e -> System.out.println(
                            "id:" + e.getId() +
                                    " name:" + e.getName() +
                                    " project_id:" + e.getProjectId()));
            return;
        }
        projectEndpoint.findOneProject(currentSession, id);
        System.out.println("List task for project id:" + id + " is empty!");
        taskEndpoint.findAllTaskByProjectId(currentSession, id)
                .forEach(e -> System.out.println("id:" + e.getId() + " name:" + e.getName()));
    }
}

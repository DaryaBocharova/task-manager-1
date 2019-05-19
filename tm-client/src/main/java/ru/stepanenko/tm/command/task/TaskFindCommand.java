package ru.stepanenko.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.api.service.ITerminalService;
import ru.stepanenko.tm.command.AbstractCommand;
import ru.stepanenko.tm.endpoint.*;

import java.util.Collection;

@NoArgsConstructor
public class TaskFindCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "task-find";
    }

    @Override
    public String getDescription() {
        return "Find task by part of name or description.";
    }

    @Override
    public void execute() throws AuthenticationSecurityException_Exception, DataValidateException_Exception {
        @NotNull final ProjectEndpoint projectEndpoint = endpointServiceLocator.getProjectEndpoint();
        @NotNull final TaskEndpoint taskEndpoint = endpointServiceLocator.getTaskEndpoint();
        @NotNull final ITerminalService terminalService = endpointServiceLocator.getTerminalService();
        @Nullable final SessionDTO currentSession = endpointServiceLocator.getSessionDTO();
        endpointServiceLocator.getSessionEndpoint().validateSession(currentSession);
        System.out.println("Please input part of task's name for search:");
        @Nullable final String name = terminalService.nextLine();
        System.out.println("Please input part of task's description for search:");
        @Nullable final String description = terminalService.nextLine();

        @Nullable final Collection<TaskDTO> findTasks = taskEndpoint.findAllTaskByPartOfNameOrDescription(currentSession, name, description);
        if (findTasks == null || findTasks.isEmpty()) {
            System.out.println("Tasks does not found!");
            return;
        }

        System.out.println("Find tasks by part of name '" + name + "' or part of description '" + description + "' :");
        findTasks.forEach(e -> System.out.println("id: " + e.getId() +
                " name: " + e.getName() +
                " description: " + e.getDescription() +
                " data start: " + e.getDateBegin() +
                " data end: " + e.getDateEnd() +
                " status: " + e.getStatus()));
    }
}



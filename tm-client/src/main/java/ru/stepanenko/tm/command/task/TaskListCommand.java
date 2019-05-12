package ru.stepanenko.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.api.service.ITerminalService;
import ru.stepanenko.tm.command.AbstractCommand;
import ru.stepanenko.tm.endpoint.*;

import java.util.Collection;

@NoArgsConstructor
public final class TaskListCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "task-list";
    }

    @Override
    public String getDescription() {
        return "Show all tasks or selected task.";
    }

    @Override
    public void execute() throws AuthenticationSecurityException_Exception {
        @NotNull final ProjectEndpoint projectEndpoint = endpointServiceLocator.getProjectEndpoint();
        @NotNull final TaskEndpoint taskEndpoint = endpointServiceLocator.getTaskEndpoint();
        @NotNull final ITerminalService terminalService = endpointServiceLocator.getTerminalService();
        @NotNull final Session currentSession = endpointServiceLocator.getSession();
        endpointServiceLocator.getSessionEndpoint().validateSession(currentSession);
        System.out.print("Please input project id or press ENTER for print all tasks:");
        @Nullable final Collection<Task> findTasks;
        @NotNull final String id = terminalService.nextLine();
        if ("".equals(id)) {
            findTasks = taskEndpoint.findAllTaskByUserId(currentSession);
            if (findTasks == null || findTasks.isEmpty()) {
                System.out.println("List task is empty!");
                return;
            }
            findTasks.forEach(e -> System.out.println("id:" + e.getId() + " name:" + e.getName() + " project_id:" + e.getProjectId()));
            return;
        }

        @Nullable final Project project = projectEndpoint.findOneProject(currentSession, id);
        if (project != null) {
            findTasks = taskEndpoint.findAllTaskByProjectId(currentSession, id);
            if (findTasks == null || findTasks.isEmpty()) {
                System.out.println("List task for project id:" + id + " is empty!");
                return;
            }
            findTasks.forEach(e -> System.out.println("id:" + e.getId() + " name:" + e.getName()));
        } else {
            System.out.println("Project id: " + id + " does not found!");
        }
    }
}
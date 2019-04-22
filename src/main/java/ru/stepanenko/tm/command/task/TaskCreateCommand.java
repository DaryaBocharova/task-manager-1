package ru.stepanenko.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.api.service.IProjectService;
import ru.stepanenko.tm.api.service.ITaskService;
import ru.stepanenko.tm.api.service.IUserService;
import ru.stepanenko.tm.command.AbstractCommand;
import ru.stepanenko.tm.entity.Project;
import ru.stepanenko.tm.entity.User;

import java.util.Scanner;

public final class TaskCreateCommand extends AbstractCommand {
    @NotNull
    private final IProjectService projectService;
    @NotNull
    private final ITaskService taskService;
    @NotNull
    private final IUserService userService;

    public TaskCreateCommand(@NotNull final IProjectService projectService, @NotNull final ITaskService taskService, @NotNull final IUserService userService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "task-create";
    }

    @Override
    public String getDescription() {
        return "Create new task.";
    }

    @Override
    public void execute() {
        @Nullable
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            System.out.println("This command available only login user!");
            return;
        }
        if (projectService.findAllByUserId(currentUser.getId()).isEmpty()) {
            System.out.println("List of projects is empty!");
            return;
        }
        @NotNull
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of projects:");
        projectService.findAllByUserId(currentUser.getId()).forEach(e -> System.out.println("id: " + e.getId()));
        System.out.println("Please input project id:");
        String projectID = scanner.nextLine();
        @Nullable
        Project project = (projectService.findOne(projectID));
        if (project != null) {
            System.out.println("Please input task name:");
            String name = scanner.nextLine();
            System.out.println("Please input task description:");
            String description = scanner.nextLine();
            if (taskService.create(name, description, projectID, currentUser.getId()) != null) {
                System.out.println("Task " + name + " is create!");
            } else {
                System.out.println("Task " + name + " does not create!");
                System.out.println("Task name or description can't be empty!");
            }
        } else {
            System.out.println("Project id: " + projectID + " does not found!");
        }
    }
}

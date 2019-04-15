package ru.stepanenko.tm.Command;

import ru.stepanenko.tm.api.service.IProjectService;
import ru.stepanenko.tm.api.service.ITaskService;
import ru.stepanenko.tm.entity.Project;
import ru.stepanenko.tm.entity.Task;

import java.util.Scanner;

public class TaskEditCommand extends AbstractCommand {
    IProjectService projectService;
    ITaskService taskService;

    public TaskEditCommand(IProjectService projectService, ITaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "task-edit";
    }

    @Override
    public String getDescription() {
        return "Edit selected task.";
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of projects:");
        projectService.findAll().forEach(e -> System.out.println("id: " + e.getId()));
        System.out.println("Please input project id:");
        String projectID = scanner.nextLine();
        Project project = projectService.findOne(projectID);
        if (project != null) {
            System.out.println("List of task:");
            taskService.findAllByProjectID(projectID).forEach(e -> System.out.println("id: " + e.getId()));
            System.out.println("Please input ID task for edit:");
            String id = scanner.nextLine();
            if (taskService.findOne(id) != null) {
                System.out.println("Input new task's name: ");
                String name = scanner.nextLine();
                System.out.println("Input new task's description: ");
                String description = scanner.nextLine();
                Task task = taskService.edit(id, name, description);
                if (task != null) {
                    System.out.println("Task id: " + task.getId() + "edit is complete!");
                } else {
                    System.out.println("Task name or description can't be empty!");
                }

            } else {
                System.out.println("Task id: " + id + " is not found!");
            }

        } else {
            System.out.println("Project id " + projectID + " does not found!");
        }
    }
}

package ru.stepanenko.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import ru.stepanenko.tm.api.service.IProjectService;
import ru.stepanenko.tm.api.service.ISessionService;
import ru.stepanenko.tm.api.service.ITaskService;
import ru.stepanenko.tm.enumerate.Status;
import ru.stepanenko.tm.exception.AuthenticationSecurityException;
import ru.stepanenko.tm.exception.DataValidateException;
import ru.stepanenko.tm.model.entity.Project;
import ru.stepanenko.tm.model.entity.Task;
import ru.stepanenko.tm.model.entity.User;
import ru.stepanenko.tm.service.ProjectService;
import ru.stepanenko.tm.service.SessionService;
import ru.stepanenko.tm.service.TaskService;
import ru.stepanenko.tm.util.DateFormatter;
import ru.stepanenko.tm.util.FieldConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "task/edit")
public class TaskEditServlet extends HttpServlet {

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @NotNull
    private final ITaskService taskService = TaskService.INSTANCE;

    @NotNull
    private final ISessionService sessionService = SessionService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        @NotNull final HttpSession session = req.getSession();
        try {
            sessionService.validateSession(session);
            @NotNull final User loggedUser = (User) session.getAttribute(FieldConst.USER);
            @NotNull final String taskId = req.getParameter(FieldConst.TASK_ID);
            @NotNull final Task task = taskService.findOne(taskId, loggedUser.getId());
            req.setAttribute(FieldConst.TASK, task);
            req.setAttribute(FieldConst.PROJECTS, projectService.findAllByUserId(loggedUser.getId()));
            req.getRequestDispatcher("/WEB-INF/jsp/task/taskEdit.jsp").forward(req, resp);
        } catch (AuthenticationSecurityException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        } catch (DataValidateException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        @NotNull final HttpSession session = req.getSession();
        try {
            sessionService.validateSession(session);
            @NotNull final User loggedUser = (User) session.getAttribute(FieldConst.USER);
            @NotNull final String projectId = req.getParameter(FieldConst.PROJECT_ID);
            @NotNull final Task task = new Task(
                    req.getParameter(FieldConst.NAME),
                    req.getParameter(FieldConst.DESCRIPTION),
                    DateFormatter.stringToDate(req.getParameter(FieldConst.DATE_BEGIN)),
                    DateFormatter.stringToDate(req.getParameter(FieldConst.DATE_END)),
                    Status.valueOf(req.getParameter(FieldConst.STATUS)),
                    projectId,
                    loggedUser.getId());
            task.setId(req.getParameter(FieldConst.TASK_ID));
            if (projectId == null || "null".equals(projectId)) {
                task.setProjectId(null);
                taskService.edit(task);
                resp.sendRedirect(req.getContextPath() + "/task/list");
                return;
            }
            taskService.edit(task);
            resp.sendRedirect(req.getContextPath() + "/task/list?" + FieldConst.PROJECT_ID + "=" + projectId);
        } catch (AuthenticationSecurityException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        } catch (DataValidateException | ParseException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }
}
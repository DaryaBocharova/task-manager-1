package ru.stepanenko.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.stepanenko.tm.api.endpoint.IProjectEndpoint;
import ru.stepanenko.tm.api.endpoint.ISessionEndpoint;
import ru.stepanenko.tm.api.endpoint.ITaskEndpoint;
import ru.stepanenko.tm.config.AppConfiguration;
import ru.stepanenko.tm.enumerate.Status;
import ru.stepanenko.tm.exception.AuthenticationSecurityException;
import ru.stepanenko.tm.exception.DataValidateException;
import ru.stepanenko.tm.model.dto.ProjectDTO;
import ru.stepanenko.tm.model.dto.SessionDTO;
import ru.stepanenko.tm.model.dto.TaskDTO;
import ru.stepanenko.tm.util.DataGenerator;
import ru.stepanenko.tm.util.HashUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class TaskEndpointTest {

    @Autowired
    private IProjectEndpoint projectEndpoint;

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Autowired
    private ISessionEndpoint sessionEndpoint;

    @Autowired
    private DataGenerator dataGenerator;

    @NotNull
    private SessionDTO currentSession;


    @Before
    public void setUp(
    ) throws DataValidateException, AuthenticationSecurityException {
        dataGenerator.generate();
        currentSession = sessionEndpoint.openSession("admin", HashUtil.md5("admin"));
    }

    @After
    public void tearDown(
    ) throws DataValidateException, AuthenticationSecurityException {
        sessionEndpoint.closeSession(currentSession);
        dataGenerator.cleanUp();
        currentSession = null;
    }

    @Test
    public void taskCRUID(
    ) throws DataValidateException, AuthenticationSecurityException {
        assertNotNull(currentSession);
        taskEndpoint.removeAllTaskByUserId(currentSession);
        assertEquals(0, taskEndpoint.findAllTaskByUserId(currentSession).size());

        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setId(UUID.randomUUID().toString());
        project.setName("New_Project");
        project.setDescription("New_Description");
        project.setUserId(currentSession.getUserId());
        projectEndpoint.createProject(currentSession, project);

        @NotNull final TaskDTO task = new TaskDTO();
        task.setId(UUID.randomUUID().toString());
        task.setName("New_Task");
        task.setDescription("New_Description");
        task.setUserId(currentSession.getUserId());
        task.setProjectId(project.getId());

        taskEndpoint.createTask(currentSession, task);//CREATE
        assertEquals(task.getId(), taskEndpoint.findOneTask(currentSession, task.getId()).getId());//READ
        task.setName("Update_name");
        task.setDescription("Update_description");
        taskEndpoint.editTask(currentSession, task);//UPDATE
        assertEquals(task.getName(), taskEndpoint.findOneTask(currentSession, task.getId()).getName());
        assertEquals(task.getDescription(), taskEndpoint.findOneTask(currentSession, task.getId()).getDescription());
        taskEndpoint.removeTask(currentSession, task.getId());//DELETE
        assertEquals(0, taskEndpoint.findAllTaskByUserId(currentSession).size());

        for (int i = 0; i < 10; i++) {
            @NotNull final TaskDTO testTask = new TaskDTO();
            testTask.setId(UUID.randomUUID().toString());
            testTask.setName("New_Task_" + i);
            testTask.setDescription("New_Description_" + i);
            testTask.setUserId(currentSession.getUserId());
            testTask.setProjectId(project.getId());
            taskEndpoint.createTask(currentSession, testTask);
        }
        assertEquals(10, taskEndpoint.findAllTaskByProjectId(currentSession, project.getId()).size());
        taskEndpoint.removeAllTaskByProjectId(currentSession, project.getId());
        assertEquals(0, taskEndpoint.findAllTaskByProjectId(currentSession, project.getId()).size());
        projectEndpoint.removeAllProjectByUserId(currentSession);
    }

    @Test
    public void sortProject(
    ) throws DataValidateException, AuthenticationSecurityException {
        assertNotNull(currentSession);

        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setId(UUID.randomUUID().toString());
        project.setName("New_Project");
        project.setDescription("New_Description");
        project.setUserId(currentSession.getUserId());
        projectEndpoint.createProject(currentSession, project);

        @NotNull final TaskDTO task1 = new TaskDTO();
        task1.setUserId(currentSession.getUserId());
        task1.setId(UUID.randomUUID().toString());
        task1.setProjectId(project.getId());
        task1.setName("task1");
        task1.setDescription("Description1");
        task1.setStatus(Status.DONE);
        task1.setDateBegin(new Date(1000));
        task1.setDateEnd(new Date(1000));

        @NotNull final TaskDTO task2 = new TaskDTO();
        task2.setUserId(currentSession.getUserId());
        task2.setId(UUID.randomUUID().toString());
        task2.setProjectId(project.getId());
        task2.setName("task2");
        task2.setDescription("Description2");
        task2.setStatus(Status.INPROCESS);
        task2.setDateBegin(new Date(1000000));
        task2.setDateEnd(new Date(1000000));

        @NotNull final TaskDTO task3 = new TaskDTO();
        task3.setId(UUID.randomUUID().toString());
        task3.setUserId(currentSession.getUserId());
        task3.setProjectId(project.getId());
        task3.setName("task3");
        task3.setDescription("Description3");
        task3.setStatus(Status.PLANNED);
        task3.setDateBegin(new Date(0));
        task3.setDateEnd(new Date(0));

        taskEndpoint.removeAllTaskByUserId(currentSession);
        assertTrue(taskEndpoint.findAllTaskByUserId(currentSession).size() == 0);

        taskEndpoint.createTask(currentSession, task1);
        taskEndpoint.createTask(currentSession, task2);
        taskEndpoint.createTask(currentSession, task3);

        @NotNull final List<TaskDTO> sortStatus = new ArrayList<>(taskEndpoint.sortAllTaskByUserId(currentSession, "status"));
        assertEquals(task3.getId(), sortStatus.get(0).getId());
        assertEquals(task2.getId(), sortStatus.get(1).getId());
        assertEquals(task1.getId(), sortStatus.get(2).getId());

        @NotNull final List<TaskDTO> sortDateBegin = new ArrayList<>(taskEndpoint.sortAllTaskByUserId(currentSession, "dateBegin"));
        assertEquals(task2.getId(), sortDateBegin.get(0).getId());
        assertEquals(task1.getId(), sortDateBegin.get(1).getId());
        assertEquals(task3.getId(), sortDateBegin.get(2).getId());

        @NotNull final List<TaskDTO> sortDateEnd = new ArrayList<>(taskEndpoint.sortAllTaskByUserId(currentSession, "dateEnd"));
        assertEquals(task2.getId(), sortDateEnd.get(0).getId());
        assertEquals(task1.getId(), sortDateEnd.get(1).getId());
        assertEquals(task3.getId(), sortDateEnd.get(2).getId());

        taskEndpoint.removeAllTaskByUserId(currentSession);
        projectEndpoint.removeAllProjectByUserId(currentSession);
    }

    @Test
    public void findProject(
    ) throws DataValidateException, AuthenticationSecurityException {
        assertNotNull(currentSession);
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setId(UUID.randomUUID().toString());
        project.setName("New_Project");
        project.setDescription("New_Description");
        project.setUserId(currentSession.getUserId());
        projectEndpoint.createProject(currentSession, project);

        @NotNull final TaskDTO task1 = new TaskDTO();
        task1.setUserId(currentSession.getUserId());
        task1.setProjectId(project.getId());
        task1.setId(UUID.randomUUID().toString());
        task1.setName("Homework");
        task1.setDescription("Make all homework");

        @NotNull final TaskDTO task2 = new TaskDTO();
        task2.setUserId(currentSession.getUserId());
        task2.setProjectId(project.getId());
        task2.setId(UUID.randomUUID().toString());
        task2.setName("Cooking");
        task2.setDescription("Make apple pie!");

        taskEndpoint.removeAllTaskByUserId(currentSession);
        assertTrue(taskEndpoint.findAllTaskByUserId(currentSession).size() == 0);

        taskEndpoint.createTask(currentSession, task1);
        taskEndpoint.createTask(currentSession, task2);
        @NotNull final List<String> findTasksId = taskEndpoint
                .findAllTaskByPartOfNameOrDescription(currentSession, "Home", "apple")
                .stream()
                .map(TaskDTO::getId)
                .collect(Collectors.toList());
        assertTrue(findTasksId.contains(task1.getId()));
        assertTrue(findTasksId.contains(task2.getId()));

        taskEndpoint.removeAllTaskByUserId(currentSession);
        projectEndpoint.removeAllProjectByUserId(currentSession);
    }
}
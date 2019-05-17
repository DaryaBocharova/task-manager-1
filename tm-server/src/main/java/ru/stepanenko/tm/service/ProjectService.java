package ru.stepanenko.tm.service;

import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.api.repository.IProjectRepository;
import ru.stepanenko.tm.api.repository.IUserRepository;
import ru.stepanenko.tm.api.service.IProjectService;
import ru.stepanenko.tm.model.dto.ProjectDTO;
import ru.stepanenko.tm.model.entity.Project;
import ru.stepanenko.tm.exception.DataValidateException;
import ru.stepanenko.tm.model.entity.User;
import ru.stepanenko.tm.repository.ProjectRepository;
import ru.stepanenko.tm.repository.UserRepository;
import ru.stepanenko.tm.util.DataValidator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

@AllArgsConstructor
public final class ProjectService implements IProjectService {

    @NotNull
    final EntityManagerFactory entityManagerFactory;

    @Override
    public void create(
            @NotNull final ProjectDTO projectDTO)
            throws DataValidateException {
        DataValidator.validateProjectDTO(projectDTO);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @NotNull final Project project = convertDTOtoProject(projectDTO, entityManager);
            projectRepository.persist(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void edit(
            @NotNull final ProjectDTO projectDTO)
            throws DataValidateException {
        DataValidator.validateProjectDTO(projectDTO);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @NotNull final Project project = convertDTOtoProject(projectDTO, entityManager);
            projectRepository.merge(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ProjectDTO findOne(
            @NotNull final String id,
            @NotNull final String userId)
            throws DataValidateException {
        DataValidator.validateString(id, userId);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository.findOneByUserId(id, getUser(userId, entityManager));
            entityManager.getTransaction().commit();
            if (project==null) throw new DataValidateException("Project not found!");
            return project.getDTO();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void remove(
            @NotNull final String id,
            @NotNull final String userId)
            throws DataValidateException {
        DataValidator.validateString(id, userId);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository.findOneByUserId(id, getUser(userId, entityManager));
            if (project==null) throw new DataValidateException("Project not found!");
            projectRepository.remove(project);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void clear()
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository.removeAll();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ProjectDTO findOne(
            @NotNull final String id)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository.findOne(id);
            entityManager.getTransaction().commit();
            if (project==null) throw new DataValidateException("Project not found!");
            return project.getDTO();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void remove(
            @NotNull final String id)
            throws DataValidateException {
        DataValidator.validateString(id);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository.findOne(id);
            if (project==null) throw new DataValidateException("Project not found!");
            projectRepository.remove(project);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<ProjectDTO> findAll()
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Collection<Project> projects = projectRepository.findAll();
            if (projects==null) throw new DataValidateException("Projects not found!");
            @Nullable final Collection<ProjectDTO> projectsDTO = projects.forEach(e->e.getDTO());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<Project> findAllByUserId(
            @NotNull final String id)
            throws DataValidateException {
        DataValidator.validateString(id);
        try (SqlSession session = entityManagerFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).findAllByUserId(id);
        } catch (Exception e) {
            throw new DataValidateException(e.getMessage());
        }
    }

    @Override
    public void removeAllByUserId(
            @NotNull final String id)
            throws DataValidateException {
        DataValidator.validateString(id);
        @Nullable SqlSession session = null;
        try {
            session = entityManagerFactory.openSession();
            session.getMapper(IProjectRepository.class).removeAllByUserID(id);
            session.commit();
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Collection<Project> sortAllByUserId(
            @NotNull final String id,
            @NotNull final String parameter)
            throws DataValidateException {
        DataValidator.validateString(id, parameter);
        DataValidator.validateParameter(parameter);
        if ("order".equals(parameter)) return findAllByUserId(id);
        try (SqlSession session = entityManagerFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).sortAllByUserId(id, parameter);
        } catch (Exception e) {
            throw new DataValidateException(e.getMessage());
        }
    }

    @Override
    public Collection<Project> findAllByPartOfNameOrDescription(
            @NotNull final String name,
            @NotNull final String description,
            @NotNull final String userId)
            throws DataValidateException {
        DataValidator.validateString(name, description, userId);
        try (SqlSession session = entityManagerFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).findAllByPartOfNameOrDescription(name, description, userId);
        } catch (Exception e) {
            throw new DataValidateException(e.getMessage());
        }
    }

    @Override
    public Project convertDTOtoProject(
            @NotNull final ProjectDTO projectDTO,
            @NotNull final EntityManager entityManager)
            throws DataValidateException {
        @NotNull final Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setDateBegin(projectDTO.getDateBegin());
        project.setDateEnd(projectDTO.getDateEnd());
        project.setUser(getUser(projectDTO.getUserId(), entityManager));
        project.setStatus(projectDTO.getStatus());
        return project;
    }

    private User getUser(@NotNull final String userId, @NotNull final EntityManager em) throws DataValidateException {
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @Nullable final User user = userRepository.findOne(userId);
        if (user==null) throw new DataValidateException("User not found!");
        return user;
    }
}

package ru.stepanenko.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.stepanenko.tm.api.service.IProjectService;
import ru.stepanenko.tm.api.repository.IProjectRepository;
import ru.stepanenko.tm.entity.Project;
import ru.stepanenko.tm.enumerate.Status;
import ru.stepanenko.tm.util.ComparatorUtil;
import ru.stepanenko.tm.util.EnumUtil;
import ru.stepanenko.tm.util.StringValidator;

import java.util.Collection;
import java.util.Date;

public final class ProjectService extends AbstractEntityService<Project, IProjectRepository> implements IProjectService {

    public ProjectService(@NotNull final IProjectRepository projectRepository) {
        super(projectRepository);
    }

    @Override
    public Project create(@NotNull final String name, @NotNull final String description, @NotNull final String userId) {
        if (!StringValidator.validate(name, description, userId)) return null;
        return repository.persist(new Project(name, description, userId));
    }

    @Override
    public Project edit(@NotNull String id, @NotNull String name, @NotNull String description, @NotNull String status, @NotNull String userId) {
        if (!StringValidator.validate(id, name, description, status)) return null;
        if (EnumUtil.stringToStatus(status) == null) return null;
        @NotNull
        Project project = findOne(id, userId);
        project.setName(name);
        project.setDescription(description);
        project.setStatus(EnumUtil.stringToStatus(status));
        if (Status.DONE == EnumUtil.stringToStatus(status)) {
            project.setDateEnd(new Date());
        } else {
            project.setDateEnd(null);
        }
        return repository.merge(project);
    }

    @Override
    public Project findOne(@NotNull String id, @NotNull String userId) {
        if (!StringValidator.validate(id, userId)) return null;
        return repository.findOne(id, userId);
    }

    @Override
    public Project remove(@NotNull String id, @NotNull String userId) {
        if (!StringValidator.validate(id, userId)) return null;
        return repository.remove(id, userId);
    }

    @Override
    public Collection<Project> findAllByUserId(@NotNull final String id) {
        if (!StringValidator.validate(id)) return null;
        return repository.findAllByUserId(id);
    }

    @Override
    public void removeAllByUserId(@NotNull final String id) {
        if (!StringValidator.validate(id)) return;
        repository.removeAllByUserID(id);
    }

    @Override
    public Collection<Project> sortAllByUserId(@NotNull String id, @NotNull String comparator) {
        if (!StringValidator.validate(id, comparator)) return null;
        if ("order".equals(comparator)) return findAllByUserId(id);
        if (ComparatorUtil.getProjectComparator(comparator) == null) return null;
        return repository.sortAllByUserId(id, ComparatorUtil.getProjectComparator(comparator));
    }

    @Override
    public Collection<Project> findAllByPartOfNameOrDescription(@NotNull String name, @NotNull String description, @NotNull String userId) {
        if (!StringValidator.validate(name, description, userId)) return null;
        return repository.findAllByPartOfNameOrDescription(name, description, userId);
    }
}

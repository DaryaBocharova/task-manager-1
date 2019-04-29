package ru.stepanenko.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.entity.Session;
import ru.stepanenko.tm.entity.User;

public interface IUserService extends IAbstractEntityService<User> {

    User create(@NotNull final String login, @NotNull final String password, @NotNull final String role);
    User edit(@NotNull final String id, @NotNull final String login, @NotNull final String password, @NotNull final String role);
    User getCurrentUser();
    User findByLogin(@NotNull final String login);
    void setCurrentUser(@Nullable final User user);
    boolean authenticationUser(@NotNull final String login, @NotNull final String password);
    void load(@NotNull final Session session);
    void save(@NotNull final Session session);
    void loadJaxbXml(@NotNull final Session session);
    void saveJaxbXml(@NotNull final Session session);
    void loadFasterXml(@NotNull final Session session);
    void saveFasterXml(@NotNull final Session session);
    void loadJaxbJSON(@NotNull final Session session);
    void saveJaxbJSON(@NotNull final Session session);
    void loadFasterJSON(@NotNull final Session session);
    void saveFasterJSON(@NotNull final Session session);

}

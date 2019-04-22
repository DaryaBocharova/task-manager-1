package ru.stepanenko.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.stepanenko.tm.entity.User;

public interface IUserRepository extends IAbstractRepository<User> {
    User findByLogin(@NotNull final String login);
}

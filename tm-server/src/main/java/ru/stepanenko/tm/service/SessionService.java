package ru.stepanenko.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.stepanenko.tm.api.repository.ISessionRepository;
import ru.stepanenko.tm.api.repository.IUserRepository;
import ru.stepanenko.tm.api.service.ISessionService;
import ru.stepanenko.tm.entity.Session;
import ru.stepanenko.tm.entity.User;
import ru.stepanenko.tm.enumerate.Role;
import ru.stepanenko.tm.exception.session.InvalidSessionException;
import ru.stepanenko.tm.util.SignatureUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class SessionService extends AbstractEntityService<Session, ISessionRepository> implements ISessionService {

    @NotNull
    private final IUserRepository userRepository;

    public SessionService(@NotNull final ISessionRepository repository, @NotNull final IUserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    public Session create(@NotNull String userId) throws IOException {
        @NotNull final Properties property = new Properties();
        try (InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("application.properties")) {
            property.load(resourceStream);
        } catch (IOException e) {
            throw new IOException(e);
        }
        @NotNull final String cycle = property.getProperty("cycle");
        @NotNull final String salt = property.getProperty("salt");
        @NotNull final Session session = new Session();
        session.setTimeStamp(new Date());
        session.setUserId(userId);
        session.setSignature(SignatureUtil.sign(session, salt, Integer.parseInt(cycle)));
        return repository.persist(session);
    }

    @Override
    public boolean validate(@NotNull Session session) throws InvalidSessionException {
        if (session == null) throw new InvalidSessionException("Session must not be null!");
        if (session.getSignature() == null) throw new InvalidSessionException("Signature must not be null!");
        if (session.getUserId() == null) throw new InvalidSessionException("User must not be null!");
        if (session.getTimeStamp() == null) throw new InvalidSessionException("Time must not be null!");
        if (findOne(session.getId()) == null) throw new InvalidSessionException("Session not found!");

        return session.getSignature().equals(findOne(session.getId()).getSignature());
    }

    @Override
    public boolean validateAdmin(@NotNull Session session) throws InvalidSessionException {
        if (validate(session)) {
            User user = userRepository.findOne(session.getUserId());
            return user.getRole().equals(Role.ADMINISTRATOR);
        }
        return false;
    }
}

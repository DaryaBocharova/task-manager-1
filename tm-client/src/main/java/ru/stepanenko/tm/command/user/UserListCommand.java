package ru.stepanenko.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.command.AbstractCommand;
import ru.stepanenko.tm.endpoint.*;

@NoArgsConstructor
public class UserListCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "user-list";
    }

    @Override
    public String getDescription() {
        return "Print list of users.";
    }

    @Override
    public void execute(
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception {
        @NotNull final UserEndpoint userEndpoint = endpointServiceLocator.getUserEndpoint();
        @Nullable final SessionDTO currentSession = endpointServiceLocator.getSessionDTO();
        endpointServiceLocator.getSessionEndpoint().validateAdminSession(currentSession);
        userEndpoint.findAllUser(currentSession).forEach(e -> System.out.println("id: " + e.getId() +
                " login: " + e.getLogin() +
                " role: " + e.getRole()));
    }
}

package ru.stepanenko.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.model.dto.ProjectDTO;
import ru.stepanenko.tm.model.dto.SessionDTO;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_user")
public class Session extends AbstractEntity {

    @Nullable
    private String signature;

    @Nullable
    private Date timestamp;

    @Nullable
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SessionDTO getDTO(){
        @NotNull final SessionDTO dto = new SessionDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setSignature(signature);
        dto.setTimestamp(timestamp);
        dto.setUserId(user.getId());
        return dto;
    }
}

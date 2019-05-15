package ru.stepanenko.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stepanenko.tm.enumerate.Status;
import ru.stepanenko.tm.util.EnumUtil;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Project extends AbstractEntity implements Serializable {

    @Nullable
    private Date dateBegin = null;

    @Nullable
    private Date dateEnd = null;

    @NotNull
    private String userId = "";

    @Getter
    private Status status = Status.PLANNED;

    public Project(
            @NotNull final String name,
            @NotNull final String description,
            @NotNull final String userId) {
        this.name = name;
        this.description = description;
        this.dateBegin = new Date();
        this.userId = userId;
    }

    public void setStatus(
            @NotNull final String status) {
        this.status = EnumUtil.stringToStatus(status);
    }

    @NotNull
    public String getStatus() {
        return status.toString();
    }

}
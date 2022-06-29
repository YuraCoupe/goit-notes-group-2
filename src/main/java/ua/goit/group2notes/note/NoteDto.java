package ua.goit.group2notes.note;

import ua.goit.group2notes.user.UserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class NoteDto {
    private UUID id;
    private String title;
    private String text;
    private NoteAccessType accessType;
    private UserDto user;

    public NoteDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @NotEmpty(message = "Please, enter Title")
    @Size(min = 5, max = 100, message = "Title must be minimum 5 characters long and no longer then 100")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotEmpty(message = "Please, enter Text")
    @Size(min = 5, max = 10000, message = "Text must be minimum 5 characters long and no longer then 10000")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NotNull(message = "Please, choose Access Type")
    public NoteAccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(NoteAccessType accessType) {
        this.accessType = accessType;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}

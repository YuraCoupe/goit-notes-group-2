package ua.goit.note;

import ua.goit.user.UserDao;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "notes")
public class NoteDao {
    private UUID id;
    private String title;
    private String text;
    private NoteAccessType accessType;
    private UserDao user;

    public NoteDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "access_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public NoteAccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(NoteAccessType accessType) {
        this.accessType = accessType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }
}

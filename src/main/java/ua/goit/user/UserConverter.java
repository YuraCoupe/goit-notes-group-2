package ua.goit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.note.NoteConverter;

import java.util.stream.Collectors;

@Service
public class UserConverter {

    private final NoteConverter noteConverter;

    @Autowired
    public UserConverter(NoteConverter noteConverter) {
        this.noteConverter = noteConverter;
    }

    public UserDto convert(UserDao dao) {
        UserDto dto = new UserDto();
        dto.setId(dao.getId());
        dto.setUsername(dao.getUsername());
        dto.setPassword(dao.getPassword());
        dto.setUserRole(dao.getUserRole());
        if (dao.getNotes() != null) {
            dto.setNotes(dao.getNotes().stream()
                    .map(noteConverter::convert)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public UserDao convert(UserDto dto) {
        UserDao dao = new UserDao();
        dao.setId(dto.getId());
        dao.setUsername(dto.getUsername());
        dao.setPassword(dto.getPassword());
        dao.setUserRole(dto.getUserRole());
        if (dto.getNotes() != null) {
            dao.setNotes(dto.getNotes().stream()
                    .map(noteConverter::convert)
                    .collect(Collectors.toSet()));
        }
        return dao;
    }
}

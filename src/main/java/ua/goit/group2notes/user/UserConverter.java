package ua.goit.group2notes.user;

import org.springframework.stereotype.Service;
import ua.goit.group2notes.note.NoteConverter;

import java.util.stream.Collectors;

@Service
public class UserConverter {

    public UserDto convertU(UserDao dao) {
        NoteConverter noteConverter = new NoteConverter();
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

    public UserDto convert(UserDao dao) {

        UserDto dto = new UserDto();
        dto.setId(dao.getId());
        dto.setUsername(dao.getUsername());
        dto.setPassword(dao.getPassword());
        dto.setUserRole(dao.getUserRole());
        return dto;
    }

    public UserDao convert(UserDto dto) {

        UserDao dao = new UserDao();
        dao.setId(dto.getId());
        dao.setUsername(dto.getUsername());
        dao.setPassword(dto.getPassword());
        dao.setUserRole(dto.getUserRole());
        return dao;
    }
}

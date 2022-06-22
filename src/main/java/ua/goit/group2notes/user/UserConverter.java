package ua.goit.group2notes.user;

import org.springframework.stereotype.Service;
import ua.goit.group2notes.note.NoteConverter;

@Service
public class UserConverter {



    public UserDto convert(UserDao dao) {
        NoteConverter noteConverter=new NoteConverter();
        UserDto dto = new UserDto();
        dto.setId(dao.getId());
        dto.setUsername(dao.getUsername());
        dto.setPassword(dao.getPassword());
        dto.setUserRole(dao.getUserRole());

            return dto;
    }

    public UserDao convert(UserDto dto) {
        NoteConverter noteConverter=new NoteConverter();
        UserDao dao = new UserDao();
        dao.setId(dto.getId());
        dao.setUsername(dto.getUsername());
        dao.setPassword(dto.getPassword());
        dao.setUserRole(dto.getUserRole());

        return dao;
    }
}

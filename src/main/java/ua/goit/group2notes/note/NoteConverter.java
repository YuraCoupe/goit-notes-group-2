package ua.goit.group2notes.note;

import org.springframework.stereotype.Service;
import ua.goit.group2notes.user.UserConverter;

@Service
public class NoteConverter {


    public NoteDto convert(NoteDao dao) {
        UserConverter userConverter=new UserConverter();
        NoteDto dto = new NoteDto();
        dto.setId(dao.getId());
        dto.setTitle(dao.getTitle());
        dto.setText(dao.getText());
        dto.setAccessType(dao.getAccessType());
        dto.setUser(userConverter.convert(dao.getUser()));
        return dto;
    }

    public NoteDao convert(NoteDto dto) {
        UserConverter userConverter=new UserConverter();
        NoteDao dao = new NoteDao();
        dao.setId(dto.getId());
        dao.setTitle(dto.getTitle());
        dao.setText(dto.getText());
        dao.setAccessType(dto.getAccessType());
        dao.setUser(userConverter.convert(dto.getUser()));
        return dao;
    }
}

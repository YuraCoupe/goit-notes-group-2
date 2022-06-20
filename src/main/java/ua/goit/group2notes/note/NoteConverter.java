package ua.goit.group2notes.note;

import org.springframework.stereotype.Service;

@Service
public class NoteConverter {

    public NoteDto convert(NoteDao dao) {
        NoteDto dto = new NoteDto();
        dto.setId(dao.getId());
        dto.setTitle(dao.getTitle());
        dto.setText(dao.getText());
        dto.setAccessType(dao.getAccessType());
        return dto;
    }

    public NoteDao convert(NoteDto dto) {
        NoteDao dao = new NoteDao();
        dao.setId(dto.getId());
        dao.setTitle(dto.getTitle());
        dao.setText(dto.getText());
        dao.setAccessType(dto.getAccessType());
        return dao;
    }
}

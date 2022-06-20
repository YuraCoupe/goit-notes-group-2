package ua.goit.group2notes.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.errorHandling.TitleNotFoundException;
import ua.goit.group2notes.note.NoteConverter;
import ua.goit.group2notes.note.NoteDto;
import ua.goit.group2notes.note.NoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NoteService {
    private NoteRepository noteRepository;
    private NoteConverter noteConverter;

    @Autowired
    public NoteService(NoteRepository noteRepository, NoteConverter noteConverter) {
        this.noteRepository = noteRepository;
        this.noteConverter = noteConverter;
    }

    public void createNote(NoteDto noteDto) {
        noteRepository.save(noteConverter.convert(noteDto));
    }


    public List<NoteDto> getAll() {
        return StreamSupport.stream(noteRepository.findAll().spliterator(), false)
                .map(noteConverter::convert)
                .collect(Collectors.toList());
    }


    public List<NoteDto> getListNotes(UUID id) {

        return StreamSupport.stream(noteRepository.findNoteByUserId(id).spliterator(), false)
                .map(noteConverter::convert)
                .collect(Collectors.toList());

    }


    public NoteDto findById(UUID uuid) {

        return noteConverter.convert(noteRepository.findById(uuid).orElseThrow(() ->
                new TitleNotFoundException(String.format("Title with id %s  not exists", uuid))));

    }

    public void delete(UUID uuid) {
        noteRepository.deleteById(uuid);
    }

    public Optional<NoteDao> findByIdOptional(UUID uuid) {
        return noteRepository.findById(uuid);
    }


}

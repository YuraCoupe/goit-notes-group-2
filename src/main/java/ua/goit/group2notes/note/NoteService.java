package ua.goit.group2notes.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.group2notes.errorHandling.TitleAlreadyExistsException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NoteService {


  private   NodeRepository noteRepository;
 private    NoteConverter converter;
    @Autowired
    public NoteService(NodeRepository noteRepository, NoteConverter converter) {
        this.noteRepository = noteRepository;
        this.converter = converter;
    }
    public List<NoteDto> getListNotes(UUID uuid) {
        List<NoteDao> listNotes = noteRepository.findByUserId(uuid);
        return listNotes.stream().map(n -> converter.convert(n)).collect(Collectors.toList());


    }

    public List<NoteDto> getAll() {

        List<NoteDao> all = noteRepository.findAll();

        return all.stream().map(p->converter.convert(p)).collect(Collectors.toList());

    }

    public void createNote(NoteDto noteDto) {
        validateToCreateNote( noteDto);
        noteRepository.save(converter.convert(noteDto));
    }

    public NoteDto findById(UUID uuid) {
        NoteDao noteDao = noteRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + uuid));
        return converter.convert(noteDao);
    }

    public void delete(UUID uuid) {
        noteRepository.deleteById(uuid);
    }
    public  Optional<NoteDao> findByIdOptional(String uuid){

            return noteRepository.findById(UUID.fromString(uuid));


    }
    public void validateToCreateNote(NoteDto noteDto) {
         NoteDao note = converter.convert(noteDto);
         noteRepository.findByTitle(note.getTitle())
                .ifPresent((title) -> {
                    throw new TitleAlreadyExistsException("Title with username " +title.getTitle()+
                            " already exists");
                });
    }
}

package ua.goit.group2notes.note;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.note.NoteDao;


import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends CrudRepository<NoteDao, UUID> {


    @Query("SELECT n FROM NoteDao n WHERE n.user.id IN (?1)")
    List<NoteDao> findNoteByUserId(UUID uuid);

}
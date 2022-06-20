package ua.goit.group2notes.note;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.goit.group2notes.note.NoteDao;


import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends CrudRepository<NoteDao, UUID> {


    @Query("SELECT n FROM NoteDao n WHERE n.user.id=(:id)")
    List<NoteDao> findNoteByUserId(@Param("id") UUID id);

}
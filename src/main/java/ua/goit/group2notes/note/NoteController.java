package ua.goit.group2notes.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.goit.group2notes.errorHandling.TitleAlreadyExistsException;
import ua.goit.group2notes.user.UserDao;
import ua.goit.group2notes.user.UserDto;
import ua.goit.group2notes.user.UserService;

import javax.validation.Valid;
import java.util.*;
//import ua.goit.errorHandling.TitleAlreadyExistsException;


@Controller
@RequestMapping(path = "/notes")
public class NoteController {

    private UserService userService;
    private NoteService noteService;

    @Autowired
    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }


    @GetMapping("/list")
    public String getNotes(Authentication authentication, Map<String, Object> model) {
        //List<NoteDto> notes = noteService.getAll();
        UserDto userDto = userService.findUserByUsername(authentication.getName());
        List<NoteDto> userNotes = noteService.getListNotes(userDto.getId());
        //model.put("userNotes", userNotes);
        model.put("notes", userNotes);
        return "notes";
    }

    @GetMapping("/create")
    public String noteCreate(Model model) {
        model.addAttribute("note", new NoteDto());
        return "note";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String noteAdd(Authentication authentication, @ModelAttribute("note") @Valid NoteDto noteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "note";
        }
        try {
            UserDto userDto = userService.findUserByUsername(authentication.getName());
            noteDto.setUser(userDto);
            noteService.createNote(noteDto);
        } catch (TitleAlreadyExistsException ex) {
            model.addAttribute("message", ex.getMessage());
            return "note";
        }
        return "redirect:/notes/list";
    }

    @GetMapping("/edit/{id}")
    public String nodeEdit(@PathVariable("id") UUID id, Map<String, Object> model) {

        NoteDto noteDto = noteService.findById(id);
        model.put("note", noteDto);
        return "note";
    }

    @PostMapping("/edit/{id}")
    public String notePostEdit(@PathVariable("id") UUID id, @ModelAttribute("note") NoteDto noteDto) {

        NoteDto note = noteService.findById(id);
        note.setTitle(noteDto.getTitle());
        note.setText(noteDto.getText());
        note.setAccessType(noteDto.getAccessType());
        noteService.createNote(note);
        return "redirect:/notes/list";

    }

    @GetMapping("delete/{id}")
    public String deleteNote(@PathVariable UUID id) {

        noteService.delete(id);
        return "redirect:/notes/list";

    }

    @GetMapping("share/{id}")
    public String noteShow(@AuthenticationPrincipal UserDao user, @PathVariable("id") UUID id, Map<String, Object> model) {
        Optional<NoteDao> note = noteService.findByIdOptional(id);
        if ((note.isPresent() && (user != null)) ||
                (user == null && note.get().getAccessType().equals(NoteAccessType.PUBLIC))) {
            model.put("note", note.get());
        } else {
            model.put("message", Collections.singletonList("We can't find this note "));
        }
        return "sharednote";
    }
}

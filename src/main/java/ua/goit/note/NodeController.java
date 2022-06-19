package ua.goit.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.goit.errorHandling.TitleAlreadyExistsException;
import ua.goit.user.UserDao;
import ua.goit.user.UserDto;
import ua.goit.user.UserService;

import java.util.*;

@Controller
@RequestMapping(path = "/note")
public class NodeController {

    private UserService userService;
    private NoteService noteService;

    @Autowired
    public NodeController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }


    @GetMapping("/list")
    public String getNotes(Authentication authentication, Map<String, Object> model) {
        List<NoteDto> all = noteService.getAll();
        UserDto userDto = userService.findUserByUsername(authentication.getName());
        List<NoteDto> listNotes = noteService.getListNotes(userDto.getId());
        model.put("userNote", listNotes);
        model.put("AllNote", all);
        return "noteList";
    }

    @GetMapping("/create")
    public String noteCreate(Map<String, Object> model) {

        return "noteCreate";
    }

    @PostMapping("/create")
    public String noteAdd(Authentication authentication, @ModelAttribute("node") NoteDto noteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "noteCreate";
        }
       try {
           UserDto userDto = userService.findUserByUsername(authentication.getName());
           noteDto.setUser(userDto);
           noteService.createNote(noteDto);
       }catch (TitleAlreadyExistsException ex){
           model.addAttribute("message", ex.getMessage());
           return "noteCreate";
       }
        return "redirect:/note/list";
    }

    @GetMapping("/edit/{id}")
    public String nodeEdit(@PathVariable("id") UUID id, Map<String, Object> model) {

        NoteDto noteDto = noteService.findById(id);
        model.put("note", noteDto);
        return "noteEdit";
    }

    @PostMapping("/edit/{id}")
    public String notePostEdit(@PathVariable("id") UUID id, @ModelAttribute("note") NoteDto noteDto) {

        NoteDto note = noteService.findById(id);
        note.setTitle(noteDto.getTitle());
        note.setText(noteDto.getText());
        note.setAccessType(noteDto.getAccessType());
        noteService.createNote(note);
        return "redirect:/note/list";

    }

    @GetMapping("delete/{id}")
    public String deleteNote(@PathVariable UUID id) {

        noteService.delete(id);
        return "redirect:/note/list";

    }
    @GetMapping("share/{id}")
    public String noteShow(@AuthenticationPrincipal UserDao user, @PathVariable ("id")UUID id, Map<String,Object> model){
         Optional<NoteDao> note = noteService.findByIdOptional(id);
        if ((note.isPresent() && (user!=null)) ||
                (user == null && note.get().getAccessType().equals(NoteAccessType.PUBLIC))){
            model.put("note", note.get());
        } else {
            model.put("message", Collections.singletonList("We can't find this note "));
        }
        return "noteShare";
    }
}

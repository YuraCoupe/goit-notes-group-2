package ua.goit.group2notes.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping(path = "/**")
    public String getNotesPage() {
        return "redirect:/note/list";
    }

    @PostMapping(path = "/**")
    public String getNotes() {
        return "redirect:/note/list";
    }

    @GetMapping(path = "/index")
    public String getIndexPage() {
        return "redirect:/note/list";
    }

    @PostMapping(path = "/index")
    public String getIndex() {
        return "redirect:/note/list";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }

        return "login";
    }
}

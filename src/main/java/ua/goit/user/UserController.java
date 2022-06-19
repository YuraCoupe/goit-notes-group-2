package ua.goit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.errorHandling.UserAlreadyExistsException;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registerUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userDto.setUserRole(UserRole.ROLE_USER);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userService.save(userDto);
        } catch (UserAlreadyExistsException ex) {
            model.addAttribute("message", ex.getMessage());
            return "registration";
        }
        return "login";
    }

    @ModelAttribute
    public UserDto getDefaultUserDto() {
        return new UserDto();
    }
}

package ua.goit.group2notes.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.group2notes.errorHandling.UserAlreadyExistsException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }
    public List<UserDto> getAll(){
       return userRepository.findAll().stream().filter(p -> !p.getUsername().equals("a"))
                .map(user -> userConverter.convertU(user)).collect(Collectors.toList());

    }

    public UserDto getUserById(UUID id){
        return userConverter.convertU(userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("User not find")));

    }

    public void save(UserDto userDto) {
        validateToCreateUser(userDto);
        userRepository.save(userConverter.convert(userDto));
    }

    public void editUser(UserDto userDto) {
        userRepository.save(userConverter.convert(userDto));
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

    public void validateToCreateUser(UserDto userDto) {
        userRepository.findUserByUsername(userDto.getUsername())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException("User with username " + user.getUsername() +
                            " already exists");
                });
    }

    public UserDto findUserByUsername(String username) {
        return userConverter.convert(userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with username %s not exists", username))));
    }
}

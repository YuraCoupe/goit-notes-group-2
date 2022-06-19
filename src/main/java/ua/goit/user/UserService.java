package ua.goit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.errorHandling.UserAlreadyExistsException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public void save(UserDto userDto) {
        validateToCreateUser(userDto);
        userRepository.save(userConverter.convert(userDto));
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

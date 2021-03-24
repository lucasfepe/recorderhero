package microservices.book.signup.user;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
/*
    private final UserRepositoryOriginal userRepositoryOriginal;

    @GetMapping
    public List<User> getAllUsers() {
        return (List<User>) userRepositoryOriginal.findAll();
    }

    @GetMapping("/{idList}")
    public List<User> getUsersByIdList(@PathVariable final List<Long> idList) {
        return userRepositoryOriginal.findAllByIdIn(idList);
    }*/

//    @GetMapping("/user/{username}")
//    public Optional<User> getUserByUsername(@PathVariable final String username) {
//        return userRepository.findByUsername(username);
//    }

}

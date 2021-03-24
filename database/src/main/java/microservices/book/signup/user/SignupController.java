package microservices.book.signup.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/signup")
public class SignupController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping
    public void postSignup(@RequestBody User user) {
         userService.signup(user);
    }
}

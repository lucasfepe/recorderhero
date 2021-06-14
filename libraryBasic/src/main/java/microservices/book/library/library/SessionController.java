package microservices.book.library.library;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final UserProgressService userProgressService;
    private final UserCoursesService userCoursesService;
    private final SessionService sessionService;

    @Autowired
    Environment env;

    @GetMapping("/getAll")
    String userHasUnifishedCourse(@RequestParam("username") String username) {
        String sessionsJson = sessionService.getAllSession(username);


        return sessionsJson;
    }

}
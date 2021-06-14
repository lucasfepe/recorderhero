package microservices.book.library.library;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.client.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final UserProgressService userProgressService;
    private final UserCoursesService userCoursesService;
    @Autowired
    Environment env;

    @GetMapping("/hasunfinishedcourse")
    boolean userHasUnifishedCourse(@RequestParam("username") String username) {
        Optional<Boolean> hasUnfinishedCourse = userProgressService.isCourseUnfinished(username);
        log.info("User {} has unfinished course: {}", username, hasUnfinishedCourse);

        return hasUnfinishedCourse.orElseGet(() -> { return null; });
    }

    @GetMapping("/courses")
    String getUserCoursesForUser(@RequestParam("username") String username) {
        String userCoursesJson = userCoursesService.getAllUserCoursesByUsername(username);
        log.info("User {} has retrieved course list", username);

        return userCoursesJson;
//                "Working on port: " + env.getProperty("local.server.port");

    }
    @PostMapping("/courses")
    String ji(@RequestBody String gameDTO) {
        log.info("Ji@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
       return "{\"ji\":\"ji\"}";

    }
}
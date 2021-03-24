package microservices.book.multiplication.library;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final UserProgressService userProgressService;
    private final UserCoursesService userCoursesService;

    @GetMapping("/hasunfinishedcourse")
    boolean userHasUnifishedCourse(@RequestParam("username") String username) {
        Optional<Boolean> hasUnfinishedCourse = userProgressService.isCourseUnfinished(username,
                SecurityContextHolder.getContext().getAuthentication().getName(),
                SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        log.info("User {} has unfinished course: {}", username, hasUnfinishedCourse);

        return hasUnfinishedCourse.orElseGet(() -> { return null; });
    }

    @GetMapping("/courses")
    String getUserCoursesForUser(@RequestParam("username") String username) {
        String userCoursesJson = userCoursesService.getAllUserCoursesByUsername(username,
                SecurityContextHolder.getContext().getAuthentication().getName(),
                SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        log.info("User {} has retrieved course list", username);

        return userCoursesJson;
    }
}
package microservices.book.library.library;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.client.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

//    @GetMapping("/hasunfinishedcourse")
//    boolean userHasUnifishedCourse(@RequestParam("username") String username) {
//        Optional<Boolean> hasUnfinishedCourse = userProgressService.isCourseUnfinished(username);
//        log.info("User {} has unfinished course: {}", username, hasUnfinishedCourse);
//
//        return hasUnfinishedCourse.orElseGet(() -> { return null; });
//    }

    @GetMapping("/courses")
    String getUserCoursesForUser(@RequestParam("username") String username, @AuthenticationPrincipal Jwt jwt) {

        String usernameV = jwt.getClaim("preferred_username");
        if(username.equals(usernameV)) {
            String userCoursesJson = userCoursesService.getAllUserCoursesByUsername(username);
            log.info("User {} has retrieved course list", username);

            return userCoursesJson;
        }else{
            return null;
        }
//                "Working on port: " + env.getProperty("local.server.port");

    }
  
}
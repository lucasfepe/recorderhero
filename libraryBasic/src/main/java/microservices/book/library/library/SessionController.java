package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.model.Courses;
import microservices.book.library.model.HighScore;
import microservices.book.library.model.Session;
import microservices.book.library.util.Clef;
import microservices.book.library.util.Key;
import microservices.book.library.util.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final UserProgressService userProgressService;
    private final UserCoursesService userCoursesService;
    private final SessionService sessionService;
    private final CoursesService coursesService;
    private final HighScoreService highScoreService;

    @Autowired
    Environment env;

    @GetMapping("/getAll")
    String userHasUnifishedCourse(@RequestParam("username") String username) {
        String sessionsJson = sessionService.getAllSession(username, SecurityContextHolder.getContext().getAuthentication());


        return sessionsJson;
    }

    @GetMapping("/highscores")
    @ResponseStatus(HttpStatus.OK)
    Collection<HighScore> archiveSession(@RequestParam("instrument") Mode instrument, @RequestParam("key") Key key,
                                   @RequestParam("clef") Clef clef, @RequestParam("range") String range, @RequestParam("octaveShift") String octaveShift ) throws JsonProcessingException {
        String codeEnding = octaveShift;
        String rangeInt = null;
        // so wrong would need to fix useless after level 33
        switch(range) {
            case "Low":
                codeEnding += "I";
                rangeInt = "1";
                break;
            case "High":
                codeEnding += "II";
                rangeInt = "2";
                break;
            case "Full":
                codeEnding += "III";
                rangeInt = "3";
                break;

        }


        String courseCode = coursesService.getCourseFromInstrumentClefAndCourseCodeEnding(instrument, clef, codeEnding, SecurityContextHolder.getContext().getAuthentication());
        String challengeCode = String.valueOf(key.ordinal()) + rangeInt;
        Collection<HighScore> highScores = highScoreService.find(courseCode, challengeCode, SecurityContextHolder.getContext().getAuthentication());
        return highScores;
    }

}
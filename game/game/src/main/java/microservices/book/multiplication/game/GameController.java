package microservices.book.multiplication.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.client.LibraryApiClient;
import microservices.book.multiplication.model.Courses;
import microservices.book.multiplication.model.Level;
import microservices.book.multiplication.model.UserCoursesDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final DatabaseService databaseService;
    private final LibraryApiClient libraryApiClient;



    @PostMapping("/enumerate_accidentals")
    ResponseEntity<AccidentalsDTO> userHasUnifishedCourse(@RequestBody String string) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(string);
        JsonNode b = jsonNode.get("userCourse");
        String c = b.toString();
        UserCoursesDTO userCourse = new ObjectMapper().readValue(c, UserCoursesDTO.class);
        log.info("Level is having accidentals enumerated");
        String sessionId = databaseService.newSession(userCourse);
        userCourse.setSessionId(sessionId);

        return ResponseEntity.ok(gameService.enumerateAccidentals(userCourse));
    }

    @PostMapping("/end_game")
    boolean archiveSession(@RequestBody GameDTO gameDTO) throws JsonProcessingException, InterruptedException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(string);
//        log.info(jsonNode.toPrettyString());
        log.info("Querying end_game {}",gameDTO);
        if(true) {
            libraryApiClient.libraryArchive(gameDTO, SecurityContextHolder.getContext().getAuthentication().getName(), SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
            log.info("true!!!!!!!!!!!!!!!!!!");
            return true;
        }
        log.info("false!!!!!!!!!!!!!!!!!!");
        return false;

    }
}
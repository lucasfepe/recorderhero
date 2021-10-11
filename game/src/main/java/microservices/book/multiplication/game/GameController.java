package microservices.book.multiplication.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.client.EndGameDTO;
import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.client.LibraryApiClient;
import microservices.book.multiplication.model.Courses;
import microservices.book.multiplication.model.Level;
import microservices.book.multiplication.model.UserCoursesDTO;
import microservices.book.multiplication.util.ScoreCalculator;
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
        String c = jsonNode.toString();
        UserCoursesDTO userCourse = new ObjectMapper().readValue(c, UserCoursesDTO.class);
        log.info("Level is having accidentals enumerated");
        String sessionId = databaseService.newSession(userCourse,SecurityContextHolder.getContext().getAuthentication());
        userCourse.setSessionId(sessionId);

        return ResponseEntity.ok(gameService.enumerateAccidentals(userCourse));
    }




    @GetMapping("/enumerate_accidentals")
    String d() throws JsonProcessingException {



        return "jkol";
    }


    @PostMapping("/end_game")
    EndGameDTO archiveSession(@RequestBody GameDTO gameDTO) throws JsonProcessingException, InterruptedException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(string);
//        log.info(jsonNode.toPrettyString());
        String sessionID = null;
        double score = 0.0;
        log.info("Querying end_game {}",gameDTO);
        
            libraryApiClient.libraryArchive(gameDTO, SecurityContextHolder.getContext().getAuthentication()
//                    SecurityContextHolder.getContext().getAuthentication().getName(), SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()
            );
            if(gameDTO.getLevel() != null){
            if(gameDTO.isChallenge() || gameDTO.getLevel() < 7 ){
                score = ScoreCalculator.execute(gameDTO);
                EndGameDTO levelup = gameService.levelAnalysis(gameDTO, SecurityContextHolder.getContext().getAuthentication(), score );
                gameService.checkHighScore(score, gameDTO, SecurityContextHolder.getContext().getAuthentication());
                EndGameDTO endGameDTO = new EndGameDTO(sessionID, levelup.isLevelup(), levelup.getLevel(), score, gameDTO.isChallenge(), levelup.isCourseComplete());
                return endGameDTO;
            }}

            return null;

      

    }

    @GetMapping("/admin")
    String ddd()   {



        return "admin";
    }
}
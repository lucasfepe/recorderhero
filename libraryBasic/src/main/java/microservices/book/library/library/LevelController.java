package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.client.GameDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/usercourse")
public class LevelController {

    private final ArchiveSessionService archiveSessionService;
    private final LevelupService levelupService;


    @PostMapping("/levelup")
    @ResponseStatus(HttpStatus.OK)
    boolean levelUp(GameDTO gameDTO) throws IOException {


        levelupService.levelup(gameDTO, SecurityContextHolder.getContext().getAuthentication().getName());

        return true;
    }


}
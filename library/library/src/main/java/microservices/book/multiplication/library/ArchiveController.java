package microservices.book.multiplication.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.client.GameDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/archive")
public class ArchiveController {

    private final ArchiveSessionService archiveSessionService;


    @PostMapping("/archive_session")
    @ResponseStatus(HttpStatus.OK)
    boolean archiveSession(@RequestBody GameDTO gameDTO) throws JsonProcessingException {


        archiveSessionService.archiveSession(gameDTO, SecurityContextHolder.getContext().getAuthentication().getName());

        return true;
    }


}
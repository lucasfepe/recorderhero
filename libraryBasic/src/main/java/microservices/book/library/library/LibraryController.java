package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.client.GameDTO;
import microservices.book.library.model.Courses;
import microservices.book.library.model.User;
import microservices.book.library.util.Clef;
import microservices.book.library.util.Key;
import microservices.book.library.util.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/library")
public class LibraryController {

    private final UserSignupService userSignupService;

    @GetMapping("/isFirstTimeUser/{username}")
    @ResponseStatus(HttpStatus.OK)
    boolean archiveSession(@PathVariable("username") String username)  {
        //if the user does not have any user_courses then do the below
        boolean isFirstTimeUser = userSignupService.isFirstTimeUser(username);

        //create new user in database
        if(isFirstTimeUser)userSignupService.createNewUser(username);

        return true;
    }


}
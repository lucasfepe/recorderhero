package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.client.GameDTO;
import microservices.book.library.model.Courses;
import microservices.book.library.util.Clef;
import microservices.book.library.util.Key;
import microservices.book.library.util.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/library")
public class LibraryController {

    private final CoursesService coursesService;




}
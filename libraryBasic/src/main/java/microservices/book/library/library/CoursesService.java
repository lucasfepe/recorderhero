package microservices.book.library.library;

import microservices.book.library.model.Courses;
import microservices.book.library.util.Clef;
import microservices.book.library.util.Mode;
import org.springframework.http.ResponseEntity;

public interface CoursesService {

    String getCourseFromInstrumentClefAndCourseCodeEnding(Mode instrument, Clef clef, String codeEnding);
}

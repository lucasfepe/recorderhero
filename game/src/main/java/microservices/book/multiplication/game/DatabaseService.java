package microservices.book.multiplication.game;

import microservices.book.multiplication.model.UserCoursesDTO;
import org.springframework.security.core.Authentication;

public interface DatabaseService {

    String newSession(UserCoursesDTO userCourse, Authentication authentication);
}

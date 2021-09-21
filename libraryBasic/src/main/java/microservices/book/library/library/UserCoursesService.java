package microservices.book.library.library;

import org.springframework.security.core.Authentication;

public interface UserCoursesService {

    String getAllUserCoursesByUsername(String username, Authentication authentication);
}

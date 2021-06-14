package microservices.book.multiplication.game;

import microservices.book.multiplication.model.UserCoursesDTO;

public interface DatabaseService {

    String newSession(UserCoursesDTO userCourse);
}

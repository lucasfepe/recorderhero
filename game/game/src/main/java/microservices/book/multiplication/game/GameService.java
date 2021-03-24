package microservices.book.multiplication.game;

import microservices.book.multiplication.model.Courses;
import microservices.book.multiplication.model.Level;
import microservices.book.multiplication.model.UserCoursesDTO;

public interface GameService {

    AccidentalsDTO enumerateAccidentals(UserCoursesDTO userCourse);
}

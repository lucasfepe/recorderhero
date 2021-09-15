package microservices.book.multiplication.game;

import microservices.book.multiplication.client.EndGameDTO;
import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.model.UserCoursesDTO;
import org.springframework.security.core.Authentication;

public interface GameService {

    AccidentalsDTO enumerateAccidentals(UserCoursesDTO userCourse);
    EndGameDTO levelAnalysis(GameDTO gameDTO, Authentication authentication, double score);
    void checkHighScore(double score, GameDTO gameDTO, Authentication authentication);
}

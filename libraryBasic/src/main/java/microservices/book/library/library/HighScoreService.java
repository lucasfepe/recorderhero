package microservices.book.library.library;

import microservices.book.library.model.HighScore;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public interface HighScoreService {

    Collection<HighScore> find(String courseCode, String challengeCode, Authentication authentication);
}

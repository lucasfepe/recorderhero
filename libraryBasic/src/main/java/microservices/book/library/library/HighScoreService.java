package microservices.book.library.library;

import microservices.book.library.model.HighScore;

import java.util.Collection;

public interface HighScoreService {

    Collection<HighScore> find(String courseCode, String challengeCode);
}

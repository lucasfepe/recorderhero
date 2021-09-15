package microservices.book.signup.data;

import microservices.book.signup.configuration.projection.InlineCourses;
import microservices.book.signup.configuration.projection.InlineHighScore;
import microservices.book.signup.model.Challenges;
import microservices.book.signup.model.Courses;
import microservices.book.signup.model.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(excerptProjection = InlineHighScore.class)
public interface HighScoreRepo extends JpaRepository<HighScore, Integer> {

    List<HighScore> findByUserUsername(String username);
    List<HighScore> findByCourseCodeAndChallengeCode(String courseCode, String challengeCode);
    List<HighScore> findByCourseCodeAndChallengeCodeAndUserUsername(String code, String challengeCode, String username);


}

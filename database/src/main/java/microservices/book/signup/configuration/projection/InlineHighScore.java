package microservices.book.signup.configuration.projection;


import microservices.book.signup.model.Challenges;
import microservices.book.signup.model.Courses;
import microservices.book.signup.model.HighScore;
import microservices.book.signup.model.Part;
import microservices.book.signup.user.User;
import microservices.book.signup.util.Clef;
import microservices.book.signup.util.Mode;
import microservices.book.signup.util.Notes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineHighScore", types = { HighScore.class })

public interface InlineHighScore {
    @Value("#{target.id}")
    Integer getId();
    Challenges getChallenge();
    Courses getCourse();
    User getUser();
    int getScore();


}

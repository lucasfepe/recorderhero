package microservices.book.multiplication.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "database.api")
public class DatabaseApi {
    private String findByUsernameUri;
    private String findAuthByUsernameUri;
    private String postSession;
    private String findHighScoreByCourseAndChallengeAndUser;
    private String postHighScore;
    private String findChallengeByCode;
    private String findUserCourse;
}
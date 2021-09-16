package microservices.book.library.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "database.api")
public class DatabaseApi {

    private String findByUsernameUri;
    private String findAuthByUsernameUri;
    private String findByUserUsernameUri;
    private String countByUserUsernameUri;
    private String putSession;
    private String postNoteHistory;
    private String putSessionInNoteHistoryA;
    private String putSessionInNoteHistoryB;
    private String putUserInSessionA;
    private String putUserInSessionB;
    private String findUser;
    private String findSession;
    private String findSessionsByUserUsernameUri;
    private String findUserCourseByUserUsernameAndCourseCode;
    private String putUserCourse;
    private String findUserCourseLevel;
    private String findCourseByInstrumentClefCodeending;
    private String listHighScores;
}
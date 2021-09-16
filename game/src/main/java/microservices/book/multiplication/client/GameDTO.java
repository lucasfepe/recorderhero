package microservices.book.multiplication.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import microservices.book.multiplication.model.Challenges;
import microservices.book.multiplication.model.Courses;
import microservices.book.multiplication.util.NotesAcc;
import microservices.book.multiplication.util.NotesAccSep;

import java.util.stream.Stream;

@Data
public class GameDTO {

    private boolean isChallenge;
    private boolean[] peeked_array;
    private NotesAccSep[] note_shown_array;
    private NotesAcc[] note_input_array;
    private long[] time_array_start;
    private long[] time_array_end;
    private String sessionId;
    private int level;
    private int levelScoreToPass;
    private String challengeId;
    private String courseCode;
    private int maxLevel;
    private boolean isCourseComplete;

    @JsonCreator
    public GameDTO(@JsonProperty("isChallenge") boolean isChallenge,
                   @JsonProperty("peeked_array") boolean[] peeked_array,
                   @JsonProperty("note_shown_array") NotesAccSep[] note_shown_array,
                   @JsonProperty("note_input_array") String[] note_input_array,
                   @JsonProperty("time_array_start") long[] time_array_start,
                   @JsonProperty("time_array_end") long[] time_array_end
            , @JsonProperty("sessionId") String sessionId
            , @JsonProperty("level") int level
            , @JsonProperty("levelScoreToPass") int levelScoreToPass
                   , @JsonProperty("challengeId") String challengeId
    , @JsonProperty("courseCode") String courseCode
            , @JsonProperty("maxLevel") int maxLevel
            , @JsonProperty("isCourseComplete") boolean isCourseComplete

    ) {
        System.out.println("'constructor invoked'");




        this.isChallenge = isChallenge;
        this.peeked_array = peeked_array;
        this.note_shown_array = note_shown_array;
        this.note_input_array = Stream.of(note_input_array).map(x -> NotesAcc.valueOfLabel(x)).toArray(NotesAcc[]::new);
        this.time_array_start = time_array_start;
        this.time_array_end = time_array_end;
        this.sessionId = sessionId;
        this.level = level;
        this.levelScoreToPass = levelScoreToPass;
        this.challengeId = challengeId;
        this.courseCode = courseCode;
        this.maxLevel = maxLevel;
        this.isCourseComplete = isCourseComplete;
    }

}
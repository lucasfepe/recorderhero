package microservices.book.library.client;

import lombok.Data;
import microservices.book.library.util.NotesAcc;
import microservices.book.library.util.NotesAccSep;

@Data
public class GameDTO {

    private boolean isChallenge;
    private boolean[] peeked_array;
    private NotesAccSep[] note_shown_array;
    private NotesAcc[] note_input_array;
    private long[] time_array_start;
    private long[] time_array_end;
    private String sessionId;
    private int userCourseId;


}

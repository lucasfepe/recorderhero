package microservices.book.multiplication.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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


}

package microservices.book.multiplication.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import microservices.book.multiplication.util.NotesAcc;
import microservices.book.multiplication.util.NotesAccSep;

import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class EndGameDTO {

    private String sessionId;
    private boolean levelup;
    private int level;
    private double score;
    private boolean isChallenge;
    private boolean courseComplete;




}
package microservices.book.multiplication.game;

import lombok.Value;
import microservices.book.multiplication.util.NotesAccSep;

import javax.validation.constraints.*;
import java.util.List;

@Value
public class AccidentalsDTO {

    int flatCount, naturalCount, sharpCount;
    String sessionId;
    List<NotesAccSep> listOfEntries;


}

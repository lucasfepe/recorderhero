package microservices.book.multiplication.game;

import lombok.Value;
import microservices.book.multiplication.util.NotesAccSep;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Stack;

@Value
public class AccidentalsDTO {

    int flatCount, naturalCount, sharpCount;
    String sessionId;
    List<NotesAccSep> listOfEntries;
    Stack<String> flatBin;
    Stack<String> sharpBin;
    Stack<String> naturalBin;



}

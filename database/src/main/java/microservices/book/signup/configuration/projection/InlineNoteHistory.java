package microservices.book.signup.configuration.projection;



import microservices.book.signup.model.NoteHistory;
import microservices.book.signup.model.Session;
import microservices.book.signup.util.NotesAcc;
import microservices.book.signup.util.NotesAccSep;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "inlineNoteHistory", types = { NoteHistory.class })
public interface InlineNoteHistory {
    int getId();
    long getReactionTime();
    NotesAccSep getNote();
    NotesAcc getNoteChosen();
    InlineSession getSession();

}

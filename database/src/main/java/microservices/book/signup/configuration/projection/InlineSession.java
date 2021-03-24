package microservices.book.signup.configuration.projection;



import microservices.book.signup.model.NoteHistory;
import microservices.book.signup.model.Session;
import microservices.book.signup.util.NotesAcc;
import microservices.book.signup.util.NotesAccSep;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineSession", types = { Session.class })
public interface InlineSession {
    int getId();


}

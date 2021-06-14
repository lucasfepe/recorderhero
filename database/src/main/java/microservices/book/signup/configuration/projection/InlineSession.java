package microservices.book.signup.configuration.projection;



import microservices.book.signup.model.Challenges;
import microservices.book.signup.model.NoteHistory;
import microservices.book.signup.model.Session;
import microservices.book.signup.user.User;
import microservices.book.signup.util.*;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineSession", types = { Session.class })
public interface InlineSession {
    int getId();
    long getStartTime();
    long getEndTime();
    User getUser();
    String getHighNote();
    String getLowNote();
    Clef getClef();
    Mode getMode();
    Key getKeym();
    String getChallengeCode();




}

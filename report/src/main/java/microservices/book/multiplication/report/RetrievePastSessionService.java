package microservices.book.multiplication.report;

import microservices.book.multiplication.model.NoteHistory;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public interface RetrievePastSessionService {

    Collection<NoteHistory> execute(int sessionID, Authentication authentication);
}

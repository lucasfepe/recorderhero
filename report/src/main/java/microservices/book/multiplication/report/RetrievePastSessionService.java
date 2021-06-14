package microservices.book.multiplication.report;

import microservices.book.multiplication.model.NoteHistory;

import java.util.Collection;

public interface RetrievePastSessionService {

    Collection<NoteHistory> execute(int sessionID);
}

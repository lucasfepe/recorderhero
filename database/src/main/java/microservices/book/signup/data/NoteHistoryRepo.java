package microservices.book.signup.data;

import microservices.book.signup.configuration.projection.InlineNoteHistory;
import microservices.book.signup.model.NoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(excerptProjection = InlineNoteHistory.class)
public interface NoteHistoryRepo extends JpaRepository<NoteHistory, Integer> {

    List<NoteHistory> findAllBySessionId(int id);



}


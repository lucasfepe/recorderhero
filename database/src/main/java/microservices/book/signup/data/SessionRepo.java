package microservices.book.signup.data;

import microservices.book.signup.configuration.projection.InlineNoteHistory;
import microservices.book.signup.configuration.projection.InlineSession;
import microservices.book.signup.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = InlineSession.class)
public interface SessionRepo extends JpaRepository<Session, Integer> {




}


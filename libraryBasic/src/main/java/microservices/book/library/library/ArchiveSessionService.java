package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import microservices.book.library.client.GameDTO;
import org.springframework.security.core.Authentication;

public interface ArchiveSessionService {

    void archiveSession(GameDTO gameDTO, String username, Authentication authentication) throws JsonProcessingException;
}

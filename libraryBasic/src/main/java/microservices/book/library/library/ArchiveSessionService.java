package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import microservices.book.library.client.GameDTO;

public interface ArchiveSessionService {

    void archiveSession(GameDTO gameDTO, String username) throws JsonProcessingException;
}

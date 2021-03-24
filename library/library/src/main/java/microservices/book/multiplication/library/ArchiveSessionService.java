package microservices.book.multiplication.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import microservices.book.multiplication.client.GameDTO;

public interface ArchiveSessionService {

    void archiveSession(GameDTO gameDTO, String username) throws JsonProcessingException;
}

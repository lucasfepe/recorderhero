package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import microservices.book.library.client.GameDTO;

import java.io.IOException;

public interface LevelupService {

    void levelup(GameDTO gameDTO, String name) throws IOException;
}

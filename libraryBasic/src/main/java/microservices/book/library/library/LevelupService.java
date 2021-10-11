package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import microservices.book.library.client.GameDTO;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface LevelupService {

    void levelup(GameDTO gameDTO, String name, Authentication authentication) throws IOException;
}

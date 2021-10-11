package microservices.book.library.library;

import microservices.book.library.model.Session;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    String getAllSession(String username, Authentication authentication);
}

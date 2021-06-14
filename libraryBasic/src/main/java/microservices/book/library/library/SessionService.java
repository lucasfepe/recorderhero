package microservices.book.library.library;

import microservices.book.library.model.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    String getAllSession(String username);
}

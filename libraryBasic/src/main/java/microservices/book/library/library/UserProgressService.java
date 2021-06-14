package microservices.book.library.library;

import java.util.Optional;

public interface UserProgressService {

    Optional<Boolean> isCourseUnfinished(String username);
}

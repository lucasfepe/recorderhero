package microservices.book.multiplication.library;

import java.util.Optional;

public interface UserProgressService {

    Optional<Boolean> isCourseUnfinished(String username);
}

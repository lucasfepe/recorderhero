package microservices.book.multiplication.game;

import java.util.Optional;

public interface UserProgressService {

    Optional<Boolean> isCourseUnfinished(String username);
}

package microservices.book.library.library;

import microservices.book.library.model.User;

import java.util.Optional;

public interface UserSignupService {

    boolean isFirstTimeUser(String username);
    void createNewUser(String username);
}

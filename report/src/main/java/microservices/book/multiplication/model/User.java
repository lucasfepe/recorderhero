package microservices.book.multiplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Stores information to identify the user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String username;
    private String password;
    private boolean enabled;

    public User(final String username, String password) {
        this(null, username, password, true);
    }
}

package microservices.book.signup.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
public interface UserRepository extends CrudRepository<User,String> {
    public User findByUsernameIgnoreCase(@Param("username") String username);

}

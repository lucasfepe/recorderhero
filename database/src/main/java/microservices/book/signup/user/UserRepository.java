package microservices.book.signup.user;

import microservices.book.signup.configuration.projection.InlineCourses;
import microservices.book.signup.configuration.projection.InlineUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = InlineUser.class)
public interface UserRepository extends CrudRepository<User,String> {
    public User findByUsernameIgnoreCase(@Param("username") String username);

}

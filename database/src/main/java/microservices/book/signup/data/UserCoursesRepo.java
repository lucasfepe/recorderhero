package microservices.book.signup.data;

import microservices.book.signup.configuration.projection.InlineUserCourses;
import microservices.book.signup.model.Courses;
import microservices.book.signup.model.UserCourses;
import microservices.book.signup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = InlineUserCourses.class)
public interface UserCoursesRepo extends JpaRepository<UserCourses, Integer> {

    List<UserCourses> findByUserUsername(String username);
    int countByUserUsername(String username);
    UserCourses findByUserAndCourse(User user, Courses course);

}
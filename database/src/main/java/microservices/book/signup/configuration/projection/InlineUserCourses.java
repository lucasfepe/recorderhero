package microservices.book.signup.configuration.projection;


import microservices.book.signup.model.Courses;
import microservices.book.signup.model.Level;
import microservices.book.signup.model.UserCourses;
import microservices.book.signup.user.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineUserCourses", types = { UserCourses.class })
public interface InlineUserCourses {
    boolean isComplete();
    Courses getCourse();
    Level getLevel();
    User getUser();
}

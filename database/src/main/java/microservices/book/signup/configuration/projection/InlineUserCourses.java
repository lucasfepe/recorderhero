package microservices.book.signup.configuration.projection;


import com.fasterxml.jackson.annotation.JsonIgnore;
import microservices.book.signup.model.Courses;
import microservices.book.signup.model.Level;
import microservices.book.signup.model.UserCourses;
import microservices.book.signup.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineUserCourses", types = { UserCourses.class })

public interface InlineUserCourses {
//    @Value("#{target.course.code}")
//    String getCourseCode();
    boolean isComplete();

    InlineCourses getCourse();
    Level getLevel();
    User getUser();
}

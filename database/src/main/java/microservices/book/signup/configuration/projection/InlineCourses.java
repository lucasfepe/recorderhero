package microservices.book.signup.configuration.projection;


import microservices.book.signup.model.Courses;
import microservices.book.signup.model.Level;
import microservices.book.signup.model.Part;
import microservices.book.signup.model.UserCourses;
import microservices.book.signup.user.User;
import microservices.book.signup.util.Clef;
import microservices.book.signup.util.Mode;
import microservices.book.signup.util.Notes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineCourses", types = { Courses.class })

public interface InlineCourses {
    @Value("#{target.code}")
    String getCode();
    Mode getInstrument();
    Clef getClef();
    int getOctaveShift();
    Notes getLowNote();
    Notes getHighNote();
    Part getPart();

}

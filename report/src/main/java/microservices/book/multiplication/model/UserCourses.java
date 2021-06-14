package microservices.book.multiplication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;



public class UserCourses extends RepresentationModel<UserCourses> {


    private Integer id;

    private User user;

    private Courses course;

    private Level level;
    private boolean complete;

    @JsonCreator
    public UserCourses(@JsonProperty("content") User user) {
        this.user = user;
    }
}
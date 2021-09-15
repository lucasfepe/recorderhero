package microservices.book.library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UserCourses  {


    private Integer id;

    private User user;

    private Courses course;

    private Level level;
    private boolean complete;

}
package microservices.book.multiplication.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class UserCoursesDTO extends RepresentationModel<UserCoursesDTO> {


    private Integer id;

    private User user;

    private Courses course;

    private Level level;
    private boolean complete;
    private boolean challenge;
    private String sessionId;


}
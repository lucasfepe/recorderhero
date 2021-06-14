package microservices.book.multiplication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
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
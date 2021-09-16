package microservices.book.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighScore {

    private Integer id;
    private Challenges challenge;
    private Courses course;
    private User user;
    private int score;

}

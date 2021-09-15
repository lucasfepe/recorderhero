package microservices.book.signup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.signup.user.User;
import microservices.book.signup.util.Key;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighScore {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Challenges challenge;
    @ManyToOne
    private Courses course;
    @ManyToOne
    private User user;
    private int score;

}

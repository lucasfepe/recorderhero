package microservices.book.signup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.signup.util.Key;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Challenges {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @Enumerated(EnumType.ORDINAL)
    private Key keym;
    @Column(length = 50)
    private String accidentals;
    private Integer accidSlider;
    private Integer numberOfNotes;
    private Integer challengeNumber;
}

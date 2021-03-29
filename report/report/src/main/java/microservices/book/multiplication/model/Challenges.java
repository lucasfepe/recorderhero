package microservices.book.multiplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.multiplication.util.Key;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Challenges {


    private String id;
    private Key keym;
    private String accidentals;
    private Integer accidSlider;
    private Integer numberOfNotes;
    private Integer challengeNumber;
}

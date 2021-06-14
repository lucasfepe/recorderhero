package microservices.book.multiplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.multiplication.util.Key;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Level {

    private Integer id;
    private Integer level;
    private int lowNote;
    private int highNote;
    private Key keym;
    private String accidentals;
    private Integer accidentalSlider;
    private Integer points;
    private int maxStar;
    private Part part;
    private String challengeCode;
}

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
public class Level {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer level;
    private int lowNote;
    private int highNote;
    @Enumerated(EnumType.ORDINAL)
    private Key keym;
    @Column(length = 50)
    private String accidentals;
    private Integer accidentalSlider;
    private Integer points;
    private int maxStar;
    @ManyToOne
    private Part part;
    @ManyToOne
    private Challenges challenge;
}

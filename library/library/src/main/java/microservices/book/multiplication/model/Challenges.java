package microservices.book.multiplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.book.multiplication.util.Key;


import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Challenges {


    @Id
    @Column(length = 50)
    private String id;
    @Enumerated(EnumType.ORDINAL)
    private Key keym;
    @Column(length = 50)
    private String accidentals;
    private Integer accidSlider;
    private Integer numberOfNotes;
    private Integer challengeNumber;
}

package microservices.book.multiplication.model;

import lombok.Data;
import microservices.book.multiplication.util.Clef;
import microservices.book.multiplication.util.Mode;
import microservices.book.multiplication.util.Notes;
import org.springframework.hateoas.RepresentationModel;


@Data
public class CoursesDTO extends RepresentationModel<CoursesDTO> {


    private String code;
    private Mode instrument = Mode.PICK;
    private Clef clef;
    private int octaveShift;

    private Notes lowNote;
    private Notes highNote;
    private Part part;





}
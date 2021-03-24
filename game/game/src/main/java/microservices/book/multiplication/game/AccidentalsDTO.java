package microservices.book.multiplication.game;

import lombok.Value;
import javax.validation.constraints.*;

@Value
public class AccidentalsDTO {

    int flatCount, naturalCount, sharpCount;
    String sessionId;


}

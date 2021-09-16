package microservices.book.multiplication.util;

import lombok.Value;
import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.util.NotesAccSep;

import java.util.List;

@Value
public class ScoreCalculator {

    public static double execute(GameDTO gameDTO){
        double timetotal =0.0;
        int notesCorrect = 0;
        float rxnTime = 0;
        for (int i = 0; i < gameDTO.getNote_input_array().length; i++) {
            if(gameDTO.getNote_input_array()[i].name().contains(gameDTO.getNote_shown_array()[i].name())){
                notesCorrect++;
            }
            timetotal += gameDTO.getTime_array_end()[i] - gameDTO.getTime_array_start()[i];
        }

        return notesCorrect * 1000000 * 4 / timetotal * gameDTO.getNote_input_array().length;
    }


}

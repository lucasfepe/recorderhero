package microservices.book.multiplication.game;

import microservices.book.multiplication.model.Courses;
import microservices.book.multiplication.model.Level;
import microservices.book.multiplication.model.UserCoursesDTO;
import microservices.book.multiplication.util.NotesAccSep;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

@Component
public class EnumerateAccidentals {

    public AccidentalsDTO execute(UserCoursesDTO userCourse, List<Map.Entry<NotesAccSep, Integer>> listOfEntries){



        String instrument_lowest_note;
        switch(userCourse.getCourse().getInstrument()){
            case PICK:instrument_lowest_note = "";break;
            case ALTO_RECORDER:instrument_lowest_note = "Fb4";break;
            case SOPRANO_RECORDER:instrument_lowest_note = "C5";break;
            case TENOR_RECORDER:instrument_lowest_note = "Cb4";break;
            default:
                throw new IllegalStateException("Unexpected value: " + userCourse.getCourse().getInstrument());
        }

        int numberOfNaturals = 0, numberOfFlats = 0, numberOfSharps = 0;
        boolean hasSharp = false;
        boolean hasFlat = false;
        int counter = 0, accidentalCounter = 0, flatCounter = 0, sharpCounter = 0;
        char note;
        boolean isLowestNote = false;
        char prevNote = 0;
        String lastNote = null;
        String secondLastNote = null;
        Stack<String> flatBin = new Stack<>();
        Stack<String> sharpBin = new Stack<>();
        Stack<String> naturalBin = new Stack<>();
        Stack<String> accidentalBin = new Stack<>();
        Map<NotesAccSep, Integer> sortedByValueNotesCount = new LinkedHashMap<NotesAccSep, Integer>(listOfEntries.size());
        for(Map.Entry<NotesAccSep, Integer> entry : listOfEntries){
            if( entry.getKey().toString().equals(instrument_lowest_note)
            ) {

                isLowestNote = true;
            }

            note = entry.getKey().toString().charAt(0);




            if(prevNote != note) {
                if(counter == 2 && accidentalCounter == 1 ) {

            if(!isLowestNote) {
                numberOfNaturals++;

                if (sharpCounter == 1) {
                    numberOfSharps--;
                    naturalBin.add(lastNote);
                    accidentalBin.pop();
                    accidentalBin.add(lastNote);
                    sharpBin.pop();
                } else {
                    numberOfFlats--;
                    naturalBin.add(secondLastNote);
                    accidentalBin.pop();
                    accidentalBin.add(secondLastNote);
                    flatBin.pop();
                }
            }else{isLowestNote = !isLowestNote;
                }}
                if(prevNote != 0) {counter = 0;
                    accidentalCounter = 0;
                    sharpCounter = 0;
                    flatCounter = 0;}
                if(entry.getKey().toString().contains("s")) {
                    numberOfSharps++;
                    accidentalCounter++;
                    sharpCounter++;
                    sharpBin.add(entry.getKey().toString());
                    accidentalBin.add(entry.getKey().toString());
                }
                else if(entry.getKey().toString().contains("b")) {
                    numberOfFlats++;
                    accidentalCounter++;
                    flatCounter++;
                    flatBin.add(entry.getKey().toString());
                    accidentalBin.add(entry.getKey().toString());
                }


            }else if( listOfEntries.indexOf(entry) == (listOfEntries.size() -1)) {

                counter++;
                if(entry.getKey().toString().contains("s")) {
                    numberOfSharps++;
                    accidentalCounter++;
                    sharpCounter++;
                    sharpBin.add(entry.getKey().toString());
                    accidentalBin.add(entry.getKey().toString());
                }
                else if(entry.getKey().toString().contains("b")) {
                    numberOfFlats++;
                    accidentalCounter++;
                    flatCounter++;
                    flatBin.add(entry.getKey().toString());
                    accidentalBin.add(entry.getKey().toString());
                }
                if(counter == 2 && accidentalCounter == 1) {
                    if(!isLowestNote){
                    numberOfNaturals++;

                    if(sharpCounter == 1) {
                        numberOfSharps--;
                        naturalBin.add(entry.getKey().toString());
                        accidentalBin.pop();
                        accidentalBin.add(entry.getKey().toString());
                        sharpBin.pop();
//								nonAccidentBin.pop();
                    }else {numberOfFlats--;
                        naturalBin.add(lastNote);
                        accidentalBin.pop();
                        accidentalBin.add(lastNote);
                        flatBin.pop();
//							nonAccidentBin.pop();
                    }
                }else{
                    isLowestNote = !isLowestNote;}
                }
            }else {
                if(entry.getKey().toString().contains("s")) {
                    numberOfSharps++;
                    accidentalCounter++;
                    sharpCounter++;
                    sharpBin.add(entry.getKey().toString());
                    accidentalBin.add(entry.getKey().toString());
                }
                else if(entry.getKey().toString().contains("b") ) {
                    numberOfFlats++;
                    accidentalCounter++;
                    flatCounter++;
                    flatBin.add(entry.getKey().toString());
                    accidentalBin.add(entry.getKey().toString());
                }
            }

            counter++;







            prevNote = note;
            secondLastNote = lastNote;
            lastNote = entry.getKey().toString();

        }
        List<NotesAccSep> collect = listOfEntries.stream().map(k -> k.getKey()).collect(Collectors.toList());

        return new AccidentalsDTO(numberOfFlats, numberOfNaturals, numberOfSharps, userCourse.getSessionId(), collect, flatBin, sharpBin, naturalBin);


    }


}

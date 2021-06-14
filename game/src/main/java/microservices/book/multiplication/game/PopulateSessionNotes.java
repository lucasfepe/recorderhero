package microservices.book.multiplication.game;

import microservices.book.multiplication.model.UserCoursesDTO;
import microservices.book.multiplication.util.Notes;
import microservices.book.multiplication.util.NotesAccSep;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
public class PopulateSessionNotes {

    public List<Map.Entry<NotesAccSep, Integer>> execute(UserCoursesDTO userCourse){




        //determines session length

        int lowNoteIndex = NotesAccSep.valueOf(Notes.values()[userCourse.getCourse().getLowNote().ordinal() + userCourse.getLevel().getLowNote()].toString()).ordinal();
        int highNoteIndex = NotesAccSep.valueOf(Notes.values()[userCourse.getCourse().getLowNote().ordinal() + userCourse.getLevel().getHighNote() ].toString()).ordinal();
        Map<NotesAccSep, Integer> sessionNotes = new TreeMap<>();
        int accAdjustUp = 0;
        int accAdjustDown = 0;
        String accidentalCh = "OFF";
        
      
        if(userCourse.getLevel().getAccidentals().equals("ON")) {
            accAdjustUp = 1;
            accAdjustDown = lowNoteIndex == 105?0: 1;
        }
        for(int i = lowNoteIndex - accAdjustDown; i <= highNoteIndex + accAdjustUp; i++) {
            if(userCourse.getLevel().getAccidentals().equals("OFF")) {
                if(!(NotesAccSep.values()[i].toString().contains("b") || NotesAccSep.values()[i].toString().contains("s"))) {
                    String note = NotesAccSep.values()[i].toString();
                    String noteSharp = note.charAt(0) + "s" + note.charAt(1);
                    String noteFlat = note.charAt(0) + "b" + note.charAt(1);

                    switch(userCourse.getLevel().getKeym()) {
                        case C:
                            sessionNotes.put(NotesAccSep.values()[i],0);
                            break;
                        case G:

                            if(NotesAccSep.values()[i].toString().contains("F")) {
                                sessionNotes.put(NotesAccSep.valueOf(noteSharp),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}


                            break;
                        case D:
                            if(NotesAccSep.values()[i].toString().contains("F")
                                    || NotesAccSep.values()[i].toString().contains("C")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteSharp),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}


                            break;
                        case A:
                            if(NotesAccSep.values()[i].toString().contains("F")
                                    || NotesAccSep.values()[i].toString().contains("C")
                                    || NotesAccSep.values()[i].toString().contains("G")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteSharp),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case E:
                            if(NotesAccSep.values()[i].toString().contains("F")
                                    || NotesAccSep.values()[i].toString().contains("C")
                                    || NotesAccSep.values()[i].toString().contains("G")
                                    || NotesAccSep.values()[i].toString().contains("D")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteSharp),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case B:
                            if(NotesAccSep.values()[i].toString().contains("F")
                                    || NotesAccSep.values()[i].toString().contains("C")
                                    || NotesAccSep.values()[i].toString().contains("G")
                                    || NotesAccSep.values()[i].toString().contains("D")
                                    || NotesAccSep.values()[i].toString().contains("A")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteSharp),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case Fs:
                            if(NotesAccSep.values()[i].toString().contains("F")
                                    || NotesAccSep.values()[i].toString().contains("C")
                                    || NotesAccSep.values()[i].toString().contains("G")
                                    || NotesAccSep.values()[i].toString().contains("D")
                                    || NotesAccSep.values()[i].toString().contains("A")
                                    || NotesAccSep.values()[i].toString().contains("E")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteSharp),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case Cs:
                            if(NotesAccSep.values()[i].toString().contains("F")
                                    || NotesAccSep.values()[i].toString().contains("C")
                                    || NotesAccSep.values()[i].toString().contains("G")
                                    || NotesAccSep.values()[i].toString().contains("D")
                                    || NotesAccSep.values()[i].toString().contains("A")
                                    || NotesAccSep.values()[i].toString().contains("E")
                                    || NotesAccSep.values()[i].toString().contains("B")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteSharp),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case F:
                            if(NotesAccSep.values()[i].toString().contains("B")) {
                                sessionNotes.put(NotesAccSep.valueOf(noteFlat),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case Bb:
                            if(NotesAccSep.values()[i].toString().contains("B")
                                    || NotesAccSep.values()[i].toString().contains("E")) {
                                sessionNotes.put(NotesAccSep.valueOf(noteFlat),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case Eb:
                            if(NotesAccSep.values()[i].toString().contains("B")
                                    || NotesAccSep.values()[i].toString().contains("E")
                                    || NotesAccSep.values()[i].toString().contains("A")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteFlat),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case Ab:
                            if(NotesAccSep.values()[i].toString().contains("B")
                                    || NotesAccSep.values()[i].toString().contains("E")
                                    || NotesAccSep.values()[i].toString().contains("A")
                                    || NotesAccSep.values()[i].toString().contains("D")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteFlat),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case Db:
                            if(NotesAccSep.values()[i].toString().contains("B")
                                    || NotesAccSep.values()[i].toString().contains("E")
                                    || NotesAccSep.values()[i].toString().contains("A")
                                    || NotesAccSep.values()[i].toString().contains("D")
                                    || NotesAccSep.values()[i].toString().contains("G")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteFlat),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case Gb:
                            if(NotesAccSep.values()[i].toString().contains("B")
                                    || NotesAccSep.values()[i].toString().contains("E")
                                    || NotesAccSep.values()[i].toString().contains("A")
                                    || NotesAccSep.values()[i].toString().contains("D")
                                    || NotesAccSep.values()[i].toString().contains("G")
                                    || NotesAccSep.values()[i].toString().contains("C")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteFlat),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;
                        case Cb:
                            if(NotesAccSep.values()[i].toString().contains("B")
                                    || NotesAccSep.values()[i].toString().contains("E")
                                    || NotesAccSep.values()[i].toString().contains("A")
                                    || NotesAccSep.values()[i].toString().contains("D")
                                    || NotesAccSep.values()[i].toString().contains("G")
                                    || NotesAccSep.values()[i].toString().contains("C")
                                    || NotesAccSep.values()[i].toString().contains("F")
                            ) {
                                sessionNotes.put(NotesAccSep.valueOf(noteFlat),0);
                            }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                            break;

                    }
                };
            }else{
                String note = NotesAccSep.values()[i].toString();
                switch(userCourse.getLevel().getKeym()) {
                    case C:
                        sessionNotes.put(NotesAccSep.values()[i],0);
                        break;
                    case G:
                        if(NotesAccSep.values()[i].toString().contains("Fb")) {
                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case D:
                        if(NotesAccSep.values()[i].toString().contains("Fb")
                                || NotesAccSep.values()[i].toString().contains("Cb")
                        ) {
                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case A:
                        if(NotesAccSep.values()[i].toString().contains("Fb")
                                || NotesAccSep.values()[i].toString().contains("Cb")
                                || NotesAccSep.values()[i].toString().contains("Gb")
                        ) {
                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case E:
                        if(NotesAccSep.values()[i].toString().contains("Fb")
                                || NotesAccSep.values()[i].toString().contains("Cb")
                                || NotesAccSep.values()[i].toString().contains("Gb")
                                || NotesAccSep.values()[i].toString().contains("Db")
                        ) {
                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case B:
                        if(NotesAccSep.values()[i].toString().contains("Fb")
                                || NotesAccSep.values()[i].toString().contains("Cb")
                                || NotesAccSep.values()[i].toString().contains("Gb")
                                || NotesAccSep.values()[i].toString().contains("Db")
                                || NotesAccSep.values()[i].toString().contains("Ab")
                        ) {
                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case Fs:
                        if(NotesAccSep.values()[i].toString().contains("Fb")
                                || NotesAccSep.values()[i].toString().contains("Cb")
                                || NotesAccSep.values()[i].toString().contains("Gb")
                                || NotesAccSep.values()[i].toString().contains("Db")
                                || NotesAccSep.values()[i].toString().contains("Ab")
                                || NotesAccSep.values()[i].toString().contains("Eb")
                        ) {
                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case Cs:
                        if(NotesAccSep.values()[i].toString().contains("Fb")
                                || NotesAccSep.values()[i].toString().contains("Cb")
                                || NotesAccSep.values()[i].toString().contains("Gb")
                                || NotesAccSep.values()[i].toString().contains("Db")
                                || NotesAccSep.values()[i].toString().contains("Ab")
                                || NotesAccSep.values()[i].toString().contains("Eb")
                                || NotesAccSep.values()[i].toString().contains("Bb")
                        ) {
                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case F:
                        if(NotesAccSep.values()[i].toString().contains("Bs")) {

                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case Bb:
                        if(NotesAccSep.values()[i].toString().contains("Bs")
                                ||	NotesAccSep.values()[i].toString().contains("Es")	) {

                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case Eb:
                        if(NotesAccSep.values()[i].toString().contains("Bs")
                                ||	NotesAccSep.values()[i].toString().contains("Es")
                                ||	NotesAccSep.values()[i].toString().contains("As")
                        ) {

                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case Ab:
                        if(NotesAccSep.values()[i].toString().contains("Bs")
                                ||	NotesAccSep.values()[i].toString().contains("Es")
                                ||	NotesAccSep.values()[i].toString().contains("As")
                                ||	NotesAccSep.values()[i].toString().contains("Ds")
                        ) {

                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case Db:
                        if(NotesAccSep.values()[i].toString().contains("Bs")
                                ||	NotesAccSep.values()[i].toString().contains("Es")
                                ||	NotesAccSep.values()[i].toString().contains("As")
                                ||	NotesAccSep.values()[i].toString().contains("Ds")
                                ||	NotesAccSep.values()[i].toString().contains("Gs")
                        ) {

                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case Gb:
                        if(NotesAccSep.values()[i].toString().contains("Bs")
                                ||	NotesAccSep.values()[i].toString().contains("Es")
                                ||	NotesAccSep.values()[i].toString().contains("As")
                                ||	NotesAccSep.values()[i].toString().contains("Ds")
                                ||	NotesAccSep.values()[i].toString().contains("Gs")
                                ||	NotesAccSep.values()[i].toString().contains("Cs")
                        ) {

                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;
                    case Cb:
                        if(NotesAccSep.values()[i].toString().contains("Bs")
                                ||	NotesAccSep.values()[i].toString().contains("Es")
                                ||	NotesAccSep.values()[i].toString().contains("As")
                                ||	NotesAccSep.values()[i].toString().contains("Ds")
                                ||	NotesAccSep.values()[i].toString().contains("Gs")
                                ||	NotesAccSep.values()[i].toString().contains("Cs")
                                ||	NotesAccSep.values()[i].toString().contains("Fs")
                        ) {

                        }else{sessionNotes.put(NotesAccSep.values()[i],0);}
                        break;

                }





            }
        }

        return
                sessionNotes.entrySet()
                        .stream()

                        .collect(Collectors.toList());


    }
}

package microservices.book.multiplication.report;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.client.ReportDTO;
import microservices.book.multiplication.model.NoteHistory;
import microservices.book.multiplication.util.MapUtil;
import microservices.book.multiplication.util.NotesAcc;
import microservices.book.multiplication.util.NotesAccSep;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@Value
public class ReportRunSummaryServiceImpl implements ReportRunSummaryService {


    private final MapUtil mapUtil;

    @Override
    public ReportDTO execute(List<NoteHistory> notes) {




        Map<NotesAccSep, Integer> notesCount = new HashMap();;
        Map<NotesAccSep, Integer> notesCorrect = new HashMap();;
        Map<NotesAccSep, Double> notesTime = new HashMap();;
        LinkedHashMap<NotesAccSep, Integer> sortedNotesCount = null;
        LinkedHashMap<NotesAccSep, Integer> sortedNotesCorrect = null;
        LinkedHashMap<NotesAccSep, Double> sortedNotesTime = null;
        int overallNoteCount;
        int overallNoteCorrect = 0;
        int correctMapMax;
        int timeMapMax;
        double reactionTime;
        int timeTotal = 0;


        for (int i = 0; i < notes.size(); i++) {

            reactionTime = notes.get(i).getReactionTime();
            timeTotal += reactionTime;
            NotesAccSep actualNote = notes.get(i).getNote();
            NotesAcc userNote = notes.get(i).getNoteChosen();
            if (userNote.toString().contains(actualNote.toString())) {
                overallNoteCorrect++;
                mapUtil.incrementMap(notesCorrect, actualNote);
            }
            mapUtil.incrementMap(notesCount, actualNote);
            mapUtil.incrementTimeMap(notesTime, actualNote, reactionTime);


        }

        //fill 0s for any notes that appeared but didn't get at least one correct
        notesCount.keySet().stream().filter(key -> !notesCorrect.containsKey(key)).forEach(x -> notesCorrect.put(x, 0));

        // divide reaction time by number of appearances to get average

        for (Map.Entry<NotesAccSep, Double> entry : notesTime.entrySet()) {

            notesTime.put(entry.getKey(), entry.getValue() / notesCount.get(entry.getKey()));

        }

        //sort maps so that notes are ascending according to C lowest B highest
        Set<Map.Entry<NotesAccSep, Integer>> entries = notesCount.entrySet();
        List<Map.Entry<NotesAccSep, Integer>> listOfEntries = new ArrayList<Map.Entry<NotesAccSep, Integer>>(entries);
        Collections.sort(listOfEntries, mapUtil.valueComparator);
        sortedNotesCount = new LinkedHashMap<NotesAccSep, Integer>(listOfEntries.size());
        for(Map.Entry<NotesAccSep, Integer> entry : listOfEntries){
            sortedNotesCount.put(entry.getKey(), entry.getValue());
        }

        entries = notesCorrect.entrySet();
        listOfEntries = new ArrayList<Map.Entry<NotesAccSep, Integer>>(entries);
        Collections.sort(listOfEntries, mapUtil.valueComparator);
        sortedNotesCorrect = new LinkedHashMap<NotesAccSep, Integer>(listOfEntries.size());
        for(Map.Entry<NotesAccSep, Integer> entry : listOfEntries){
            sortedNotesCorrect.put(entry.getKey(), entry.getValue());
        }

        Set<Map.Entry<NotesAccSep, Double>> entriesTime = notesTime.entrySet();
        List<Map.Entry<NotesAccSep, Double>> listOfEntriesTime = new ArrayList<Map.Entry<NotesAccSep, Double>>(entriesTime);
        Collections.sort(listOfEntriesTime, mapUtil.valueComparatorTime);
        sortedNotesTime = new LinkedHashMap<NotesAccSep, Double>(listOfEntriesTime.size());
        for(Map.Entry<NotesAccSep, Double> entry : listOfEntriesTime){
            sortedNotesTime.put(entry.getKey(), entry.getValue());
        }


//		get maximum in each map set max y axis value on charts
        correctMapMax = mapUtil.maxMap(notesCount);
        timeMapMax = (int) Math.round(mapUtil.maxMap(notesTime) + 0.5);


        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setCorrectValues(new ArrayList<>(sortedNotesCorrect.values()) );
        reportDTO.setCountKeySet(new ArrayList<>(sortedNotesCount.keySet()));
        reportDTO.setCountValues(new ArrayList<>(sortedNotesCount.values()));
        reportDTO.setTimeKeySet(new ArrayList<>( sortedNotesTime.keySet()));
        reportDTO.setTimeValues(new ArrayList<>(sortedNotesTime.values()));
        reportDTO.setMapMax(correctMapMax);
        reportDTO.setTimeMapMax(timeMapMax);
        reportDTO.setOverallNoteCorrect(overallNoteCorrect);
        reportDTO.setOverallNoteCount(notes.size());
        reportDTO.setTimeTotal(timeTotal);

        return reportDTO;



    }

}

package microservices.book.multiplication.client;

import lombok.Data;
import microservices.book.multiplication.util.NotesAcc;
import microservices.book.multiplication.util.NotesAccSep;

import java.util.LinkedHashMap;
import java.util.List;

@Data
public class ReportDTO {

    private List<NotesAccSep> countKeySet;
    private List<Integer> countValues;
    private List<Integer> correctValues;
    private List<NotesAccSep> timeKeySet;
    private List<Double> timeValues;
    private int overallNoteCount;
    private int overallNoteCorrect;
    private int mapMax;
    private int timeMapMax;
    private int timeTotal;

}

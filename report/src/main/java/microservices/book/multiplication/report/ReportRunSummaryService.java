package microservices.book.multiplication.report;

import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.client.ReportDTO;
import microservices.book.multiplication.model.NoteHistory;

import java.util.List;

public interface ReportRunSummaryService {

    ReportDTO execute(List<NoteHistory> notes);
}

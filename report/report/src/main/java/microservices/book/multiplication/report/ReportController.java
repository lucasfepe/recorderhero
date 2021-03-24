package microservices.book.multiplication.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.client.ReportDTO;
import microservices.book.multiplication.model.NoteHistory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportRunSummaryService reportRunSummaryService;
    private final RetrievePastSessionService retrievePastSessionService;


    @GetMapping("/run_summary/{sessionID}")
    ReportDTO archiveSession(@PathVariable("sessionID") Integer sessionID) throws InterruptedException {
        List<NoteHistory> notes = (List<NoteHistory>) retrievePastSessionService.execute(sessionID);
        log.info("nowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        return reportRunSummaryService.execute(notes);


    }


}
package microservices.book.multiplication.game;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.game.GameService;
import microservices.book.multiplication.model.Courses;
import microservices.book.multiplication.model.Level;
import microservices.book.multiplication.model.UserCoursesDTO;
import microservices.book.multiplication.util.NotesAccSep;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameServiceImpl implements GameService {


    private final PopulateSessionNotes populateSessionNotes;
    private final EnumerateAccidentals enumerateAccidentals;


    @Override
    public AccidentalsDTO enumerateAccidentals(UserCoursesDTO userCourse) {

        return enumerateAccidentals.execute(userCourse, populateSessionNotes.execute(userCourse));


    }

}

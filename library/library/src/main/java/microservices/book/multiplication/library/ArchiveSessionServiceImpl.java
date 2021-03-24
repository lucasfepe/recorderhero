package microservices.book.multiplication.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.configuration.AdminLogin;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.model.NoteHistory;
import microservices.book.multiplication.model.Session;
import microservices.book.multiplication.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})
public class ArchiveSessionServiceImpl implements ArchiveSessionService {

    private UriComponentsBuilder builder;
    private UriComponentsBuilder builder2;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public ArchiveSessionServiceImpl(RestTemplateBuilder restTemplateBuilder,
                                     DatabaseApi databaseApi,
                                     AdminLogin adminLogin,
                                     @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }

    @Override
    public void archiveSession(GameDTO gameDTO, String username) throws JsonProcessingException {
        this.restTemplate = restTemplateBuilder.basicAuthentication(adminLogin.getUsername(), adminLogin.getPassword()).build();
//        ResponseEntity<Session> forEntity = null;
        ResponseEntity<EntityModel<Session>> forEntity = null;
        ResponseEntity<EntityModel<User>> userResponse = null;
        ResponseEntity<EntityModel<NoteHistory>> noteHistoryResponse = null;
        ResponseEntity<User> userResponseEntity = null;
        Optional<Link> self;
        int noteHistoryId;
        int sessionId = 0;
        String userId = null;

        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getPutSession() + gameDTO.getSessionId());

        log.info("Querying: {}", builder.toUriString());
        try {
            forEntity = restTemplate.exchange(
                    builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );
//            forEntity = restTemplate.getForEntity(builder.toUriString(), Session.class);
            self = forEntity.getBody().getLink("self");
             sessionId = Integer.valueOf(new File(self.get().getHref()).getName());
            log.info("Got session successfully, Id: {}",  sessionId);




        }catch(Exception e){
            log.error("Couldn't get session",e);
        }



        forEntity.getBody().getContent().setStartTime(gameDTO.getTime_array_start()[0]);
        forEntity.getBody().getContent().setEndTime(gameDTO.getTime_array_end()[gameDTO.getTime_array_end().length - 1]);


        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getPutSession() + sessionId);

        log.info("Querying: {}", builder.toUriString());
        try {
            restTemplate.put(builder.toUriString(), forEntity);
            log.info("Put session successfully. Id: {}", sessionId);
        }catch(Exception e){
            log.error("Couldn't put session",e);
        }

        //create association of session to user

            //find user
        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getFindByUsernameUri() )
        .queryParam("username",username);

        log.info("Querying: {}", builder.toUriString());
        try {
            userResponse = restTemplate.exchange(
                    builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );
//            forEntity = restTemplate.getForEntity(builder.toUriString(), Session.class);
            self = userResponse.getBody().getLink("self");
            userId = new File(self.get().getHref()).getName();
            log.info("Got User successfully, Id: {}",  userId);




        }catch(Exception e){
            log.error("Couldn't get User",e);
        }



        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getPutUserInSessionA() + sessionId +
                        databaseApi.getPutUserInSessionB());

        log.info("Querying: {}", builder.toUriString());
        builder2 = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getFindUser() + userId);
        log.info("Body: {}", builder2.toUriString());
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-type", "text/uri-list");
            HttpEntity<String> httpEntity
                    = new HttpEntity<>(builder2.toUriString(), requestHeaders);
            restTemplate.exchange( builder.toUriString(),
                    HttpMethod.PUT, httpEntity, String.class);

            log.info("Associate session with user successfully");
        }catch(Exception e){
            log.error("Couldn't associate session with user",e);
        }




        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json;



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);




            for (int i = 0; i < gameDTO.getNote_input_array().length; i++) {
                //Only add note_history if they didn't peek
                if (!gameDTO.getPeeked_array()[i]) {
                    NoteHistory nh = new NoteHistory();
                    nh.setNote(gameDTO.getNote_shown_array()[i]);
                    nh.setNoteChosen(gameDTO.getNote_input_array()[i]);
                    nh.setReactionTime(gameDTO.getTime_array_end()[i] - gameDTO.getTime_array_start()[i]);


                    json = ow.writeValueAsString(nh);
                    HttpEntity<String> entity = new HttpEntity<String>(json, headers);


                    builder = UriComponentsBuilder
                            .fromUriString(databaseHostUrl + databaseApi.getPostNoteHistory());

                    log.info("Querying: {}", builder.toUriString());
                    try {
                        noteHistoryResponse = restTemplate.exchange(
                                builder.toUriString(), HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
                                });

//                                restTemplate.postForEntity(builder.toUriString(), nh, String.class);
                        log.info("Post note history successfully.");
                    }catch(Exception e){
                        log.error("Couldn't post note history",e);
                    }

                    self = noteHistoryResponse.getBody().getLink("self");
                    noteHistoryId = Integer.valueOf(new File(self.get().getHref()).getName());

                    //create association with session
                    builder = UriComponentsBuilder
                            .fromUriString(databaseHostUrl + databaseApi.getPutSessionInNoteHistoryA() + noteHistoryId +
                                    databaseApi.getPutSessionInNoteHistoryB());

                    log.info("Querying: {}", builder.toUriString());
                    builder2 = UriComponentsBuilder
                            .fromUriString(databaseHostUrl + databaseApi.getFindSession() + sessionId);
                    log.info("Body: {}", builder2.toUriString());
                    try {
                        HttpHeaders requestHeaders = new HttpHeaders();
                        requestHeaders.add("Content-type", "text/uri-list");
                        HttpEntity<String> httpEntity
                                = new HttpEntity<>(builder2.toUriString(), requestHeaders);
                        restTemplate.exchange( builder.toUriString(),
                                HttpMethod.PUT, httpEntity, String.class);

                        log.info("Associate note history with session successfully");
                    }catch(Exception e){
                        log.error("Couldn't associate note history with session",e);
                    }


            }




    }
}}

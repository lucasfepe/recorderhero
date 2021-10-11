package microservices.book.multiplication.game;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.client.EndGameDTO;
import microservices.book.multiplication.client.GameDTO;
import microservices.book.multiplication.client.LibraryApiClient;
import microservices.book.multiplication.configuration.AdminLogin;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})


public class GameServiceImpl implements GameService {


    private final PopulateSessionNotes populateSessionNotes;
    private final EnumerateAccidentals enumerateAccidentals;
    private final LibraryApiClient libraryApiClient;
    private UriComponentsBuilder builder;
    private UriComponentsBuilder builderPUT;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;


    public GameServiceImpl(PopulateSessionNotes populateSessionNotes, EnumerateAccidentals enumerateAccidentals, LibraryApiClient libraryApiClient, RestTemplateBuilder restTemplateBuilder,
                           DatabaseApi databaseApi,
                           AdminLogin adminLogin,
                           @Value("${service.database.host}") final String databaseHostUrl) {
        this.populateSessionNotes = populateSessionNotes;
        this.enumerateAccidentals = enumerateAccidentals;
        this.libraryApiClient = libraryApiClient;
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }

    @Override
    public AccidentalsDTO enumerateAccidentals(UserCoursesDTO userCourse) {

        return enumerateAccidentals.execute(userCourse, populateSessionNotes.execute(userCourse));


    }

    @Override
    public EndGameDTO levelAnalysis(GameDTO gameDTO, Authentication authentication, double score) {
        boolean levelup = false;
        boolean courseComplete = false;
        int level = gameDTO.getLevel();
        int length = gameDTO.getNote_input_array().length;
        if(length > 0 && level < 7){
            level++;
            libraryApiClient.levelUp(gameDTO, authentication);
            levelup = true;
        }else if(gameDTO.isChallenge()){
            if(gameDTO.getLevelScoreToPass() < score){
                if(gameDTO.getMaxLevel() != gameDTO.getLevel()){
                    level++;
                    libraryApiClient.levelUp(gameDTO, authentication);
                    levelup = true;
                }else {
                    libraryApiClient.courseComplete(gameDTO, authentication);
                    courseComplete = true;
                }
            }
        }
        return new EndGameDTO("",levelup,level, 0, false, courseComplete);
    }

    @Override
    public void checkHighScore(double score, GameDTO gameDTO, Authentication authentication) {
boolean ishighscore = false;
        String highScoreId = null;
        String oldHighScoreId = null;


        //check if score is higher than in database if so then replace
    String username = authentication.getName().substring(authentication.getName().lastIndexOf(":") + 1);

        try {

            Jwt authenticationPrincipal = (Jwt) authentication.getPrincipal();
            this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + authenticationPrincipal.getTokenValue()).build();
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getFindHighScoreByCourseAndChallengeAndUser())
                    .queryParam("code", gameDTO.getCourseCode())
                    .queryParam("challengeCode", gameDTO.getChallengeId())
                    .queryParam("username", username);
            log.info("GET to Query: {}", builder.toUriString());


            int highScorePrev = 0;
            ResponseEntity<String> highScore = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,null,String.class);
            String body = highScore.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(body);
            JsonNode jsonNode = actualObj.get("content");
            JsonNode finallyHighScore = jsonNode.get(0);
//if first high score do nothing
        if(finallyHighScore.has("score")) {
            highScorePrev = Integer.valueOf(finallyHighScore.get("score").toString());
        }
            if(highScorePrev < score){

                //delete previous record if present
                if(highScorePrev > 0){
                    String highScorePrevId = jsonNode.get(0).get("id").toString();

                    builder = UriComponentsBuilder
                            .fromUriString(databaseHostUrl + databaseApi.getPostHighScore() + "/" + highScorePrevId);
                    log.info("DELETE to Query: {}", builder.toUriString());
                    restTemplate.delete(builder.toUriString());
                }

                    //insert new score
                    ishighscore = true;

                    builder = UriComponentsBuilder
                            .fromUriString(databaseHostUrl + databaseApi.getPostHighScore());
                    log.info("POST to Query: {}", builder.toUriString());
                    HighScore hs = new HighScore((int)score);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    String json = "{\"score\":" + score + "}";
                    HttpEntity<String> entity = new HttpEntity<String>(json, headers);



                    ResponseEntity<EntityModel<HighScore>> highscore = restTemplate.exchange(
                            builder.toUriString(), HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
                            }
                    );

                    Optional<Link> self = highscore.getBody().getLink("self");


                     highScoreId = new File(self.get().getHref()).getName();
                    log.info("Inserted high schore successfully. Id: {}", highScoreId);




            }else{
                    log.info("HIgh score found but too high to beat. Id: {}");
                }





        } catch (Exception e) {
            log.error("There was a problem finding high schore or inserting higher one.", e);
        }

        if(ishighscore) {
            try {
//find user id for later from username

                builder =  UriComponentsBuilder
                        .fromUriString(databaseHostUrl + databaseApi.getFindByUsernameUri())
                        .queryParam("username",username);

                ResponseEntity<EntityModel<User>> user = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,null,new ParameterizedTypeReference<>() {
                });
                String userId = new File(user.getBody().getLink("self").get().getHref()).getName();

//associate user with high score inserted
                builder = UriComponentsBuilder
                        .fromUriString(databaseHostUrl + databaseApi.getPostHighScore() + "/" + highScoreId + "/user");
                builderPUT = UriComponentsBuilder
                        .fromUriString(databaseHostUrl + "/users/" + userId);


                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.add("Content-type", "text/uri-list");
                HttpEntity<String> httpEntity
                        = new HttpEntity<>(builderPUT.toUriString(), requestHeaders);
                ResponseEntity<String> entity = restTemplate.exchange(builder.toUriString(),
                        HttpMethod.PUT, httpEntity, String.class);


                log.info("PUT to Query: {}, body: {}", builder.toUriString(), builderPUT.toUriString());


                log.info("Inserted high score successfully. Id: {}");


                //associate high score with course


                builder = UriComponentsBuilder
                        .fromUriString(databaseHostUrl + databaseApi.getPostHighScore() + "/" + highScoreId + "/course");
                builderPUT = UriComponentsBuilder
                        .fromUriString(databaseHostUrl + "/courses/" + gameDTO.getCourseCode());

httpEntity
                        = new HttpEntity<>(builderPUT.toUriString(), requestHeaders);
                 entity = restTemplate.exchange(builder.toUriString(),
                        HttpMethod.PUT, httpEntity, String.class);


                log.info("PUT to Query: {}, body: {}", builder.toUriString(), builderPUT.toUriString());


                log.info("Associated high score with course. Id: {}");


                //associate high score with challenge
                    //challengeID is actually challenge code but need to pass challenge id

                builder = UriComponentsBuilder
                        .fromUriString(databaseHostUrl + databaseApi.getFindChallengeByCode())
                .queryParam("code",gameDTO.getChallengeId());
                ResponseEntity<EntityModel<Challenges>> challenge = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
                Optional<Link> optional = challenge.getBody().getLinks().getLink("self");
                String challengeId = new File(optional.get().getHref()).getName();
                builder = UriComponentsBuilder
                        .fromUriString(databaseHostUrl + databaseApi.getPostHighScore() + "/" + highScoreId + "/challenge");
                builderPUT = UriComponentsBuilder
                        .fromUriString(databaseHostUrl + "/challengeses/" + challengeId);

                httpEntity
                        = new HttpEntity<>(builderPUT.toUriString(), requestHeaders);
                entity = restTemplate.exchange(builder.toUriString(),
                        HttpMethod.PUT, httpEntity, String.class);


                log.info("PUT to Query: {}, body: {}", builder.toUriString(), builderPUT.toUriString());


                log.info("Associated high score with challenge. Id: {}");




            } catch (Exception e) {
                log.error("There was a problem derp.", e);
            }

        }

    }

}

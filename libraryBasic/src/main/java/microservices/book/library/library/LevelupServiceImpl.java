package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netflix.ribbon.proxy.annotation.Http;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.client.GameDTO;
import microservices.book.library.configuration.AdminLogin;
import microservices.book.library.configuration.DatabaseApi;
import microservices.book.library.model.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})
public class LevelupServiceImpl implements LevelupService {

    private UriComponentsBuilder builder;
    private UriComponentsBuilder builder2;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public LevelupServiceImpl(RestTemplateBuilder restTemplateBuilder,
                              DatabaseApi databaseApi,
                              AdminLogin adminLogin,
                              @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }




    @Override
    public void levelup(GameDTO gameDTO, String name, Authentication authentication) throws IOException {
        Jwt authenticationPrincipal = (Jwt) authentication.getPrincipal();
        this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + authenticationPrincipal.getTokenValue()).build();
//        ResponseEntity<Session> forEntity = null;
        ResponseEntity<EntityModel<UserCourses>> forEntity = null;
        ResponseEntity<EntityModel<Level>> levelEntity = null;
        ResponseEntity<EntityModel<User>> userResponse = null;
        ResponseEntity<EntityModel<NoteHistory>> noteHistoryResponse = null;
        ResponseEntity<User> userResponseEntity = null;
        Optional<Link> selfUserCourse;

        int noteHistoryId;
        int userCourseId = 0;
        String userId = null;


        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getFindUserCourseByUserUsernameAndCourseCode())
                .queryParam("username",name.substring(name.lastIndexOf(":") + 1))
                .queryParam("code","SRTrHI");
        log.info("Querying GET: {}", builder.toUriString());
        try {
            forEntity = restTemplate.exchange(
                    builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );}catch(Exception e){
            log.error("Couldn't get usserCourse",e);
        }
//            forEntity = restTemplate.getForEntity(builder.toUriString(), Session.class);
        selfUserCourse = forEntity.getBody().getLink("self");
        userCourseId = Integer.valueOf(new File(selfUserCourse.get().getHref()).getName());

//        builder = UriComponentsBuilder
//                .fromUriString(databaseHostUrl + databaseApi.getPutUserCourse() + gameDTO.getUserCourseId());
//
//        log.info("Querying PUT: {}", builder.toUriString());
        try {
            forEntity = restTemplate.exchange(
                    builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );}catch(Exception e){
            log.error("Couldn't get usserCourse",e);
        }
//            forEntity = restTemplate.getForEntity(builder.toUriString(), Session.class);
            selfUserCourse = forEntity.getBody().getLink("self");
        Optional<Link> level = forEntity.getBody().getLink("level");


            userCourseId = Integer.valueOf(new File(selfUserCourse.get().getHref()).getName());
            log.info("Got userCourse successfully, Id: {}",  userCourseId);

            //get level of user course from database
            builder = UriComponentsBuilder
                    .fromUriString(String.valueOf(level.get().getHref()))
                    ;
            log.info("Querying GET: {}", builder.toUriString());
            try {
                levelEntity = restTemplate.exchange(
                        builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        }
                );}catch(Exception e){
                log.error("Couldn't get user Course level",e);
            }


        Integer levelLevel = levelEntity.getBody().getContent().getLevel();

        log.info("Got level successfully, level: {}",  levelLevel);








//        forEntity.getBody().getContent().setComplete(false);
//        forEntity.getBody().getContent().setLevel(forEntity.getBody().getContent().getLevel() + 1);
//        forEntity.getBody().getContent().setEndTime(gameDTO.getTime_array_end()[gameDTO.getTime_array_end().length - 1]);





//level up the user course
        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getPutUserCourse() + userCourseId + "/level");

        log.info("Querying PUT: {}, BODY: {}", builder.toUriString(), "http://localhost:8484/levels/" + (levelLevel + 1));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/uri-list");
        HttpEntity<String> levelHttpEntity
                = new HttpEntity<>("http://localhost:8484/levels/" + (levelLevel + 1), requestHeaders);
        restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, levelHttpEntity, String.class);











    }
}

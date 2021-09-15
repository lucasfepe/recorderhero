package microservices.book.multiplication.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.configuration.AdminLogin;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.configuration.LibraryApi;
import microservices.book.multiplication.game.DatabaseService;
import microservices.book.multiplication.model.HighScore;
import microservices.book.multiplication.model.Session;
import microservices.book.multiplication.model.SessionDTO;
import microservices.book.multiplication.model.UserCoursesDTO;
import microservices.book.multiplication.util.Notes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties({LibraryApi.class})
public class LibraryApiClient  {


    private UriComponentsBuilder builder;
    private RestTemplate restTemplate;
    private final String libraryHostUrl;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private LibraryApi libraryApi;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public LibraryApiClient(RestTemplateBuilder restTemplateBuilder,
                            LibraryApi libraryApi,
                            DatabaseApi databaseApi,
                            @Value("${service.library.host}") final String libraryHostUrl,

                            @Value("${service.database.host}") final String databaseHostUrl) {
        this.libraryApi = libraryApi;
        this.databaseApi = databaseApi;
        this.restTemplateBuilder = restTemplateBuilder;
        this.libraryHostUrl = libraryHostUrl;
        this.databaseHostUrl = databaseHostUrl;
    }

    public boolean libraryArchive(GameDTO gameDTO, Authentication authentication) {


        try {
//            username = username.substring(username.lastIndexOf(":") + 1);
            Jwt authenticationPrincipal = (Jwt) authentication.getPrincipal();


//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", "Bearer " + );
            this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + authenticationPrincipal.getTokenValue()).build();
            builder = UriComponentsBuilder
                    .fromUriString(libraryHostUrl + libraryApi.getPostSession());
            log.info("Querying: {}", builder.toUriString());
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            String json = ow.writeValueAsString(session);
//            log.info(json);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<String> entity = new HttpEntity<String>(json, headers);

            log.info("{}",gameDTO);
            ResponseEntity<String> r = restTemplate.postForEntity(
                    builder.toUriString(), gameDTO,
                    String.class);
            log.info("Gamification service response: {}", r.getStatusCode());
            return r.getStatusCode().is2xxSuccessful();

        } catch (Exception e) {
            log.error("There was a problem creating new session.", e);
            return false;
        }


    }

    public boolean levelUp(GameDTO gameDTO, Authentication authentication) {


        try {

            Jwt authenticationPrincipal = (Jwt) authentication.getPrincipal();


            this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + authenticationPrincipal.getTokenValue()).build();
            builder = UriComponentsBuilder
                    .fromUriString(libraryHostUrl + libraryApi.getPutUserCourse());
            log.info("Querying: {}", builder.toUriString());


            log.info("{}",gameDTO);
            ResponseEntity<String> r = restTemplate.postForEntity(
                    builder.toUriString(), gameDTO,
                    String.class);
            log.info("Gamification service response: {}", r.getStatusCode());
            return r.getStatusCode().is2xxSuccessful();

        } catch (Exception e) {
            log.error("There was a problem creating new session.", e);
            return false;
        }


    }
    public boolean courseComplete(GameDTO gameDTO, Authentication authentication) {
    int userCourseId = 0;

        try {

            Jwt authenticationPrincipal = (Jwt) authentication.getPrincipal();


            this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + authenticationPrincipal.getTokenValue()).build();

            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getFindUserCourse())
                    .queryParam("username", authentication.getName().substring(authentication.getName().lastIndexOf(":") + 1))
                    .queryParam("code", gameDTO.getCourseCode());

            ResponseEntity<EntityModel<UserCoursesDTO>> userCourse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });

            Optional<Link> self = userCourse.getBody().getLink("self");


            userCourseId = Integer.valueOf(new File(self.get().getHref()).getName());

            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/userCourseses/" + userCourseId);
            log.info("Querying: {}, with body: {}", builder.toUriString(), "{\"complete\":true}");


            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-type", "application/json");
            HttpEntity<String> httpEntity
                    = new HttpEntity<>("{\"complete\":true}", requestHeaders);
            ResponseEntity<String> entity = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.PUT, httpEntity, String.class);
            return true;

        } catch (Exception e) {
            log.error("There was a problem creating new session.", e);
            return false;
        }


    }

}

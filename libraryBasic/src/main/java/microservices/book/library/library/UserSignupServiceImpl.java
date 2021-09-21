package microservices.book.library.library;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.configuration.AdminLogin;
import microservices.book.library.configuration.DatabaseApi;
import microservices.book.library.model.User;
import microservices.book.library.model.UserCourses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})
public class UserSignupServiceImpl implements UserSignupService {

    private UriComponentsBuilder builder;
    private UriComponentsBuilder builder2;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public UserSignupServiceImpl(RestTemplateBuilder restTemplateBuilder,
                                 DatabaseApi databaseApi,
                                 AdminLogin adminLogin,
                                 @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }



    @Override
    public boolean isFirstTimeUser(String username){
        this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue()).build();
        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getFindByUserUsernameUri())
                .queryParam("username", username);
        log.info("Querying GET: {}", builder.toUriString());
        ResponseEntity<EntityModel> forEntity = restTemplate.getForEntity(builder.toUriString(), EntityModel.class);
        if(((LinkedHashMap)((ArrayList)forEntity.getBody().getContent()).get(0)).containsKey("rel")){
            return true;
        };
        return false;
    }


    @Override
    public void createNewUser(String username) {
        this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue()).build();

        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + "/users");
        log.info("Querying POST: {}", builder.toUriString());
        try {
            //create user
            URI uri = restTemplate.postForLocation(
                    builder.toUriString(),new User(username), User.class);


            String userId = new File(uri.toString()).getName();

            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/userCourseses");
            log.info("Querying POST: {}", builder.toUriString());
            //create user_course
            uri = restTemplate.postForLocation(
                    builder.toUriString(), new UserCourses(), UserCourses.class);
            String userCourseId = new File(uri.toString()).getName();

           //associate with user
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/userCourseses/" + userCourseId + "/user");
            builder2 = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/users/" + userId);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-type", "text/uri-list");
            log.info("Querying PPUT: {}, with body: {}", builder.toUriString(), builder2.toUriString());
            HttpEntity<String> httpEntity
                    = new HttpEntity<>(builder2.toUriString(), requestHeaders);
            restTemplate.exchange( builder.toUriString(),
                    HttpMethod.PUT, httpEntity, String.class);

            //associate with course
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/userCourseses/" + userCourseId + "/course");
            builder2 = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/courseses/" + "SRTrHI");
             requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-type", "text/uri-list");
            log.info("Querying PPUT: {}, with body: {}", builder.toUriString(), builder2.toUriString());
           httpEntity
                    = new HttpEntity<>(builder2.toUriString(), requestHeaders);
            restTemplate.exchange( builder.toUriString(),
                    HttpMethod.PUT, httpEntity, String.class);

            //associate with level
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/userCourseses/" + userCourseId + "/level");
            builder2 = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/levels/" + "1");
            requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-type", "text/uri-list");
            log.info("Querying PPUT: {}, with body: {}", builder.toUriString(), builder2.toUriString());
            httpEntity
                    = new HttpEntity<>(builder2.toUriString(), requestHeaders);
            restTemplate.exchange( builder.toUriString(),
                    HttpMethod.PUT, httpEntity, String.class);

            log.info("Cddd");

        }catch(Exception e){
            log.error("Couldn't PUT user",e);
        }

    }
}

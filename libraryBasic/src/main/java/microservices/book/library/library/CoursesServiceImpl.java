package microservices.book.library.library;

import lombok.extern.slf4j.Slf4j;
import microservices.book.library.client.GameDTO;
import microservices.book.library.configuration.AdminLogin;
import microservices.book.library.configuration.DatabaseApi;
import microservices.book.library.model.*;
import microservices.book.library.util.Clef;
import microservices.book.library.util.Mode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
public class CoursesServiceImpl implements CoursesService {

    private UriComponentsBuilder builder;
    private UriComponentsBuilder builder2;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public CoursesServiceImpl(RestTemplateBuilder restTemplateBuilder,
                              DatabaseApi databaseApi,
                              AdminLogin adminLogin,
                              @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }




    @Override
    public String getCourseFromInstrumentClefAndCourseCodeEnding(Mode instrument, Clef clef, String codeEnding, Authentication authentication) {
        Jwt authenticationPrincipal = (Jwt) authentication.getPrincipal();
        this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + authenticationPrincipal.getTokenValue()).build();
        ResponseEntity<EntityModel<Courses>> course = null;
        //get course from given parameters
        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getFindCourseByInstrumentClefCodeending())
                .queryParam("instrument",instrument)
                .queryParam("clef",clef)
                .queryParam("code",codeEnding);

        log.info("Querying GET: {}", builder.toUriString());
        try {
           course = restTemplate.exchange(
                    builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>(){}
            );

        }catch(Exception e){
            log.error("Couldn't get Course ",e);
        }
        String courseCode = new File(course.getBody().getLink("self").get().getHref()).getName();

        return courseCode;
    }
}

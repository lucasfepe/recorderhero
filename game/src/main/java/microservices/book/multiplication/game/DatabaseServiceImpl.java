package microservices.book.multiplication.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.configuration.AdminLogin;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.model.Session;
import microservices.book.multiplication.model.SessionDTO;
import microservices.book.multiplication.model.User;
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
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})
public class DatabaseServiceImpl implements DatabaseService {


    private UriComponentsBuilder builder;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public DatabaseServiceImpl(RestTemplateBuilder restTemplateBuilder,
                               DatabaseApi databaseApi,
                               AdminLogin adminLogin,
                               @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }


    @Override
    public String newSession(UserCoursesDTO userCourse, Authentication authentication) {
        SessionDTO session = new SessionDTO();
        session.setClef(userCourse.getCourse().getClef());
        session.setKeym(userCourse.getLevel().getKeym());
        session.setMode(userCourse.getCourse().getInstrument());
        session.setHighNote(Notes.values()[userCourse.getCourse().getLowNote().ordinal() + userCourse.getLevel().getHighNote()]);
        session.setLowNote(Notes.values()[userCourse.getCourse().getLowNote().ordinal() + userCourse.getLevel().getLowNote()]);
        session.setChallengeCode(userCourse.isChallenge() ? userCourse.getLevel().getChallengeCode() : null);
        String id = null;

        try {

            Jwt authenticationPrincipal = (Jwt) authentication.getPrincipal();
            this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + authenticationPrincipal.getTokenValue()).build();
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getPostSession());
            log.info("Post to Querying: {}", builder.toUriString());
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(session);


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(json, headers);
            ResponseEntity<EntityModel<Session>> objectResponseEntity = restTemplate.exchange(
                    builder.toUriString(), HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
                    }
            );

            Optional<Link> self = objectResponseEntity.getBody().getLink("self");

            if (!self.isEmpty()) {
                id = new File(self.get().getHref()).getName();
            }

            log.info("Created new session successfully. Id: {}", id);

        } catch (Exception e) {
            log.error("There was a problem creating new session.", e);
        }

    return id;
    }
}

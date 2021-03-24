package microservices.book.multiplication.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.configuration.AdminLogin;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.configuration.LibraryApi;
import microservices.book.multiplication.game.DatabaseService;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties({LibraryApi.class})
public class LibraryApiClient  {


    private UriComponentsBuilder builder;
    private RestTemplate restTemplate;
    private final String libraryHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private LibraryApi libraryApi;
    private AdminLogin adminLogin;

    public LibraryApiClient(RestTemplateBuilder restTemplateBuilder,
                            LibraryApi libraryApi,

                            @Value("${service.library.host}") final String libraryHostUrl) {
        this.libraryApi = libraryApi;

        this.restTemplateBuilder = restTemplateBuilder;
        this.libraryHostUrl = libraryHostUrl;
    }

    public boolean libraryArchive( GameDTO gameDTO, String username, String password) {


        try {

            this.restTemplate = restTemplateBuilder.basicAuthentication(username, password).build();
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
}

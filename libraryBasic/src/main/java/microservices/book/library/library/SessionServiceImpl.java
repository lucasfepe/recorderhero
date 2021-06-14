package microservices.book.library.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.client.GameDTO;
import microservices.book.library.configuration.AdminLogin;
import microservices.book.library.configuration.DatabaseApi;
import microservices.book.library.model.NoteHistory;
import microservices.book.library.model.Session;
import microservices.book.library.model.User;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})
public class SessionServiceImpl implements SessionService {

    private UriComponentsBuilder builder;
    private UriComponentsBuilder builder2;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public SessionServiceImpl(RestTemplateBuilder restTemplateBuilder,
                              DatabaseApi databaseApi,
                              AdminLogin adminLogin,
                              @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }


    @Override
    public String getAllSession(String username) {
        this.restTemplate = restTemplateBuilder.build();
        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getFindSessionsByUserUsernameUri())
                .queryParam("username",username);
        log.info("Querying GET: {}", builder.toUriString());
        try {
            ResponseEntity<String> response =  restTemplate.getForEntity(
                    builder.toUriString(),String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String ha = root.get("content").toString();

            return ha;

        }catch(Exception e){
            log.error("Couldn't get session",e);
        }
        return null;
    }
}

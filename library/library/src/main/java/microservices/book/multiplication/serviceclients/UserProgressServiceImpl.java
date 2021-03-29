package microservices.book.multiplication.serviceclients;

import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.configuration.AdminLogin;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.model.User;
import microservices.book.multiplication.library.UserProgressService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})
public class UserProgressServiceImpl implements UserProgressService {

    private UriComponentsBuilder builder;
    private RestTemplate restTemplate;
    private AdminLogin adminLogin;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;

    public UserProgressServiceImpl(RestTemplateBuilder restTemplateBuilder,
                                   DatabaseApi databaseApi, AdminLogin adminLogin,
                                   @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.restTemplateBuilder = restTemplateBuilder;
        this.adminLogin = adminLogin;
        this.databaseHostUrl = databaseHostUrl;
    }

    @Override
    public Optional<Boolean> isCourseUnfinished(final String username) throws UsernameNotFoundException {
        try {
            this.restTemplate = restTemplateBuilder.basicAuthentication(adminLogin.getUsername(), adminLogin.getPassword()).build();
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getFindByUsernameUri())
                    .queryParam("username", username);

            log.info("Querying: {}", builder.toUriString());
            ResponseEntity<JSONObject> r =  restTemplate.getForEntity(
                    builder.toUriString(),
                    JSONObject.class);
            List<ArrayList> a = (List<ArrayList>) r.getBody().get("links");
            Map b = (Map) a.get(0);

            String href = (String) b.get("href");
            String id = href.substring(href.lastIndexOf("/") + 1);

            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + "/users/" + id);
            log.info("Querying: {}", builder.toUriString());
            ResponseEntity<EntityModel<User>> re =  restTemplate.exchange(
                    RequestEntity.get(URI.create(builder.toUriString()))

                            .build()
                    , new ParameterizedTypeReference<EntityModel<User>>() {
                    });


            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getCountByUserUsernameUri())
                    .queryParam("username", username);
            log.info("Querying: {}", builder.toUriString());
            ResponseEntity<Integer> ra = restTemplate.getForEntity(
                    builder.toUriString(),
                    Integer.class);

            log.info("Database service response: {}", r.getStatusCode());
//            return r.getStatusCode().is2xxSuccessful();
            Optional<Boolean> opt = Optional.ofNullable(ra.getBody() > 0);
            return opt;

        } catch (Exception e) {
            log.error("There was a problem finding the user.",e);
//            return false;

        }
        throw new UsernameNotFoundException(username);
    }


}

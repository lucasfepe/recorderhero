package microservices.book.library.serviceclients;

import lombok.extern.slf4j.Slf4j;
import microservices.book.library.configuration.DatabaseApi;
import microservices.book.library.library.UserProgressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties(DatabaseApi.class)
public class UserProgressServiceImpl implements UserProgressService {

    private UriComponentsBuilder builder;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;

    public UserProgressServiceImpl(RestTemplateBuilder restTemplateBuilder,
                                   DatabaseApi databaseApi,
                                   @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }

    @Override
    public Optional<Boolean> isCourseUnfinished(final String username)
//            throws UsernameNotFoundException
    {
        try {
           //Get count of userCourses assigned to a user
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getCountByUserUsernameUri())
                    .queryParam("username", username);
            log.info("Querying: {}", builder.toUriString());
            ResponseEntity<Integer> ra = restTemplate.getForEntity(
                    builder.toUriString(),
                    Integer.class);

            log.info("Database service response: {}", ra.getStatusCode());
//            return r.getStatusCode().is2xxSuccessful();
            Optional<Boolean> opt = Optional.ofNullable(ra.getBody() > 0);
            return opt;

        } catch (Exception e) {
            log.error("There was a problem finding the user.",e);
//            return false;

        }
//        throw new UsernameNotFoundException(username);
        return Optional.of(true);
    }


}

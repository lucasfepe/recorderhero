package microservices.book.library.library;

import lombok.extern.slf4j.Slf4j;
import microservices.book.library.configuration.AdminLogin;
import microservices.book.library.configuration.DatabaseApi;
import microservices.book.library.model.HighScore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})
public class HighScoreServiceImpl implements HighScoreService {

    private UriComponentsBuilder builder;
    private UriComponentsBuilder builder2;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public HighScoreServiceImpl(RestTemplateBuilder restTemplateBuilder,
                                DatabaseApi databaseApi,
                                AdminLogin adminLogin,
                                @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }


    @Override
    public Collection<HighScore> find(String courseCode, String challengeCode) {
        this.restTemplate = restTemplateBuilder.build();
       ResponseEntity<PagedModel<HighScore>> highScores = null;
        //get course from given parameters
        builder = UriComponentsBuilder
                .fromUriString(databaseHostUrl + databaseApi.getListHighScores())
                .queryParam("courseCode",courseCode)
                .queryParam("challengeCode",challengeCode);

        log.info("Querying GET: {}", builder.toUriString());
        try {
            highScores =  restTemplate.exchange(
                    builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );

        }catch(Exception e){
            log.error("Couldn't get high scores ",e);
        }


        return  highScores.getBody().getContent();
    }
}

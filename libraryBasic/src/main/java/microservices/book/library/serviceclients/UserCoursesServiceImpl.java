package microservices.book.library.serviceclients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import microservices.book.library.configuration.DatabaseApi;
import microservices.book.library.library.UserCoursesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@EnableConfigurationProperties(DatabaseApi.class)
public class UserCoursesServiceImpl implements UserCoursesService {

    private UriComponentsBuilder builder;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private DatabaseApi databaseApi;
    private RestTemplateBuilder restTemplateBuilder;




    public UserCoursesServiceImpl(

            DatabaseApi databaseApi, RestTemplateBuilder restTemplateBuilder, @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.restTemplateBuilder = restTemplateBuilder;

        this.databaseHostUrl = databaseHostUrl;
    }



    @Override
    public String getAllUserCoursesByUsername(String username) {
        try {

            this.restTemplate = restTemplateBuilder.build();

            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getFindByUserUsernameUri())
                    .queryParam("username", username);
            log.info("Querying: {}", builder.toUriString());
            ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String ha = root.get("content").toString();


//            List<UserCourses> myObjects = mapper.readValue(ha, new TypeReference<List<UserCourses>>(){});
            log.info("Database service response: {}", response.getStatusCode());
//            return r.getStatusCode().is2xxSuccessful();

            return ha;

        } catch (Exception e) {
            log.error("There was a problem finding the user.",e);
//            return false;

        }
//        throw new UsernameNotFoundException(username);
        return "s";
    }
}

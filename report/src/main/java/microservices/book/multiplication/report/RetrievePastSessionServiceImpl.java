package microservices.book.multiplication.report;

import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.configuration.AdminLogin;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.model.NoteHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@EnableConfigurationProperties({DatabaseApi.class, AdminLogin.class})
public class RetrievePastSessionServiceImpl implements RetrievePastSessionService {


    private UriComponentsBuilder builder;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private DatabaseApi databaseApi;
    private AdminLogin adminLogin;

    public RetrievePastSessionServiceImpl(RestTemplateBuilder restTemplateBuilder,
                                          DatabaseApi databaseApi,
                                          AdminLogin adminLogin,
                                          @Value("${service.database.host}") final String databaseHostUrl) {
        this.databaseApi = databaseApi;
        this.adminLogin = adminLogin;
        this.restTemplateBuilder = restTemplateBuilder;
        this.databaseHostUrl = databaseHostUrl;
    }

  


    @Override
    public Collection<NoteHistory> execute(int sessionID) {



        try {

            this.restTemplate = restTemplateBuilder.defaultHeader("Authorization","Bearer " + ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue()).build();
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getRetrievePastNotes())
                    .queryParam("id", sessionID);
            log.info("Post to Querying: {}", builder.toUriString());

            ResponseEntity<PagedModel<NoteHistory>> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<PagedModel<NoteHistory>>() {
            });


            return new ArrayList<NoteHistory>(exchange.getBody().getContent());
        } catch (Exception e) {
            log.error("There was a problem retrieving past notes.", e);
        }

    return null;
    }


}

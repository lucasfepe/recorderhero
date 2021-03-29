package microservices.book.multiplication.jwt;

import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.model.Authorities;
import microservices.book.multiplication.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(DatabaseApi.class)
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    private UriComponentsBuilder builder;
    private DatabaseApi databaseApi;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;

    public JwtInMemoryUserDetailsService(RestTemplateBuilder restTemplateBuilder, DatabaseApi databaseApi,
                                                @Value("${service.database.host}") final String databaseHostUrl){
        this.databaseApi = databaseApi;
        this.restTemplate = restTemplateBuilder.build();
        this.databaseHostUrl = databaseHostUrl;
    }

//    static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
//
//    static {
//        inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
//                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      /*  Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();*/

        try {
            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getFindByUsernameUri())
                    .queryParam("username", username);
            log.info("Querying: {}", builder.toUriString());
            ResponseEntity<EntityModel<User>> responseEntity1 =
                    restTemplate.exchange(
                            RequestEntity.get(URI.create(builder.toUriString()))

                                    .build()
                            , new ParameterizedTypeReference<EntityModel<User>>() {
                            });


            builder = UriComponentsBuilder
                    .fromUriString(databaseHostUrl + databaseApi.getFindAuthByUsernameUri())
                    .queryParam("username", username);
            log.info("Querying: {}", builder.toUriString());
            ResponseEntity<EntityModel<List<Authorities>>> responseEntity2 =
                    restTemplate.exchange(
                            RequestEntity.get(URI.create(builder.toUriString()))

                                    .build()
                            , new ParameterizedTypeReference<EntityModel<List<Authorities>>>() {
                            });
            if (responseEntity1.getStatusCode() == HttpStatus.OK && responseEntity2.getStatusCode() == HttpStatus.OK) {
                EntityModel<User> resource1 = responseEntity1.getBody();
                User user = resource1.getContent();

                EntityModel<List<Authorities>> resource2 = responseEntity2.getBody();
                String[] authorities = resource2.getContent().stream().map(a -> a.getAuthority()).toArray(String[]::new);

                PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

                return org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .passwordEncoder(encoder::encode)
                        .accountLocked(!user.isEnabled())
                        .authorities(authorities).build();
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        throw new UsernameNotFoundException(username);


    }

}
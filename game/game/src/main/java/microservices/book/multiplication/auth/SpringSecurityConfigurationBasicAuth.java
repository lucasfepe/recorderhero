package microservices.book.multiplication.auth;


import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.configuration.DatabaseApi;
import microservices.book.multiplication.model.Authorities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.hateoas.EntityModel;

import java.net.URI;
import java.util.List;

@Slf4j
@Configuration
@EnableConfigurationProperties(DatabaseApi.class)
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{


    private UriComponentsBuilder builder;
    private DatabaseApi databaseApi;
    private RestTemplate restTemplate;
    private final String databaseHostUrl;

    public SpringSecurityConfigurationBasicAuth(RestTemplateBuilder restTemplateBuilder, DatabaseApi databaseApi,
                                                @Value("${service.database.host}") final String databaseHostUrl){
        this.databaseApi = databaseApi;
        this.restTemplate = restTemplateBuilder.build();
        this.databaseHostUrl = databaseHostUrl;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try {
                    builder = UriComponentsBuilder
                            .fromUriString(databaseHostUrl + databaseApi.getFindByUsernameUri())
                            .queryParam("username", username);
                    log.info("Querying: {}", builder.toUriString());
                    ResponseEntity<EntityModel<microservices.book.multiplication.model.User>> responseEntity1 =
                            restTemplate.exchange(
                                    RequestEntity.get(URI.create(builder.toUriString()))

                                            .build()
                                    , new ParameterizedTypeReference<EntityModel<microservices.book.multiplication.model.User>>() {
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
                        EntityModel<microservices.book.multiplication.model.User> resource1 = responseEntity1.getBody();
                        microservices.book.multiplication.model.User user = resource1.getContent();

                        EntityModel<List<Authorities>> resource2 = responseEntity2.getBody();
                        String[] authorities = resource2.getContent().stream().map(a -> a.getAuthority()).toArray(String[]::new);

                        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

                        return User
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
        }).and().eraseCredentials(false);



    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin().and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .antMatchers("/","/**").authenticated()
                .antMatchers("/","/**").hasAnyAuthority("USER")
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login")
                .and()
                .httpBasic();
    }
}


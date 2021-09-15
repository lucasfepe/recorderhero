package microservices.book.library.configuration;


import lombok.extern.slf4j.Slf4j;
import microservices.book.library.security.KeycloakRoleConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Slf4j
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth
        extends WebSecurityConfigurerAdapter
{




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.cors().and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/users/status/check")
                //.hasAuthority("SCOPE_profile")
//                .hasRole("developer")
                //.hasAnyAuthority("ROLE_developer")
                //.hasAnyRole("devleoper","user")
//                .antMatchers("/archive/**")
//                .access("hasIpAddress(\"127.0.0.1\") or hasIpAddress(\"::1\")")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }


}


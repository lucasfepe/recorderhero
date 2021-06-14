package microservices.book.multiplication.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "admin")
public class AdminLogin {

    private String username;
    private String password;

}
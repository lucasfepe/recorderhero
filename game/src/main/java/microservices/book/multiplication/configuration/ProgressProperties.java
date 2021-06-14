package microservices.book.multiplication.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "library.progress")
public class ProgressProperties {
    private String hasUnfinishedCourses;
    private String username;
    private String password;
}
package microservices.book.multiplication.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "library.api")
public class LibraryApi {

    private String postSession;
    private String putUserCourse;

}
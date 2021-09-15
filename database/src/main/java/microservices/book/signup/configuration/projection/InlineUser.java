package microservices.book.signup.configuration.projection;


import microservices.book.signup.model.Session;
import microservices.book.signup.user.User;
import microservices.book.signup.util.Clef;
import microservices.book.signup.util.Key;
import microservices.book.signup.util.Mode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineUser", types = { User.class })
public interface InlineUser {
    @Value("#{target.id}")
    String getId();
    String getUsername();




}

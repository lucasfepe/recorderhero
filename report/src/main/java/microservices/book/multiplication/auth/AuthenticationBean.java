package microservices.book.multiplication.auth;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class AuthenticationBean {

    private String message;
    private List roles;


    public AuthenticationBean(String message, List roles) {
        this.message = message;
        this.roles = roles;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }

}

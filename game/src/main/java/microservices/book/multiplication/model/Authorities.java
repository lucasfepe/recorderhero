package microservices.book.multiplication.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authorities {

    private int id;

    private String username;
    private String authority;

    public Authorities(String username, String authority) {
        super();
        this.username = username;
        this.authority = authority;
    }




}


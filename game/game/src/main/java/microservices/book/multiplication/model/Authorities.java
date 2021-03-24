package microservices.book.multiplication.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authorities {

    @Id
    @Column(length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String username;
    @Column(length = 50)
    private String authority;

    public Authorities(String username, String authority) {
        super();
        this.username = username;
        this.authority = authority;
    }




}


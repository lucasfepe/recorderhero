package microservices.book.signup.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AuthoritiesRepo extends JpaRepository<Authorities, Integer>{
    public  List<Authorities> findByUsernameIgnoreCase(@Param("username") String username);
}

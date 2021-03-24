package microservices.book.signup.data;

import microservices.book.signup.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepo extends JpaRepository<Part, Integer> {

    Part findByPart(int part);


}


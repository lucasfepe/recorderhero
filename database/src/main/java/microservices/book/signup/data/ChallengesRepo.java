package microservices.book.signup.data;

import microservices.book.signup.model.Challenges;
import microservices.book.signup.model.NoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengesRepo extends JpaRepository<Challenges, Integer> {

    List<Challenges> findByChallengeNumber(int challengeNumber);
    Challenges findByCode(String code);


}

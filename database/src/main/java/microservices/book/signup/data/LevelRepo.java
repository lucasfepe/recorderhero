package microservices.book.signup.data;

import microservices.book.signup.model.Level;
import microservices.book.signup.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LevelRepo extends JpaRepository<Level, Integer> {

    Level findByPartAndLevel(Part part, int level);
    List<Level> findByLevelGreaterThanAndPartOrderByLevelAsc(int level, Part part);
    Level findTopByLevelGreaterThanAndPartOrderByLevelAsc(int level, Part part);
    List<Level> findByPartOrderByLevelAsc(Part part);
    @Query(value = "select * from level where part_part = :part and points = (select max(points) from level where part_part = :part) order by level ASC;",

            nativeQuery = true)
    List<Level> reviewLevels(@Param("part") int part);


}
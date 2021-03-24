package microservices.book.signup.data;

import		 java.util.List;

import microservices.book.signup.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface CoursesRepo extends CrudRepository<Courses, Integer> {

	Courses findByCode(String code);

	List<Courses> findAllByOrderByCode();

}

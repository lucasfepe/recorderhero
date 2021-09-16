package microservices.book.signup.data;

import		 java.util.List;

import microservices.book.signup.configuration.projection.InlineCourses;
import microservices.book.signup.configuration.projection.InlineUserCourses;
import microservices.book.signup.model.Courses;
import microservices.book.signup.util.Clef;
import microservices.book.signup.util.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = InlineCourses.class)
public interface CoursesRepo extends CrudRepository<Courses, String> {

	Courses findByCode(String code);
	Courses findByInstrumentAndClefAndCodeEndsWith(Mode instrument, Clef clef, String code);
	List<Courses> findAllByOrderByCode();

}

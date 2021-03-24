package microservices.book.multiplication.library;

public interface UserCoursesService {

    String getAllUserCoursesByUsername(String username, String principalUsername, String password);
}

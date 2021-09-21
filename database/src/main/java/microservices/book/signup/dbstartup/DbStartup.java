package microservices.book.signup.dbstartup;


import lombok.RequiredArgsConstructor;
import microservices.book.signup.data.CoursesRepo;
import microservices.book.signup.data.LevelRepo;
import microservices.book.signup.data.PartRepo;
import microservices.book.signup.data.UserCoursesRepo;
import microservices.book.signup.model.UserCourses;
import microservices.book.signup.user.Authorities;
import microservices.book.signup.user.AuthoritiesRepo;
import microservices.book.signup.user.User;
import microservices.book.signup.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DbStartup {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final AuthoritiesRepo authoritiesRepo;
    private final CoursesRepo coursesRepo;
    private final LevelRepo levelRepo;
    private final PartRepo partRepo;
    private final UserCoursesRepo userCoursesRepo;

    @PostConstruct
    private void init(){

        //guest user

        User u = new User("ma");
        userRepo.save(u);
        authoritiesRepo.save(new Authorities("ma","USER"));

        UserCourses uc = new UserCourses();
        uc.setUser(u);
        uc.setCourse(coursesRepo.findByCode("SRTrHI"));
        uc.setLevel(levelRepo.findByPartAndLevel(partRepo.findByPart(1), 1));
        uc.setComplete(false);
        userCoursesRepo.save(uc);




    }
}

package microservices.book.signup.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.signup.data.CoursesRepo;
import microservices.book.signup.data.LevelRepo;
import microservices.book.signup.data.PartRepo;
import microservices.book.signup.data.UserCoursesRepo;
import microservices.book.signup.model.UserCourses;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthoritiesRepo authoritiesRepo;
    private final CoursesRepo coursesRepo;
    private final LevelRepo levelRepo;
    private final PartRepo partRepo;
    private final UserCoursesRepo userCoursesRepo;

    @Override
    public void signup(User user) {

       if(!StreamSupport.stream(userRepository.findAll().spliterator(), false)
               .map(User::getUsername)
               .anyMatch(u -> u.equals(user.getUsername()))){
           user.setEnabled(true);

           userRepository.save(user);

           log.info("User {} saved in database", user.getUsername());

           authoritiesRepo.save(new Authorities(user.getUsername(), "USER"));

           UserCourses uc = new UserCourses();
           uc.setUser(user);
           uc.setCourse(coursesRepo.findByCode("SRTrHI"));
           uc.setLevel(levelRepo.findByPartAndLevel(partRepo.findByPart(1), 1));
           uc.setComplete(false);
           userCoursesRepo.save(uc);

       }else{
           log.info("User {} already exists, could not save", user.getUsername());
           throw new SecurityException("User already exists, could not create user");
       };


    }
}


import { instance } from './AuthenticationService'

class LibraryApiClient{

    static SERVER_URL = 'http://localhost:8181';
    static GET_UNFINISHED_COURSES = '/progress/hasunfinishedcourse?username=';
    static GET_USER_COURSES = '/progress/courses?username=';
    static GET_USER_SESSIONS = '/sessions/';


    

    

    static hasUnfinishedCourse(username){
        var a = instance;
        return instance.get(LibraryApiClient.SERVER_URL + LibraryApiClient.GET_UNFINISHED_COURSES + username);
    }

    static getUserCourses(username){
        return instance.get(LibraryApiClient.SERVER_URL + LibraryApiClient.GET_USER_COURSES + username);
    }
    static getUserSessions(username){
        return instance.get('http://localhost:8080/sessions/search/findByUserUsername?username='  + username );
    }

   

   

    

}

export default  LibraryApiClient;
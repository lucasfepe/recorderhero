
import { instance } from './AuthenticationService'

class LibraryApiClient{

    static SERVER_URL = 'http://localhost:8181';
    static GET_UNFINISHED_COURSES = '/progress/hasunfinishedcourse?username=';
    static GET_USER_COURSES = '/progress/courses?username=';


    

    

    static hasUnfinishedCourse(username){
        var a = instance;
        return instance.get(LibraryApiClient.SERVER_URL + LibraryApiClient.GET_UNFINISHED_COURSES + username);
    }

    static getUserCourses(username){
        return instance.get(LibraryApiClient.SERVER_URL + LibraryApiClient.GET_USER_COURSES + username);
    }

   

    

}

export default  LibraryApiClient;
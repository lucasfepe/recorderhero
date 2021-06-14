

import HttpService from "../ServicesNew/HttpService";

class LibraryApiClient{

    static SERVER_URL = 'http://localhost:8082';
    static GET_UNFINISHED_COURSES = '/progress/hasunfinishedcourse?username=';
    static GET_USER_COURSES = '/progress/courses?username=';


    

    

    static hasUnfinishedCourse(username){
        let axios = HttpService.getAxiosClient;
        return axios.get(LibraryApiClient.SERVER_URL + LibraryApiClient.GET_UNFINISHED_COURSES + username);
    }

    static getUserCourses(username){
        return HttpService.getAxiosClient.get(LibraryApiClient.SERVER_URL + LibraryApiClient.GET_USER_COURSES + username);
    }

   

    

}

export default  LibraryApiClient;
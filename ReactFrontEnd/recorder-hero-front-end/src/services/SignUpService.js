import axios from 'axios'
import { instance } from './AuthenticationService'

const API_URL = 'http://localhost:8080'


class SignUpService {

    static GET_USER_SESSIONS = '/sessions/search/findByUserUsername?username=';

    executeSignUp(username, password) {
        return axios.post(`${API_URL}/signup`,{
            "username":username,
            "password":password
        }
          )
            

    }

     getUserSessions(username){
        return instance.get(`${API_URL}` + SignUpService.GET_USER_SESSIONS + username );
    }

 

    
}

export default new SignUpService()
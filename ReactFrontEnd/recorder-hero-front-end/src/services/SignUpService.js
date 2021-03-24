import axios from 'axios'

const API_URL = 'http://localhost:8080'


class SignUpService {

    executeSignUp(username, password) {
        return axios.post(`${API_URL}/signup`,{
            "username":username,
            "password":password
        }
          )
            
    }

 

    
}

export default new SignUpService()
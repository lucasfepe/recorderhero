import axios from 'axios'

const API_URL = 'https://localhost:8099'


class SignUpService {

    executeSignUp(username, password, firstName, lastName) {
        return axios.post(`${API_URL}/users`,{
            "email":username,
            "encryptedPassword":password,
            "firstName":firstName,
            "lastName":lastName,
            "emailVerificationToken":'',
            "emailVerificationStatus":false

        }
          )
            
    }

 

    
}

export default new SignUpService()
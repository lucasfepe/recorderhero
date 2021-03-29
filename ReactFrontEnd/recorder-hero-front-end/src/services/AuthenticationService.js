// import axios from 'axios'
import axios from 'axios'
import LibraryApiClient from './LibraryApiClient'


const API_URL = 'http://localhost:8181'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
export const USER_ROLES_SESSION_ATTRIBUTE_NAME = 'authenticatedUserRoles'
export const USER_TOKEN = 'user_token'

export const instance = axios.create({
    baseURL: '',
    timeout: 1000,
    headers: {}
  });

var inter





class AuthenticationService {

      
    
    executeJwtAuthenticationService(username, password) {
        console.log(username);
        
        return axios.post(`${API_URL}/authenticate`, {
            username,
            password
        })
    }

    // executeBasicAuthenticationService(username, password) {
    //     this.createBasicAuthToken(username,password);
    //     return instance.get(`${API_URL}/basicauth`,
    //         { headers: { authorization: sessionStorage.getItem(USER_TOKEN) } })
    // }

   

    // createBasicAuthToken(username, password) {
    //     sessionStorage.setItem(USER_TOKEN, 'Basic ' + window.btoa(username + ":" + password))
        
    // }

//     registerSuccessfulLogin(username, password, roles) {
//         //let basicAuthHeader = 'Basic ' +  window.btoa(username + ":" + password)
//         //console.log('registerSuccessfulLogin');
//         sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
        
//         sessionStorage.setItem(USER_ROLES_SESSION_ATTRIBUTE_NAME, JSON.stringify(roles));
        
//         // this.setupAxiosInterceptors(sessionStorage.getItem(USER_TOKEN));


// }
registerSuccessfulLoginForJwt(username, token) {
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)

    sessionStorage.setItem(USER_TOKEN, this.createJWTToken(token))
    this.setupAxiosInterceptors()
    
}

createJWTToken(token) {
    return 'Bearer ' + token
}

  


    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem(USER_TOKEN);
        
        sessionStorage.removeItem(USER_ROLES_SESSION_ATTRIBUTE_NAME);
        instance.interceptors.request.eject(inter);
        
        

    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        if (user === null) return false;
        return true;
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }

    getLoggedInUserRoles() {
        let user = sessionStorage.getItem(USER_ROLES_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }

    


    setupAxiosInterceptors() {
        
        
    inter =  instance.interceptors.request.use(config => {
    const currentUser = this.isUserLoggedIn() //You can get the user directly from the cookie or session storage...
    if(currentUser) {
        config.headers.Authorization = sessionStorage.getItem(USER_TOKEN)
    }

return config
}, err => {
   console.log(err)
return Promise.reject(err)
})
    }

   
   

     

    
}

export default new AuthenticationService()
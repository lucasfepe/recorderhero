import axios from "axios";
import UserService from "./UserService";
import axiosCookieJarSupport from 'axios-cookiejar-support'
import https from 'https'



import tough from 'tough-cookie'

const HttpMethods = {
  GET: 'GET',
  POST: 'POST',
  DELETE: 'DELETE',
};

axiosCookieJarSupport(axios);
const cookieJar = new tough.CookieJar();

const _axios = axios.create({
  withCredentials: true,
  jar: cookieJar,

  httpsAgent: new https.Agent({ rejectUnauthorized: false, requestCert: true, keepAlive: true})});




const configure = () => {
  
 

  _axios.interceptors.request.use((config) => {
    if (UserService.isLoggedIn()) {
      const cb = () => {
        
        config.headers.Authorization = `Bearer ${UserService.getToken()}`;
        return Promise.resolve(config);
      };
      return UserService.updateToken(cb);
    }
  });
};

const getAxiosClient = () => _axios;

const HttpService = {
  HttpMethods,
  configure,
  getAxiosClient,
};

export default HttpService;

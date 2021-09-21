import { SUCCESS_SUFFIX } from "redux-axios-middleware";
import HttpService from "../ServicesNew/HttpService";



const GET_IS_FIRST_TIME_USER = 'GET_IS_FIRST_TIME_USER';

const isFirstTimeUserReducer = (state = [], action) => {
  switch (action.type) {
    

    case GET_IS_FIRST_TIME_USER + SUCCESS_SUFFIX:
        return action.payload.data;

   
    default:
      return state;
  }
};

export default isFirstTimeUserReducer;

export const isFirstTimeUser = (username) => ({
  type: GET_IS_FIRST_TIME_USER,
  payload: {
    request: {
      url: '/library/isFirstTimeUser/' + username,
      
      method: HttpService.HttpMethods.GET   
    },
  },
});



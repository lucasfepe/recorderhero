import { SUCCESS_SUFFIX, FAILURE_SUFFIX } from "redux-axios-middleware";
import HttpService from "../ServicesNew/HttpService";



const LIST_COURSES = 'LIST_COURSES';
const POST_COURSES = 'POST_COURSES';
const POST_NEW_USER = 'POST_NEW_USER';

const coursesReducer = (state = [], action) => {
  switch (action.type) {
    

    case LIST_COURSES + SUCCESS_SUFFIX:
        return action.payload.data;

    case POST_COURSES:
      return "ji";

    case POST_NEW_USER + SUCCESS_SUFFIX:
      return "";


      case POST_NEW_USER + FAILURE_SUFFIX:
        return "";

        case POST_NEW_USER:
          return "";

    default:
      return state;
  }
};

export default coursesReducer;

export const allCourses = (username) => ({
  type: LIST_COURSES,
  payload: {
    request: {
      url: '/progress/courses?username=' + username,
      
    },
  },
});
export const allpoop = (hi) => ({
  type: POST_COURSES,
  payload: {
    request: {
      url: '/progress/courses',
      method: HttpService.HttpMethods.POST,
      data: hi
    },
  },
});

export const newUser = (username) => ({
  type: POST_NEW_USER,
  payload: {
    request: {
      url: '/library/new_user',
      method: HttpService.HttpMethods.POST,
      data: {"username":username}
    },
  },
});




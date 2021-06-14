import { SUCCESS_SUFFIX } from "redux-axios-middleware";
import HttpService from "../ServicesNew/HttpService";



const LIST_COURSES = 'LIST_COURSES';
const POST_COURSES = 'POST_COURSES';

const coursesReducer = (state = [], action) => {
  switch (action.type) {
    

    case LIST_COURSES + SUCCESS_SUFFIX:
        return action.payload.data;

    case POST_COURSES:
      return "ji";

    default:
      return state;
  }
};

export default coursesReducer;

export const allCourses = () => ({
  type: LIST_COURSES,
  payload: {
    request: {
      url: '/progress/courses?username=ma',
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




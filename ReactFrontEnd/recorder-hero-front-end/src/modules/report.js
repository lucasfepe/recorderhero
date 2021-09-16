import { SUCCESS_SUFFIX } from "redux-axios-middleware";
import HttpService from "../ServicesNew/HttpService";



const GET_REPORT = 'GET_REPORT';
const CLEAR_REPORT = 'CLEAR_REPORT';


const reportReducer = (state = [], action) => {
  switch (action.type) {
    

    case GET_REPORT + SUCCESS_SUFFIX:
        return action.payload.data;

    case CLEAR_REPORT:
        return "";

    default:
      return state;
  }
};

export default reportReducer;

export const getReport = (sessionID) => ({
  type: GET_REPORT,
  payload: {
    request: {
      url: '/report/run_summary/' + sessionID,
      method: HttpService.HttpMethods.GET   
    },
  },
});




import { SUCCESS_SUFFIX } from "redux-axios-middleware";
import HttpService from "../ServicesNew/HttpService";



const GET_HIGH_SCORES = 'GET_HIGH_SCORES';


const highScoresReducer = (state = [], action) => {
  switch (action.type) {
    

    case GET_HIGH_SCORES + SUCCESS_SUFFIX:
        return action.payload.data;

  

    default:
      return state;
  }
};

export default highScoresReducer;

export const getHighScores = (instrument, key, clef, range, octave) => ({
  type: GET_HIGH_SCORES,
  payload: {
    request: {
      url: '/sessions/highscores?instrument=' + instrument + "&key=" + key + "&clef=" + clef + "&range=" + range + "&octaveShift=" + octave,
      method: HttpService.HttpMethods.GET   
    },
  },
});





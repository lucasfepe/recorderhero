import { SUCCESS_SUFFIX } from "redux-axios-middleware";
import HttpService from "../ServicesNew/HttpService";



const GENERATE_ACCIDENTALS = 'GENERATE_ACCIDENTALS';
const END_GAME = 'END_GAME';

const accidentalsReducer = (state = [], action) => {
  switch (action.type) {
    

    case GENERATE_ACCIDENTALS + SUCCESS_SUFFIX:
        return action.payload.data;

    default:
      return state;
  }
};

export default accidentalsReducer;

export const generateAccidentals = (userCourse) => ({
  type: GENERATE_ACCIDENTALS,
  payload: {
    request: {
      url: '/game/enumerate_accidentals',
      data: userCourse,
      method: HttpService.HttpMethods.POST   
    },
  },
});

export const endGame = (isChallenge, peeked_array, note_shown_array, 
                      note_input_array, time_array_start, time_array_end, sessionId) => ({
  type: END_GAME,
  payload: {
    request: {
      url: '/game/end_game',
      data: {
                
        "isChallenge": isChallenge,
        "peeked_array": peeked_array,
        "note_shown_array": note_shown_array,
        "note_input_array": note_input_array,
        "time_array_start": time_array_start,
        "time_array_end": time_array_end
        ,    "sessionId": sessionId
      
    },
      method: HttpService.HttpMethods.POST

      
    },
  },
});



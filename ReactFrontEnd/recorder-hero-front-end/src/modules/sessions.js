import { SUCCESS_SUFFIX } from "redux-axios-middleware";




const LIST_SESSIONS = 'LIST_SESSIONS';

const sessionReducer = (state = [], action) => {
  switch (action.type) {
    

    case LIST_SESSIONS + SUCCESS_SUFFIX:
        return action.payload.data;

    

    default:
      return state;
  }
};

export default sessionReducer;

export const allSessions = (username) => ({
  type: LIST_SESSIONS,
  payload: {
    request: {
      url: '/sessions/getAll?username=' + username,
    },
  },
});




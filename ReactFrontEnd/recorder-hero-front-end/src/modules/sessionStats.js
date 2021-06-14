



const SESSION_STATS = 'SESSION_STATS';
const INCREASE_STAT = 'INCREASE_STAT';

const sessionStatsReducer = (state = [], action) => {
  
  switch (action.type) {
    case SESSION_STATS:
      return action.payload.newa;

    case INCREASE_STAT:
      if(state.length > 0){  
    var arr = []
      state.map(a => arr.push([a[0],a[1]]))
      arr[action.payload][1] += 1;
      return  arr;
      }else{
        return state
      }

    default:
      return state;
  }
};

export default sessionStatsReducer;

export const getSessionStats = (listOfEntries) => {
  
  var newa = [];
  if(listOfEntries != null){
  
  newa = listOfEntries.slice(0).reverse().map(obj => [obj, 0]);
  }
  return ({
  type: SESSION_STATS,
  payload:{
    newa
  }
});}






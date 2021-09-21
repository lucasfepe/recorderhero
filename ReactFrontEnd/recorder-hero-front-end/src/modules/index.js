import { combineReducers } from "redux";
import accidentals from './accidentals'
import courses from "./courses"
import report from './report'
import sessions from './sessions'
import sessionStats from './sessionStats'
import highScores from './highScores'
import  isFirstTimeUser  from "./isFirstTimeUser";

export default combineReducers({
   courses, accidentals, report, sessions, sessionStats, highScores, isFirstTimeUser
});

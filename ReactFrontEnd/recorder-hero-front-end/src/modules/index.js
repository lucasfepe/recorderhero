import { combineReducers } from "redux";
import accidentals from './accidentals'
import courses from "./courses"
import report from './report'
import sessions from './sessions'
import sessionStats from './sessionStats'

export default combineReducers({
   courses, accidentals, report, sessions, sessionStats
});

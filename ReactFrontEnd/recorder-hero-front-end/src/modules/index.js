import { combineReducers } from "redux";
import accidentals from './Accidentals'
import courses from "./Courses"
import report from './Report'
import sessions from './Sessions'
import sessionStats from './SessionStats'

export default combineReducers({
   courses, accidentals, report, sessions, sessionStats
});

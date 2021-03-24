
import { instance } from './AuthenticationService'

class ReportApiClient{

    static SERVER_URL = 'http://localhost:8383';
    static GET_PAST_NOTES = '/report/run_summary/';
    


    

    

    static getPastNotes(sessionID){
        
        return instance.get(ReportApiClient.SERVER_URL + ReportApiClient.GET_PAST_NOTES + sessionID,{timeout: 5000});
    }



   

    

}

export default  ReportApiClient;
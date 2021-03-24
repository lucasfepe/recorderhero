import { instance } from './AuthenticationService'
import AuthenticationService from './AuthenticationService'

class GameApiClient{

    static SERVER_URL = 'http://localhost:8282';
    static POST_ENUMERATE_ACCIDENTALS = '/game/enumerate_accidentals';
    static POST_END_GAME = '/game/end_game'

    static enumerateAccidentals(userCourse){
        return instance.post(GameApiClient.SERVER_URL + GameApiClient.POST_ENUMERATE_ACCIDENTALS,
            {
                userCourse
            });
    }

    static endGame(gameData){
        var a = instance;
        var b = AuthenticationService.isUserLoggedIn();
        return instance.post(GameApiClient.SERVER_URL + GameApiClient.POST_END_GAME,
            {
                
                  "isChallenge": gameData.isChallenge,
                  "peeked_array": gameData.peeked_array,
                  "note_shown_array": gameData.note_shown_array,
                  "note_input_array": gameData.note_input_array,
                  "time_array_start": gameData.time_array_start,
                  "time_array_end": gameData.time_array_end,
                  "sessionId": gameData.sessionId
                
              },{timeout: 5000});
    }

    
}

export default GameApiClient;
import * as React from "react";
import AuthenticationService from "../keycloak";
import MenuComponent from './MenuComponent';


class AchievementsRankingsComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
           
        }
        
      
        this.componentDidMount = this.componentDidMount.bind(this);
    }

    componentDidMount(){
        this.setState({
           
    })
        
       
    }

   

   

    render() {
        return (
            <div className="display-column">
                <MenuComponent />
                AchievementsRankingsComponent

            </div>
        );
    }
}

export default AchievementsRankingsComponent;

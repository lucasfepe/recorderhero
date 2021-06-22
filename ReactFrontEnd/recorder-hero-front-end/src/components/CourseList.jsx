import * as React from "react";

import MenuComponent from './MenuComponent';


class AchievementsComponent extends React.Component {

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
                Achievements

            </div>
        );
    }
}

export default AchievementsComponent;

import * as React from "react";

import MenuComponent from './MenuComponent';


class RewardsComponent extends React.Component {

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
                Rewards

            </div>
        );
    }
}

export default RewardsComponent;

import * as React from "react";
import AuthenticationService from "../services/AuthenticationService";
import MenuComponent from './MenuComponent';


class ShopComponent extends React.Component {

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
                ShopComponent

            </div>
        );
    }
}

export default ShopComponent;

import * as React from "react";
import AuthenticationService from "../services/AuthenticationService";
import MenuComponent from './MenuComponent';


class ReportGeneratorComponent extends React.Component {

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
                ReportGeneratorComponent

            </div>
        );
    }
}

export default ReportGeneratorComponent;

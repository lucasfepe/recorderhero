import * as React from "react";
import AuthenticationService from "../keycloak";
import MenuComponent from './MenuComponent';


class AdminComponent extends React.Component {

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
                Admin Page
                {AuthenticationService.getLoggedInUserRoles().includes("ADMIN") && <span>ADMIN CONTENT ONLY</span>}

                {!AuthenticationService.getLoggedInUserRoles().includes("ADMIN") && <span>If you are not an admin you shouldn't be here</span>}

            </div>
        );
    }
}

export default AdminComponent;

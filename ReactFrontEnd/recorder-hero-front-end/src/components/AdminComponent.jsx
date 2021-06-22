import * as React from "react";
import AuthenticationService from "../Keycloak";
import MenuComponent from './MenuComponent';
import UserService from '../ServicesNew/UserService';


const AdminComponent = () => {

 
        return (
            <div className="display-column">
                <MenuComponent />
                Admin Page
                {UserService.hasRole(["developer"]) ? <span>ADMIN CONTENT ONLY</span> : <span>You are not an admin and shouldn't be here</span>}

                

            </div>
        );
    }


export default AdminComponent;

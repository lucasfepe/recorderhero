import React, { Component } from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../services/AuthenticationService';

class AuthenticatedRoute extends Component {

    componentWillMount(){
        AuthenticationService.setupAxiosInterceptors();
        
    }

    render() {
        if (AuthenticationService.isUserLoggedIn()) {
            return <Route {...this.props} />
        } else {
            return <Redirect to="/login" />
        }

    }
}

export default AuthenticatedRoute
import React from 'react';
import { Route } from 'react-router-dom';
import UserService from '../ServicesNew/UserService';

function PrivateRoute({ component: Component }) {
    return  <Route  render={props => (
        UserService.isLoggedIn() ? <Component {...props} /> : UserService.doLogin()
    )} />
}
export default PrivateRoute
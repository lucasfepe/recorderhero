import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import UserService from "./ServicesNew/UserService";
import HttpService from './ServicesNew/HttpService';

import * as serviceWorker from './serviceWorker';






const renderApp = () => ReactDOM.render(
  <React.StrictMode>
    
    <App />
    
  </React.StrictMode>,
  document.getElementById('root')
);

UserService.initKeycloak(renderApp);
HttpService.configure();
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
// serviceWorker.unregister();
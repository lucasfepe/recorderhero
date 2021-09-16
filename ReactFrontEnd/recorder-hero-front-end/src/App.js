import './App.css';
import RecorderHeroApp from './components/RecorderHeroApp';
import React from 'react';

import StoreService from "./ServicesNew/StoreService";
import { Provider } from "react-redux";
import './App.css';


const store = StoreService.setup();

// Wrap everything inside KeycloakProvider
const App = () => (

  <Provider store={store}><RecorderHeroApp/></Provider>
)
  

export default App;


 

 

import { applyMiddleware, compose, createStore } from "redux";
import axiosMiddleware from "redux-axios-middleware";
import logger from "redux-logger";
import thunk from "redux-thunk";
import rootReducer from "../modules";
import HttpService from "./HttpService";

const setup = () => {
  const enhancers = [ window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()];
  const middleware = [
    thunk,
    axiosMiddleware(HttpService.getAxiosClient())
  ];

  if (process.env.NODE_ENV === 'development') {
    enhancers.push(applyMiddleware(logger));
  }

  const composedEnhancers = compose(applyMiddleware(...middleware), ...enhancers);

  return createStore(rootReducer, composedEnhancers);
};

const StoreService = {
  setup,
};

export default StoreService;

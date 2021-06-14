import React from 'react';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginComponent from './LoginComponent';
import CoursesComponent from './CoursesComponent';
import StatsSummaryComponent from './StatsSummaryComponent';
import RewardsComponent from './RewardsComponent';
import ReportGeneratorComponent from './ReportGeneratorComponent';
import AchievementsRankingsComponent from './AchievementsRankingsComponent';
import ChallengeRankingsComponent from './ChallengeRankingsComponent';
import AchievementsComponent from './AchievementsComponent';
import CustomRunComponent from './CustomRunComponent';
import SessionComponent from './SessionComponent';
import RunSummaryComponent from './RunSummaryComponent';
import ShopComponent from './ShopComponent';
import AdminComponent from './AdminComponent';


import HomeComponent from './HomeComponent';
import PrivateRoute from '../utilities/PrivateRoute';








 const RecorderHeroApp = () => {

    
    
        return (
          
    <Router>
      <Switch>
      <Route exact path="/" component={LoginComponent} />
                            <PrivateRoute path="/home" exact component={HomeComponent} />
                            <PrivateRoute path="/courses" exact component={CoursesComponent} />
                            <PrivateRoute path="/customrun" exact component={CustomRunComponent} />
                            <PrivateRoute path="/achievements" exact component={AchievementsComponent} />
                            <PrivateRoute path="/stats" exact component={StatsSummaryComponent} />
                            <PrivateRoute path="/rewards" exact component={RewardsComponent} />
                            <PrivateRoute path="/session" exact component={SessionComponent} />
                            <PrivateRoute path="/runsummary" exact component={RunSummaryComponent} />
                            <PrivateRoute path="/report" exact component={ReportGeneratorComponent} />
                            <PrivateRoute path="/rankings" exact component={AchievementsRankingsComponent} />
                            <PrivateRoute path="/challengerankings" exact component={ChallengeRankingsComponent} />
                            <PrivateRoute path="/shop" exact component={ShopComponent} />
                            <PrivateRoute path="/admin" exact component={AdminComponent} />
                            </Switch>
    </Router>
    
  
           
        )
    }


export default RecorderHeroApp
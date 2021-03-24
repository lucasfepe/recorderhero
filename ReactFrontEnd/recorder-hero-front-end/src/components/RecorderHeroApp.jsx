import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import LoginComponent from './LoginComponent';
import LogoutComponent from './LogoutComponent';
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

import AuthenticatedRoute from './AuthenticatedRoute';
import HomeComponent from './HomeComponent';

class RecorderHeroApp extends Component {

    render() {
        return (
            <>
                <Router>
                    <>
                        
                        <Switch>
                            <Route path="/" exact component={LoginComponent} />
                            <Route path="/login" exact component={LoginComponent} />
                            <AuthenticatedRoute path="/logout" exact component={LogoutComponent} />
                            <AuthenticatedRoute path="/home" exact component={HomeComponent} />
                            <AuthenticatedRoute path="/courses" exact component={CoursesComponent} />
                            <AuthenticatedRoute path="/customrun" exact component={CustomRunComponent} />
                            <AuthenticatedRoute path="/achievements" exact component={AchievementsComponent} />
                            <AuthenticatedRoute path="/stats" exact component={StatsSummaryComponent} />
                            <AuthenticatedRoute path="/rewards" exact component={RewardsComponent} />
                            <AuthenticatedRoute path="/session" exact component={SessionComponent} />
                            <AuthenticatedRoute path="/runsummary" exact component={RunSummaryComponent} />
                            <AuthenticatedRoute path="/report" exact component={ReportGeneratorComponent} />
                            <AuthenticatedRoute path="/rankings" exact component={AchievementsRankingsComponent} />
                            <AuthenticatedRoute path="/challengerankings" exact component={ChallengeRankingsComponent} />
                            <AuthenticatedRoute path="/shop" exact component={ShopComponent} />
                            <AuthenticatedRoute path="/admin" exact component={AdminComponent} />
                        </Switch>
                    </>
                </Router>
            </>
        )
    }
}

export default RecorderHeroApp
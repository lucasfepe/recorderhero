import React, { Fragment } from 'react'
import AuthenticationService from '../services/AuthenticationService';
import MenuComponent from './MenuComponent';
import LibraryApiClient from '../services/LibraryApiClient';
import CourseStartComponent from "./CourseStartComponent";


class CoursesComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            anyCurrentCourses:false,
            user: AuthenticationService.getLoggedInUserName(),
            userCourses: '',
            logedinuser: '',
            logedinpass: '',
            logedinroles: [],
            authorizationError: ''
        }


        this.componentDidMount = this.componentDidMount.bind(this);
    }

    componentDidMount() {
        this.setState({
            logedinuser: AuthenticationService.getLoggedInUserName(),
            logedinroles: AuthenticationService.getLoggedInUserRoles()
    })
    LibraryApiClient.hasUnfinishedCourse(this.state.user).then(
            res => {
                this.setState({anyCurrentCourses: res.data})
            }
        ).catch(
            () => {console.log("error Role:'USER' required. Your roles:" + this.state.logedinroles)
            
            
            this.setState({authorizationError: "error Role: 'USER' required. Your roles:" + JSON.parse(this.state.logedinroles).map(x => " " + x.authority)})}
        )

        LibraryApiClient.getUserCourses(this.state.user).then(
            res => {
                this.setState({userCourses: res})
            }
        )
        


    }





    render() {
        return (
            <div className="display-column">
                <MenuComponent />
                <div>Logged in User</div>
                <div>{this.state.logedinuser}</div>
             
                <div>{this.state.logedinroles}</div>
                
                <div className="container  px-lg-5 bg-light border">
                    <br />
                    <div className="text-center">
                        <img src="images/sopranoRecorder2.jpg" alt="..." className="img-thumbnail img-square" />
                    </div>
                    <h3 className="display-4 text-center my-3 ">Ongoing Courses</h3>
                    <hr />
                    <div className="courses">
                        {this.state.authorizationError ? <div>{this.state.authorizationError}</div> : <Fragment>{!this.state.anyCurrentCourses &&
                            <div className="text-center">
                                <em className="text-muted">No current courses?</em>
                                <br/> 
                                <a href="shop">
                                    <button type="button" className="btn m-2 px-4 py-2  btn-light btn-link">
                                        <span className="lead">Get new courses</span>
                                    </button>
                                </a>
                            </div>
                        }</Fragment>}
                        {this.state.anyCurrentCourses &&
                            <div className="text-center">
                                {this.state.userCourses.data.filter(row =>  row.complete === false).map(row => 
                                    
                                    <CourseStartComponent user_course={row}/>
                                    
                                    )}
                            
                           
                        
                  
                                
                            </div>
                        }
                    </div>
                </div>
            </div>
        );
    }
}

export default CoursesComponent;

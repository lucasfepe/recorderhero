import { MDBBtn } from "mdbreact";
import {  withRouter } from 'react-router-dom';
import * as React from 'react';

class CourseStartComponent extends React.Component {


    constructor(props) {
        super(props)
       
        this.goClicked = this.goClicked.bind(this)
    }

    goClicked(event){
        this.props.user_course.challenge = event.target.value == "challenge" ? true:false;
        this.props.history.push({pathname:'/session',state: { detail: this.props.user_course }});
    }


    render() {
        return (
            <div key={this.props.user_course.course.clef} className="py-1 this.props.user_course justify-content-center rounded">

                <label htmlFor="inputEmail3" className=" col-form-label col-xs-7 col-lg-4 col-xl-4 col-sm-7 col-md-5 ">
                    <span  >{this.props.user_course.course.instrument.toLowerCase()} </span>
                      in <span >{this.props.user_course.course.clef} </span>
                        clef
                    Level {this.props.user_course.level.level}
                </label>
                <MDBBtn value="practice" gradient="aqua" onClick={this.goClicked}>Practice</MDBBtn>
                <MDBBtn value="challenge" gradient="aqua" onClick={this.goClicked}>Start</MDBBtn>
                
                

            </div>
        );
    }
}

export default withRouter(CourseStartComponent);




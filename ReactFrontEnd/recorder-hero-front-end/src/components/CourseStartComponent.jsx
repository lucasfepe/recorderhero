import { MDBBtn } from "mdbreact";
import {  withRouter } from 'react-router-dom';
import * as React from 'react';
import { useDispatch, useSelector, shallowEqual } from 'react-redux';





const CourseStartComponent = (props) => {

    const dispatch = useDispatch();
   
    

    const goClicked = (event) => {
        dispatch({
			type: 'CLEAR_REPORT'
		  })
<<<<<<< HEAD
          
=======
>>>>>>> 5a83b81a0b3d281c43b7f8650fea5473532f87a0
        props.user_course.challenge = event.target.value == "challenge" ? true:false;
        props.history.push({pathname:'/session',state: { detail: props.user_course }});
    }
 
    React.useEffect(() => {

        
    })


    
        return (
            <div key={props.user_course.course.clef} className="py-1 this.props.user_course justify-content-center rounded course-start-comp">

                <label htmlFor="inputEmail3" className=" col-form-label col-xs-7 col-lg-4 col-xl-4 col-sm-7 col-md-5 ">
                    <span  >{props.user_course.course.instrument.toLowerCase().split("_").map(c => c.charAt(0).toUpperCase() + c.slice(1) + " ")} </span>
                      in <span >{props.user_course.course.clef.charAt(0) + props.user_course.course.clef.slice(1).toLowerCase()} </span>
                        clef
                    Level {props.level}
                    
                    
                </label>
                <MDBBtn value="practice" gradient="aqua" onClick={goClicked}>Practice</MDBBtn>
                {props.level > 6 && <MDBBtn value="challenge" gradient="aqua" onClick={goClicked}>Start</MDBBtn>}
                
                

            </div>
        );
    }


export default withRouter(CourseStartComponent);




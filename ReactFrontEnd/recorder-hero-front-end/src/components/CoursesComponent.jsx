import React, {  useEffect } from 'react'
import MenuComponent from './MenuComponent';

import { useDispatch, useSelector } from "react-redux";
import { allCourses  } from "../modules/courses";
import CourseStartComponent from "./CourseStartComponent";
import UserService from '../ServicesNew/UserService'
import { MDBBtn } from "mdbreact";




// const selectNumCompletedTodos = createSelector(
//     state => state
    
//   )

const CoursesComponent = (props) => {
    const dispatch = useDispatch();
    
    const { courses } = useSelector((state) => state);
   
    useEffect(() => {

       dispatch(allCourses(UserService.getUsername())).then(res => console.log(res));
        

    }, [])
    
    
		
        

        return (
            <div className="display-column">
                <MenuComponent />
               <br/>
               <br/>
               
                <div className="container  px-lg-5 bg-light border">
                    <br />
                    <div className="text-center">
                        <img src="image/descant.jpg" alt="..." className="img-thumbnail img-square" />
                    </div>
                    <h3 className="display-4 text-center my-3 ">Ongoing Courses</h3>


                   

                    <hr />
                    <div className="courses">
                         {courses.filter(c => c.complete == false).length == 0 ?
                            <div className="text-center">
                                <em className="text-muted">No current courses?</em>
                                <br/> 
                                <a href="shop">
                                    <button type="button" className="btn m-2 px-4 py-2  btn-light btn-link">
                                        <span className="lead">Get new courses</span>
                                    </button>
                                </a>
                            </div>
                            :
                            <div>{courses.map(c => <CourseStartComponent user_course={c} level={c.level.level}/>
                            
                            )}
                            </div>
                        }  
                        
                    </div>
                    <h3 className="display-4 text-center my-3 ">Completed Courses</h3>


                   

                    <hr />
                    <div className="courses">
                        
                            <div className="py-1 this.props.user_course justify-content-center rounded course-start-comp">{courses.filter(c => c.complete == true).map(c => <><label htmlFor="inputEmail3" className=" col-form-label col-xs-7 col-lg-4 col-xl-4 col-sm-7 col-md-5 ">
                            <span  >{c.course.instrument.toLowerCase().split("_").map(d => d.charAt(0).toUpperCase() + d.slice(1) + " ") }{ c.course.code.substring(c.course.code.indexOf("I"))} </span>
                              in <span >{c.course.clef.charAt(0) + c.course.clef.slice(1).toLowerCase()} </span>
                                clef
                        </label>
                             {/* <MDBBtn value="practice" gradient="aqua" onClick={() => {props.history.push({pathname:'/listlevels',state: { c }})}}>See Levels</MDBBtn> */}
                             </>)
                            }
                            </div>
                        
                        
                    </div>
                </div>
            </div>
        );
    }


export default CoursesComponent;

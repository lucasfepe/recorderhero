import React , {useEffect} from "react";

import MenuComponent from './MenuComponent';
import CourseStartComponent from './CourseStartComponent';


const ListLevelsComponent = (props) => {
var userCourse = props.location.state.c;
var courseCode = userCourse.course.code;
var maxLevel = courseCode.includes("III") ? 36: courseCode.includes("II") ? 58 : 60;





   

        return (
            <div className="display-column">
                <MenuComponent />
               {[...Array(maxLevel)].map((a,i)=> <CourseStartComponent user_course={userCourse} level={i + 1} list="true" />
            
            
               
               
            )}
            
               
                

            </div>
        );
    
}

export default ListLevelsComponent;

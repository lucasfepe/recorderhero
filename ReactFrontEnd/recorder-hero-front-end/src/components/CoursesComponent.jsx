import React, {  useEffect } from 'react'
import MenuComponent from './MenuComponent';

import { useDispatch, useSelector } from "react-redux";
import { allCourses  } from "../modules/courses";
import CourseStartComponent from "./CourseStartComponent";




// const selectNumCompletedTodos = createSelector(
//     state => state
    
//   )

const CoursesComponent = () => {
    const dispatch = useDispatch();
    
    const { courses } = useSelector((state) => state);
    // const courses = useSelector(selectNumCompletedTodos)
   
    // const dothing = () => {
    //     HttpService.getAxiosClient().get('http://localhost:8082/progress/courses?username=ma', {
    //     //   headers: {
    //     //     'Authorization': `Bearer ${UserService.getToken()}`
    
            
    //     //   }
    //     })
    //   }

// const dothing = () => {
//      alert("doing it")
        // dispatch(allCourses(UserService.getUsername()))
//     }
// const csrftoken = Cookies.get('csrftoken') 
    useEffect(() => {
//         const cookieJar = new tough.CookieJar();
// const instance = axios.create();
// axiosCookieJarSupport(instance);
// instance.defaults.jar = new tough.CookieJar();
// instance
//   .post('/progress/courses?', '{"hi":"hi"}',{
//     jar: cookieJar, // tough.CookieJar or boolean
//     withCredentials: true,
   
//     headers: {
//         'Authorization': 'Bearer ' + UserService.getToken()}
//     } // If true, send cookie stored in jar
//   )
        // axios.post( '/progress/courses','{"hi":"hi"}',
        //    { headers: {
        //       'Content-Type': 'application/json',
        //       'Authorization': `Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzMUlnc25BNmJKV0FxSG5KbE5Da2dzekRSb25RbE9hdUJiTkxlc1VraVM4In0.eyJleHAiOjE2MjIzMDYwNzksImlhdCI6MTYyMjMwNTc3OSwiYXV0aF90aW1lIjoxNjIyMzA1Nzc4LCJqdGkiOiIyNDMyODQ4Ni1lMzdlLTQyNGUtYTNkMy04YjFjZDY5ZGQ5ZTYiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvbXlyZWFsbSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJmOjI0ZTZhOWUwLTJkYzktNGQ3OS1hZjA3LTYyN2MzMmNmNDRiNTptYSIsInR5cCI6IkJlYXJlciIsImF6cCI6Im15Y2xpZW50Iiwibm9uY2UiOiJlMzVkYjlhNC05ZjdmLTQ5OGItOGMzNy1iMjBhZTdhZmFmMzUiLCJzZXNzaW9uX3N0YXRlIjoiZDI1ZmVjNjktYjBiNy00ZDU5LWFkMDQtZjUxNDQxMzU5OTU5IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODMiLCJodHRwOi8vMTI3LjAuMC4xOjMwMDAvaG9tZSIsImh0dHA6Ly8xMjcuMC4wLjE6MzAwMCIsImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMC8iLCJodHRwOi8vbG9jYWxob3N0OjMwMDAiLCJodHRwOi8vbG9jYWxob3N0OjgwODciLCJodHRwOi8vbG9jYWxob3N0OjMwMDAvaG9tZSJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6Im1hIn0.IiiEeE3DfqVwOXbfzxCzVd2NpIrMpw7NIRTsysQNtHWs3tfioGkOUU3LmgmnoXgY-xEw8uPcXpy7uZOOT6_aOwKrQBnzSRJpOkDiPyJFdWHzByvPdgIv5x6G_sC645SNlMhN5M1BTwdrNuU62-L9AQYYGWy76SQbDs9RfoZni6RAAtG5LB9-dR_EHGStWlqqSeWqozHfnLAvtz84AN2NNsP5J7U6UfQP5JEjqhqUsF7zOlZu8diPNp-gUf-iywng4WuTf3N_8-dshcuIBD7wOeLtfurbMPl5O8jGtOvO9Q6UjabyVTgtAgBQbTxkpQfTg9qtZf3wn7RZa61_dfRgZw`
        //     }}
         
            
        //   )
        // dispatch(allpoop('{"hi":"hi"}'))
        dispatch(allCourses());

    }, [])
    
    
		
        

        return (
            <div className="display-column">
                <MenuComponent />
                <div>Logged in User</div>
               
                <button onClick={() => {}}>do</button>
                <div className="container  px-lg-5 bg-light border">
                    <br />
                    <div className="text-center">
                        <img src="images/sopranoRecorder2.jpg" alt="..." className="img-thumbnail img-square" />
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
                            <div>{courses.map(c => <CourseStartComponent user_course={c}/>
                            
                            )}
                            </div>
                        }  
                        
                    </div>
                </div>
            </div>
        );
    }


export default CoursesComponent;

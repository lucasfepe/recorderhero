import React, { Fragment, useState } from "react";
import AuthenticationService from "../keycloak";
import MenuComponent from './MenuComponent';
import NoteDisplayComponent from './NoteDisplayComponent';
import NotesComponent from './NotesComponent';
import { MDBBtn } from "mdbreact";


const SessionComponent = (props) => {

    
        
       
    
    const [course, setCourse] = useState(props.location.state.detail);
   

        return (
            <div className="display-column">
                Session


                <div id="hidden" >
                    <div className="text-center">Notes Left:</div>
                    <div className="text-center bg-primary p-3 text-light" id="numberNotes"></div>
                </div>
                <br/><br/>
                    <div className="container-fluid "><div className="row">
                    <NoteDisplayComponent user_course={course} />
                   
                    <NotesComponent/>
                    
        </div></div>


                
            </div>
        );
    }


export default SessionComponent;

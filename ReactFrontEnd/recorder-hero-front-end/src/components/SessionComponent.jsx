import React, { Fragment, useState } from "react";

import NoteDisplayComponent from './NoteDisplayComponent';
import NotesComponent from './NotesComponent';



const SessionComponent = (props) => {

    
        
       
    
    const [course, setCourse] = useState(props.location.state.detail);
   

        return (
            <div className="display-column">
                


                <div className="hidden" >
                    <div className="text-center">Notes Left:</div>
                    <div className="text-center bg-primary p-3 text-light" id="numberNotes"></div>
                </div>
                <br/><br/>
                    <div className="container-fluid text-center"><div className="row">
                    <NoteDisplayComponent user_course={course} />
                   
                    <NotesComponent/>
                    
        </div></div>


                
            </div>
        );
    }


export default SessionComponent;

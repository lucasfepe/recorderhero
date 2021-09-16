import React, { useEffect } from "react";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { getSessionStats } from '../modules/sessionStats'
import { MDBProgress } from 'mdbreact'



const NotesComponent = () => {

   const dispatch = useDispatch();
   const { accidentals } = useSelector((state) => state, shallowEqual);
   const { sessionStats } = useSelector((state) => state);
   const { courses } = useSelector((state) => state);

   useEffect(() => {
      console.log("WHYYYYYYYYYYMEMEEEE")
      console.log(accidentals)
        dispatch(getSessionStats(accidentals.listOfEntries));
        
   },[accidentals])
   
    

        return (
            
                    
                    <div className="col-4  d-flex justify-content-center  align-content-center flex-wrap">
                {!courses[0].challenge && sessionStats.map(c => <>
                   <MDBProgress value={c[1] * 20} className="position-relative bg-dark" striped height="20px">
                   <small className="justify-content-center align-items-center 
d-flex position-absolute w-100 text-light "><span id="hi1" className="fullText">{c[0]}: {c[1]}/5</span></small>

                   </MDBProgress>
                  </>)}
                  </div>
);
    

                   }

export default NotesComponent;

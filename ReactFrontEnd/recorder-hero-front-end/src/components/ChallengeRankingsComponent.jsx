import React, {useEffect, useState, useDebugValue} from "react";
import { useDispatch, useSelector } from "react-redux";
import { getHighScores } from "../modules/highScores";
import MenuComponent from './MenuComponent';
import { MDBModal, MDBTooltip,  MDBAlert , MDBModalBody, MDBModalHeader, MDBModalFooter, MDBContainer, MDBRow, MDBCol, MDBBtn, MDBCard, MDBCardBody, MDBIcon , MDBTable, MDBTableBody, MDBTableHead} from 'mdbreact';

const ChallengeRankingsComponent = () => {
    const dispatch = useDispatch();
    const useStateWithLabel = (initialValue, name) => {
		const [value, setValue] = useState(initialValue);
		
		useDebugValue(`${name}: ${value}`);
		return [value, setValue]; 
	  } ;
    const [instrument, setInstrument] = useStateWithLabel('SOPRANO_RECORDER',"Instrument");
    const [key, setKey] = useStateWithLabel('G',"Key");
    const [clef, setClef] = useStateWithLabel('TREBLE',"Clef");
    
    const [range, setRange] = useStateWithLabel('Low',"Range");
    const [octave, setOctave] = useStateWithLabel('H',"Octave");
    const highScores = useSelector((state) => state.highScores);
      
    const [modal, setModal] =useStateWithLabel(false, "modal")
 

   const toggle = () => {
        setModal(!modal)
        
      }
    

        useEffect(() => {
            
        },[])
   

        const sendClicked = () => {
            dispatch(getHighScores(instrument, key, clef, range, octave))
            setModal(true);
        }

   

    
        return (
            <div className="display-column">
                <MenuComponent />
                
                <MDBContainer>
      <MDBRow>
        <MDBCol md="6">
          <MDBCard >
            <MDBCardBody>
              <form id="highScoreForm " className="">
              <MDBTooltip
          domElement
          tag="span"
          placement="right"
        >
           <span id="lookuphigh">Look Up high scores </span>
          <span>Hit <i>SEND</i> with default settings to begin</span>
        </MDBTooltip>
        <br/><br/>
        <div className="form-group">
                <label
                  htmlFor="InstrumentHighScore"
                  className="grey-text font-weight-light d-none"
                >
                  Instrument 
                </label>
                <select id="InstrumentHighScore" className="d-none browser-default custom-select" onChange={e => setInstrument(e.target.value)}>
          <option>Choose your option</option>
          <option selected="selected" value="SOPRANO_RECORDER">Soprano Recorder</option>
          
        </select></div>
        <div className="form-group">
                <label
                  htmlFor="KeyHighScore"
                  className="grey-text font-weight-light"
                >
                  Key
                </label>
                <select id="KeyHighScore" className="browser-default custom-select" onChange={e => setKey(e.target.value)}>
          <option>Choose your option</option>
          <option value="C">C</option>
<option selected="selected" value="G">G</option>
<option value="D">D</option>
<option value="A">A</option>
<option value="E">E</option>
<option value="F">F</option>
<option value="Bb">Bb</option>
<option value="Eb">Eb</option>
<option value="Ab">Ab</option>
          
        </select></div>
        <div className="form-group">
                <label
                  htmlFor="ClefHighScore"
                  className="grey-text font-weight-light d-none"
                >
                  Clef
                </label>
                <select id="ClefHighScore" className=" d-none browser-default custom-select" onChange={e => setClef(e.target.value)}>
          <option>Choose your option</option>
          <option selected="selected" value="TREBLE">Treble</option>
<option value="BASS">Bass</option>
<option value="TENOR">Tenor</option>
<option value="ALTO">Alto</option>

          
        </select></div>
        <div className="form-group">
                <label
                  htmlFor="RangeHighScore"
                  className="grey-text font-weight-light d-none"
                >
                  Note Range
                </label>
                <select id="RangeHighScore" className="d-none browser-default custom-select" onChange={e => setRange(e.target.value)}>
          <option>Choose your option</option>
          <option selected="selected" value="Low">Low Notes</option>
          <option value="High">High Notes</option>
          <option value="Full">Full Range</option>
        </select></div>
        <div className="form-group">
                <label
                  htmlFor="OctaveHighScore"
                  className="grey-text font-weight-light d-none"
                >
                  Octave Shift
                </label>
                <select id="OctaveHighScore" className="d-none browser-default custom-select" onChange={e => setOctave(e.target.value)}>
          <option>Choose your option</option>
          <option selected="selected" value="H">Standard</option>
          <option value="HH">Octave Up</option>
          <option value="L">Octave Down</option>
        </select></div>
                <div className="text-center py-4 mt-3">
                  <MDBBtn className="btn " type="button" onClick={() => sendClicked()}>
                    Send
                    <MDBIcon far icon="paper-plane" className="ml-2" />
                   
                  </MDBBtn>
                  <br/><br/>
                  {(highScores.length > 0 ? highScores[0].id == null : false) && <MDBAlert color="info" >
        No data to display
      </MDBAlert>}
                </div>
              </form>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
<br/>
{(modal && highScores.length > 0 ? highScores[0].id != null : false ) &&      
<div id="modal">
<MDBContainer>
      
      <MDBModal isOpen={modal} toggle={toggle}>
        <MDBModalHeader toggle={toggle}>Leaderboard</MDBModalHeader>
        <MDBModalBody>
         
    <MDBTable>
      <MDBTableHead>
        <tr>
          <th>#</th>
          <th>Username</th>
          <th>Score</th>
         
        </tr>
      </MDBTableHead>
      <MDBTableBody>
          {highScores.sort((a, b) => b.score - a.score).map((e, index) => 
            <tr>
            <td>{index + 1}</td>
            <td>{e.user.username}</td>
            <td>{e.score}</td>
           
          </tr>
            )}
     
      
      </MDBTableBody>
    </MDBTable>
        </MDBModalBody>
        <MDBModalFooter>
          <MDBBtn color="secondary" onClick={toggle}>Close</MDBBtn>
          {/* <MDBBtn color="primary">Save changes</MDBBtn> */}
        </MDBModalFooter>
      </MDBModal>
    </MDBContainer>
    </div>}
<br/>
    </div>
          
        );
    
}

export default ChallengeRankingsComponent;

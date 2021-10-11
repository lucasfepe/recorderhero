import React, {useEffect, useState, useCallback, useDebugValue} from "react";
import { useDispatch, useSelector } from "react-redux";
import { getHighScores } from "../modules/highScores";
import MenuComponent from './MenuComponent';
import Vex from "vexflow";
import Divider from '@material-ui/core/Divider'
import { allCourses  } from "../modules/courses";
import UserService from '../ServicesNew/UserService'
import { MDBModal, MDBTooltip,  MDBAlert , MDBModalBody, MDBModalHeader, MDBModalFooter, MDBContainer, MDBRow, MDBCol, MDBBtn, MDBCard, MDBCardBody, MDBIcon , MDBTable, MDBTableBody, MDBTableHead} from 'mdbreact';

const CustomRunComponent = (props) => {
    const dispatch = useDispatch();
    const useStateWithLabel = (initialValue, name) => {
		const [value, setValue] = useState(initialValue);
		
		useDebugValue(`${name}: ${value}`);
		return [value, setValue]; 
	  } ;
   
    const [key, setKey] = useStateWithLabel('G',"Key");
    const [clef, setClef] = useStateWithLabel('TREBLE',"Clef");
    var notesa = new Array("C1","D1","E1","F1","G1","A1","B1","C2","D2","E2","F2","G2","A2","B2","C3","D3","E3","F3","G3","A3","B3","C4","D4","E4","F4","G4","A4","B4","C5","D5","E5","F5","G5","A5","B5","C6","D6","E6","F6","G6","A6","B6","C7","D7","E7","F7","G7","A7","B7");
    var notes = [...notesa].reverse();
    const [notesActive,setNotesActive] = useStateWithLabel(notes,'notes active');
    const [instrument, setInstrument] = useStateWithLabel('SOPRANO_RECORDER',"Instrument");
    const [lowNote, setLowNote] = useStateWithLabel('',"LowNote");
    const [highNote, setHighNote] = useStateWithLabel('',"HighNote");
    const [octave, setOctave] = useStateWithLabel(0,"Octave");
    const highScores = useSelector((state) => state.highScores);
      
    const [modal, setModal] =useStateWithLabel(false, "modal")
    const [isAccidental, setIsAccidental] = useStateWithLabel(false, "isAccidental")
    const [accidentalSlider, setAccidentalSlider] = useStateWithLabel(50, "AccidentalSlider")
    const [startError, setStartError] = useStateWithLabel('', "startError");
    useEffect(() => {

      dispatch(allCourses(UserService.getUsername())).then(res => console.log(res));
       

   }, [])

   const toggle = () => {
        setModal(!modal)
        
      }
    

      
      
        useEffect(() => {
            switch(instrument) {
                case "SOPRANO_RECORDER":
                   
                    var ayy = [...notes];
                    setNotesActive(ayy.slice(notes.indexOf("G7"),notes.indexOf("C5") +1));
                    setLowNote("C5");
                    setHighNote("C6");
                    setOctave(1);
                    setTimeout(() => {document.getElementById('octave').value = 1;
                    document.getElementById('LowNote').value = "C5";
                    document.getElementById('HighNote').value = "C6";},200)
                    

                  break;
                case "ALTO_RECORDER":
                  var ayy = [...notes];
                    setNotesActive(ayy.slice(notes.indexOf("E6"),notes.indexOf("F4") + 1));
                    setLowNote("F4");
                    setHighNote("F5");
                    setOctave(0);
                    setTimeout(() => {document.getElementById('octave').value = 0;
                    document.getElementById('LowNote').value = "F4";
                    document.getElementById('HighNote').value = "F5";},200)

                    
                    
                  break;
                default:
                  // code block
              }
        },[instrument])

        

            

        
        const startClicked = useCallback(() => {



            
            setStartError('')
    
            if(notes.indexOf(highNote) > notes.indexOf(lowNote)){
                setStartError('High note must be higher than low note.');
            } else if(false){
                setStartError('Email can not be empty')
            } 
            else {
                setStartError('')

                var i, j, noteValue = [];
                for (i = 1; i < 8; i++) {
                    for (j = 65; j < 72; j++) {
                        if (j > 66) {
                            noteValue.splice(noteValue.length - 2, 0, String.fromCharCode(j)
                                .concat(i));
                        } else {
                            noteValue.push(String.fromCharCode(j).concat(i));
                        }
                    }
                }
                //must do shnanigans to fit other level structure
                var highNoteindex = noteValue.indexOf(highNote) - noteValue.indexOf(lowNote);


                dispatch({
                    type: 'CLEAR_REPORT'
                  })
               
                props.history.push({pathname:'/session',state: { detail: { 
                    
                        level: {
                            accidentalSlider: accidentalSlider,
                            accidentals: isAccidental  ? "ON": "OFF",
                            keym: key,
                            lowNote: 0,
                            highNote: highNoteindex,
                            level: null
                        },
                        course: {
                            clef: clef,
                            lowNote: lowNote,
                            highNote: highNote,
                            instrument: instrument,
                            octaveShift: octave
                        }
                    } 
                 }});
            
           
    
        }},[key, clef, lowNote, highNote, octave, instrument, isAccidental, accidentalSlider]);

        const sendClicked = () => {
            dispatch(getHighScores(key, clef, lowNote, highNote, octave))
            setModal(true);
        }
        const previewClicked = () => {
            var VF;


            VF = Vex.Flow;
            // Create an SVG renderer and attach it to the DIV element named "boo".
            var div = document.getElementById("boo");
            div.innerHTML = null;
            var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
    
            // Size our SVG:
            renderer.resize(350, 400);
            // And get a drawing context:
            var context = renderer.getContext().scale(2, 2);
            var group = context.openGroup();
            var notess;
            
                notess = [
                    // A quarter-note C.
                    new VF.StaveNote({
                        clef: clef.toLowerCase(),
                        octave_shift: octave,
                        keys: [lowNote.slice(0,1) + "/" + lowNote.slice(1)],
                        duration: "4"
                    }),
                    new VF.StaveNote({
                        clef: clef.toLowerCase(),
                        octave_shift: octave,
                        keys: [highNote.slice(0,1) + "/" + highNote.slice(1)],
                        duration: "4"
                    })];
           
            // Create a voice in 4/4 and add the notes from above
            var voice;
            voice = new VF.Voice({
                num_beats: 2,
                beat_value: 4
            });
            voice.addTickables(notess);
    
            // Format and justify the notes to 400 pixels.
            var formatter;
            formatter = new VF.Formatter().joinVoices([voice]).format(
                [voice], 100);
    
    
            //for grand staff
            
                var stave = new VF.Stave(-2, 47, 800);
                // Add a clef and time signature.
                 stave.addKeySignature(key)
                stave.addClef(clef.toLowerCase());
                stave.setContext(context).draw();
    
                //Render voice
                voice.draw(context, stave);
        }

   

    
        return (
            <div className="display-column">
                <MenuComponent />
                
      
        
        
      
     
    
                
            
            <MDBCard id="cardCustomRun" >
            <MDBCardBody>
            <MDBContainer>
      <MDBRow>
      
        <MDBCol size="12" xl="5">
          
              <form id="highScoreForm " className="">
              <MDBTooltip
          domElement
          tag="span"
          placement="right"
        >
           <h1 className="text-center">Select settings</h1>
          <span>Hit <u><i>Generate Preview</i></u> to see what your settings will look like</span>
        </MDBTooltip>
        <br/><br/>
                
        <div className="form-group">
                <label
                  htmlFor="KeyHighScore"
                  className="grey-text font-weight-light"
                >
                  <MDBTooltip
          domElement
          tag="span"
          placement="top"
        >
           <span >Instrument</span>
          <span>Select from list or other if not in list <i>(will not display how to play notes for other).</i> Will control <b>Low Note</b> and <b>High Note</b> options bellow.</span>
        </MDBTooltip>
                </label>
                <select id="KeyHighScore" className="browser-default custom-select" onChange={e => {setInstrument(e.target.value);}}>
<option selected="selected" value="SOPRANO_RECORDER">Soprano Recorder</option>
<option value="ALTO_RECORDER">Alto Recorder</option>
<option value="PICK">Other</option>

          
        </select></div>
        <div className="form-group">
                <label
                  htmlFor="KeyHighScore"
                  className="grey-text font-weight-light"
                >
                  <MDBTooltip
          domElement
          tag="span"
          placement="top"
        >
           <span >Key</span>
          <span>Controls which notes are <b><i>sharp (#)</i></b> or <b><i>flat (b)</i></b> by default</span>
        </MDBTooltip>
                </label>
                <select id="KeyHighScore" className="browser-default custom-select" onChange={e => setKey(e.target.value)}>
          
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
                  className="grey-text font-weight-light "
                >
                   <MDBTooltip
          domElement
          tag="span"
          placement="top"
        >
           <span >Clef</span>
          <span>Controls note placement on the staff</span>
        </MDBTooltip>
                </label>
                <select id="ClefHighScore" className="  browser-default custom-select" onChange={e => setClef(e.target.value)}>
          
          <option selected="selected" value="TREBLE">Treble</option>
<option value="BASS">Bass</option>
<option value="TENOR">Tenor</option>
<option value="ALTO">Alto</option>

          
        </select></div>
        
        <div className="form-group">
                <label
                  htmlFor="RangeHighScore"
                  className="grey-text font-weight-light "
                >
                   <MDBTooltip
          domElement
          tag="span"
          placement="top"
        >
           <span >Low Note</span>
          <span>Controls lowest note that might appear</span>
        </MDBTooltip>
                </label>
                <select id="LowNote" className=" browser-default custom-select" onChange={e => setLowNote(e.target.value)}>
                
                    {notesActive.map(c => <option value={c}>{c}</option>)}
       
        </select></div>
        <div className="form-group">
                <label
                  htmlFor="RangeHighScore"
                  className="grey-text font-weight-light "
                >
                  <MDBTooltip
          domElement
          tag="span"
          placement="top"
        >
           <span >High Note</span>
          <span>Controls highest note that might appear</span>
        </MDBTooltip>
                </label>
                <select id="HighNote" className=" browser-default custom-select" onChange={e => setHighNote(e.target.value)}>
                
                    {notesActive.map(c => <option value={c}>{c}</option>)}
       
        </select></div>
        <div className="form-group">
                <label
                  htmlFor="OctaveHighScore"
                  className="grey-text font-weight-light "
                >
                 <MDBTooltip
          domElement
          tag="span"
          placement="top"
        >
           <span >Octave Shift</span>
          <span>Where on staff notes will show. <b>Does not change pitch.</b></span>
        </MDBTooltip>
                </label>
                <select id="octave" className=" browser-default custom-select" onChange={e => setOctave(e.target.value)}>
          
          <option selected="selected" value="0">Standard</option>
          <option value="-1">Octave Up</option>
          <option value="1">Octave Down</option>
          <option value="2">Two Octaves Down</option>
        </select></div>
        <div className="form-group">
        <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="defaultUnchecked" checked={isAccidental} onChange={e => setIsAccidental(!isAccidental)}/>
        <label class="custom-control-label" for="defaultUnchecked">
        <MDBTooltip
          domElement
          tag="span"
          placement="top"
        >
           <span >Accidentals</span>
          <span>Whether you'll see additional random <i><u>flats, sharps, naturals</u></i></span>
        </MDBTooltip>
            </label>
        </div>

        <div class="form-group">
        <label htmlFor="customRange1">
        <MDBTooltip
          domElement
          tag="span"
          placement="top"
        >
           <span >Accidental Frequency</span>
          <span>Likelihood of a note being accidented</span>
        </MDBTooltip>
           
            
            </label>
      <input type="range" className="custom-range" id="customRange1" onChange={e => setAccidentalSlider(e.target.value)}/>
      </div>
        </div>
      
                <div className="text-center py-4 mt-3">
                <MDBBtn className="btn " type="button" onClick={() => previewClicked()}>
                    Generate Preview
                    
                   
                  </MDBBtn>
                  <MDBBtn className="btn " type="button" onClick={() => startClicked()}>
                    Start
                    
                   
                  </MDBBtn>
                  <br/><br/>
                  
                </div>
              </form>
            
        </MDBCol>
        <MDBCol size="1" xl="0">
        <Divider orientation="vertical"  />
        </MDBCol>
        <MDBCol xl="5" size="12">
          <h1 className="text-center">Preview</h1>
          <div id="boo" className="boo text-center" ></div>
          {startError && <div className="alert alert-warning">{startError}</div>}
        </MDBCol>
        
      </MDBRow>
    </MDBContainer>
    </MDBCardBody>
          </MDBCard>
<br/>

<br/>
    </div>
          
        );
    
}

export default CustomRunComponent;
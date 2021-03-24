import React, { Fragment, useState } from "react";
import ScriptTag from 'react-script-tag';
import AuthenticationService from "../services/AuthenticationService";
import MenuComponent from './MenuComponent';
import { MDBBtn } from "mdbreact";
import  Vex from "vexflow";
import GameApiClient from '../services/GameApiClient';
import {  withRouter } from 'react-router-dom';


class NoteDisplayComponent extends React.Component {

    constructor(props) {
        super(props);
		this.detector = React.createRef();
		this.pitchElem = React.createRef();
		this.feedbackElem = React.createRef();
		
        this.state = {
			accidental: "",
			accidentalFrequency: this.props.user_course.level.accidentalSlider,
			accidentals: this.props.user_course.level.accidentals,
			accs: null,
            analyzer: null,
            arr: null,
            audioContext: null,
            buf: null,
            buflen: 2048,
			clef: this.props.user_course.course.clef,
            date: null,
			endClicked: false,
            firstNote: true,
            instrument_lower_limit_frequency: null,
            instrument_upper_limit_frequency: null,
            isChallenge:this.props.user_course.challenge,
			lowNoteIndex: 0,
			mode: this.props.user_course.course.instrument,
            newNote: null,
            no_sound_happened: true,
			noSharps: 0,
			noNaturals: 0,
			noFlats: 0,
			noteValue: [],
            note_shown: 'C5',
			note_shown_array: [],
			noteLetter: '',
            note_input_array: [],
			notes_done: this.props.user_course.challenge ? 3 : null,
			sessionId: '',
            notesLeft: this.props.user_course.challenge ? 3 : null,
            noteStrings: ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"],
			noteText: 'C',
			noteScale: '5',
			level_low_note: this.props.user_course.level.lowNote,
			numberOfNotes: this.props.user_course.level.highNote + 1 - this.props.user_course.level.lowNote,
			octave_shift: 1,
            peeked_array: [],
            previousNote: "",
			time: null,
            time_array_start: [],
			time_array_end: [],
            time_interval_between_readings: 0,
            time_prev: 0,


        }
	  
	   this.vex = this.vex.bind(this)
	   this.updatePitch = this.updatePitch.bind(this)
	   this.componentDidMount = this.componentDidMount.bind(this)
	   this.endClicked = this.endClicked.bind(this)
       
    }

	componentDidMount(){
		
		this.state.date = new Date();
		this.state.time_array_start.push(this.state.date.getTime());
		GameApiClient.enumerateAccidentals(this.props.user_course).then(
            res => {
                
				this.state.noFlats = res.data.flatCount;
				this.state.noSharps = res.data.sharpCount;
				this.state.noNaturals = res.data.naturalCount;
				this.state.sessionId = res.data.sessionId;
            }
        ).catch(
            () => {console.log("no work")}
        )


		var i,j;
		for (i = 1; i < 8; i++) {
			for (j = 65; j < 72; j++) {
				if (j > 66) {
					this.state.noteValue.splice(this.state.noteValue.length - 2, 0, String.fromCharCode(j)
							.concat(i));
				} else {
					this.state.noteValue.push(String.fromCharCode(j).concat(i));
				}
			}
		}
		var dd = this.props.user_course.course.lowNote
		this.state.lowNoteIndex = this.state.noteValue.indexOf(dd) + this.state.level_low_note;
		this.state.buf = new Float32Array( this.state.buflen );
		this.state.date = new Date();
		this.state.time = this.state.date.getTime();
		if(this.state.mode == "SOPRANO_RECORDER"){
			this.state.instrument_upper_limit_frequency = 2200;
			this.state.instrument_lower_limit_frequency = 500;
		}else if(this.state.mode == "ALTO_RECORDER"){
			this.state.instrument_upper_limit_frequency = 1460;
			this.state.instrument_lower_limit_frequency = 330;
		}else if(this.state.mode == "TENOR_RECORDER"){
			this.state.instrument_upper_limit_frequency = 1100;
			this.state.instrument_lower_limit_frequency = 250;
		}
			
		
		{this.vex()}
	}
  

    render() {
		
        return (
            
            <div className="col-4 ">
                
                  <ScriptTag isHydrating={true} type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vexflow/3.0.9/vexflow-min.js" /> 
						{this.state.notes_done && <div>Notes Left: {this.state.notesLeft}</div>}
                        <div className="vexbox ">
                            <div>
                                <div id="boo" className="boo " ></div>
                            </div>
                        </div>
                        <h3 id="answer1" className="text-center"></h3>

						<div hidden ref={this.detector}  className="vague">
			<div className="pitch"><span ref={this.pitchElem}></span>Hz</div>
			<div className="note"><span id="note">--</span></div>   
			<canvas id="output" width="300" height="42"></canvas>
			<div id="detune"><span id="detune_amt">--</span><span id="flat">cents &#9837;</span><span id="sharp">cents &#9839;</span></div>
						</div>
						<div className="feedback bg-primary" ref={this.feedbackElem}></div>
						
						<MDBBtn gradient="aqua"  onClick={this.endClicked}>End</MDBBtn>
                
						
                    </div>
        );




    }


	endClicked(){
		this.state.endClicked = true;
       

				GameApiClient.endGame(this.state).then(() => {

					this.state.audioContext.close();
				this.state.time_array_start.pop();
				setTimeout(()=>{this.props.history.push({pathname:'/runsummary',state: { sessionId : this.state.sessionId}})},500);
				})
        
        
    }
	
	 
  
	
    
	vex(){
		
		
		


		
		//# sourceMappingURL=vexflow-min.js.map
		/*
		The MIT License (MIT)
		
		Copyright (c) 2014 Chris Wilson
		
		Permission is hereby granted, free of charge, to any person obtaining a copy
		of this software and associated documentation files (the "Software"), to deal
		in the Software without restriction, including without limitation the rights
		to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
		copies of the Software, and to permit persons to whom the Software is
		furnished to do so, subject to the following conditions:
		
		The above copyright notice and this permission notice shall be included in all
		copies or substantial portions of the Software.
		
		THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
		IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
		FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
		AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
		LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
		OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
		SOFTWARE.
		*/
		window.AudioContext = window.AudioContext || window.webkitAudioContext;
		
		var theBuffer = null;
		var mediaStreamSource = null;
	
		
		
			this.newNote();
			
		
			this.state.audioContext = new AudioContext();
			var request = new XMLHttpRequest();
			
			request.responseType = "arraybuffer";
			request.onload = () => {
				this.state.audioContext.decodeAudioData( request.response, function(buffer) { 
					theBuffer = buffer;
				} );
			}
			// request.send();
			var detectorElem;
			detectorElem = this.detector.current;
		
			detectorElem.ondragenter = function () { 
				this.classList.add("droptarget"); 
				return false; };
			detectorElem.ondragleave = function () { this.classList.remove("droptarget"); return false; };
			detectorElem.ondrop = function (e) {
				  this.classList.remove("droptarget");
				  e.preventDefault();
				theBuffer = null;
		
				  var reader = new FileReader();
				  reader.onload = function (event) {
					this.state.audioContext.decodeAudioData( event.target.result, function(buffer) {
						theBuffer = buffer;
					  }, function(){alert("error loading!");} ); 
		
				  };
				  reader.onerror = function (event) {
					  alert("Error: " + reader.error );
				};
				  reader.readAsArrayBuffer(e.dataTransfer.files[0]);
				  return false;
			};

			let gotStream = ( stream ) =>  {
				// Create an AudioNode from the stream.
				mediaStreamSource = this.state.audioContext.createMediaStreamSource(stream);
			
				// Connect it to the destination.
				this.state.analyser = this.state.audioContext.createAnalyser();
				this.state.analyser.fftSize = 2048;
				mediaStreamSource.connect( this.state.analyser );
				
				this.updatePitch();
			}
		
		 getUserMedia(
				{
					"audio": {
						"mandatory": {
							"googEchoCancellation": "false",
							"googAutoGainControl": "false",
							"googNoiseSuppression": "false",
							"googHighpassFilter": "false"
						},
						"optional": []
					},
				}, gotStream);
		
			
		
		
		
		
		function error() {
			alert('Stream generation failed.');
		}
		
		function getUserMedia(dictionary, callback) {
			try {
				navigator.getUserMedia = 
					navigator.getUserMedia ||
					navigator.webkitGetUserMedia ||
					navigator.mozGetUserMedia;
				navigator.getUserMedia(dictionary, callback, error);
			} catch (e) {
				alert('getUserMedia threw exception :' + e);
			}
		}
		
		
		
		
		
		   
		
		
		
		
		var rafID = null;
		var tracks = null;
		
		
		
		
		
		
		function frequencyFromNoteNumber( note ) {
			return 440 * Math.pow(2,(note-69)/12);
		}
		
		function centsOffFromPitch( frequency, note ) {
			return Math.floor( 1200 * Math.log( frequency / frequencyFromNoteNumber( note ))/Math.log(2) );
		}
		
		var arr = document.getElementsByClassName("feedback");
		
		var imagePrefix;
		
			}
			 autoCorrelate( buf, sampleRate ) {
				// Implements the ACF2+ algorithm
				var SIZE = buf.length;
				var rms = 0;
			
				for (var i=0;i<SIZE;i++) {
					var val = buf[i];
					rms += val*val;
				}
				rms = Math.sqrt(rms/SIZE);
				if (rms<0.01) // not enough signal
					return -1;
			
				var r1=0, r2=SIZE-1, thres=0.2;
				for (var i=0; i<SIZE/2; i++)
					if (Math.abs(buf[i])<thres) { r1=i; break; }
				for (var i=1; i<SIZE/2; i++)
					if (Math.abs(buf[SIZE-i])<thres) { r2=SIZE-i; break; }
			
				buf = buf.slice(r1,r2);
				SIZE = buf.length;
			
				var c = new Array(SIZE).fill(0);
				for (var i=0; i<SIZE; i++)
					for (var j=0; j<SIZE-i; j++)
						c[i] = c[i] + buf[j]*buf[j+i];
			
				var d=0; while (c[d]>c[d+1]) d++;
				var maxval=-1, maxpos=-1;
				for (var i=d; i<SIZE; i++) {
					if (c[i] > maxval) {
						maxval = c[i];
						maxpos = i;
					}
				}
				var T0 = maxpos;
				var a, b;
				var x1=c[T0-1], x2=c[T0], x3=c[T0+1];
				a = (x1 + x3 - 2*x2)/2;
				b = (x3 - x1)/2;
				if (a) T0 = T0 - b/(2*a);
			
				return sampleRate/T0;
			}
			 updatePitch(  ){
				// updatePitch = ( time ) => {
					
					if(this.state.time != null && this.state.endClicked == false){
						if(this.state.time - this.state.time_prev >= this.state.time_interval_between_readings){
							this.state.time_prev = this.state.time;
					var cycles = new Array;
					this.state.analyser.getFloatTimeDomainData( this.state.buf );
					var ac = this.autoCorrelate( this.state.buf, this.state.audioContext.sampleRate );
					// TODO: Paint confidence meter on canvasElem here.
				
					
				
				
					
				
					 if (ac == -1) {
						this.detector.current.className = "vague";
						this.pitchElem.current.innerText = "--";
					
						this.state.no_sound_happened = true;
						
						
					 } else {
						var pitch;
						this.detector.current.className = "confident";
						 pitch = ac;
						//  this.pitchElem.innerText = Math.round( pitch ) ;
						 var note =  this.noteFromPitch( pitch );
						var scale_pitch = Math.trunc(note /12) - 1;
					var current_note = this.state.noteStrings[note%12] + scale_pitch;
					if(this.state.no_sound_happened){
						
						
					
						this.state.no_sound_happened = false;
						if(pitch < this.state.instrument_upper_limit_frequency && pitch > this.state.instrument_lower_limit_frequency){
							this.state.date = new Date();
						this.state.time_array_end.push(this.state.date.getTime());
						this.state.time_array_start.push(this.state.date.getTime());
							if( this.state.notesLeft !== null){
								this.setState({notesLeft: --this.state.notesLeft})
							}
								
									
							
								
							
							
							
						var noteLetterImg = "";
						if(this.state.note_shown == current_note){
							
						
						
							this.feedbackElem.current.classList.add("bg-success");
							
						setTimeout(()=>{
							
							this.feedbackElem.current.classList.remove("bg-success");
							},400);
						
						}else{
							this.feedbackElem.current.classList.add("bg-danger");
							
						setTimeout(()=>{
							
							this.feedbackElem.current.classList.remove("bg-danger");
							},400);
						}
						
						this.state.peeked_array.push(false);
						
						
						this.state.note_shown_array.push(this.state.note_shown);
						this.state.note_input_array.push(current_note.replace("#","s"));
					this.newNote();
					}else{
						this.feedbackElem.current.classList.add("bg-warning");
							
						setTimeout(()=>{
							
							this.feedbackElem.current.classList.remove("bg-warning");
							},400);
						this.state.time_array_start.pop();
						this.state.date = new Date();
						this.state.time_array_start.push(this.state.date.getTime());

						this.newNote(); 
					}
					
					
					this.state.firstNote = false;
					}
						
					
				}}}
				if(this.state.notes_done == this.state.notes_done - this.state.notesLeft){
					GameApiClient.endGame(this.state).then(() => {

						this.state.audioContext.close();
					this.state.time_array_start.pop();
					setTimeout(()=>{this.props.history.push({pathname:'/runsummary',state: { sessionId : this.state.sessionId}})},500);
					}
					).catch(error => {console.log(error)});
					
				
					
						}else if(this.state.endClicked == true){}else{
					if (!window.requestAnimationFrame)
						window.requestAnimationFrame = window.webkitRequestAnimationFrame;
						this.state.rafID = window.requestAnimationFrame( this.updatePitch );
					}
				}
				noteFromPitch( frequency ) {
					var noteNum = 12 * (Math.log( frequency / 440 )/Math.log(2) );
					return Math.round( noteNum ) + 69;
				}

				
		 newNote(){
		
			if(this.state.accidentals == "ON"){
				var ak = Math.round((Math.random() * 100));
				if(ak <= this.state.accidentalFrequency){
					
					do{
					
					var jsn = (Math.random() * 100);
					var kdi = Math.trunc(jsn);
					var kso = kdi % (this.state.noSharps + this.state.noNaturals + this.state.noFlats);
					
					if(kso >= (this.state.noFlats + this.state.noSharps)){
						this.state.accidental = "n";
						do{
							this.state.note_shown = this.state.naturalBin[Math.trunc(Math.random() * 100) % this.state.naturalBin.length];
						}while(this.state.note_shown == this.state.previousNote && this.state.naturalBin.length > 1);
					}else if(kso >= (this.state.noFlats)){
						this.state.accidental = "#";
						do{
							this.state.note_shown = this.state.sharpBin[Math.trunc(Math.random() * 100) % this.state.sharpBin.length];
						}
						while(this.state.note_shown == this.state.previousNote && this.state.sharpBin.length > 1);
					}else{
						
						this.state.accidental = "b";
						do{
							this.state.note_shown = this.state.flatBin[Math.trunc(Math.random() * 100) % this.state.flatBin.length];
						}while(this.state.note_shown == this.state.previousNote && this.state.flatBin.length > 1);
					}
					
				}while(this.state.note_shown == this.state.previousNote && (parseInt(this.state.noSharps) + parseInt(this.state.noNaturals) + parseInt(this.state.noFlats)) > 1);
					
				
				
				}else{
					this.state.accidental = "";
					if(this.state.naturalBin.includes(this.state.previousNote.charAt(0).concat(this.state.previousNote.charAt(this.state.previousNote.length - 1)))){
						this.state.previousNote = this.state.previousNote.charAt(0).concat(this.state.previousNote.charAt(this.state.previousNote.length - 1));
					}
					do{
						var x;
						x = Math.trunc((((Math.random() * 100)) % this.state.numberOfNotes));
						this.state.note_shown = this.state.noteValue[x + this.state.lowNoteIndex];
					}while(this.state.note_shown == this.state.previousNote && this.state.numberOfNotes > 1);
				}
				
			}else{
				
				this.state.previousNote = this.state.previousNote.charAt(0).concat(this.state.previousNote.charAt(this.state.previousNote.length - 1));
				
				do{
					var x;
					x = Math.trunc((((Math.random() * 100)) % this.state.numberOfNotes));
					this.state.note_shown = this.state.noteValue[x + this.state.lowNoteIndex];
				}while(this.state.note_shown == this.state.previousNote && this.state.numberOfNotes > 1);
			}
			
			this.state.previousNote = this.state.note_shown;
			this.state.noteLetter = this.state.note_shown.charAt(0);
			this.state.noteText = this.state.note_shown.substring(0, this.state.note_shown.length - 1);;
			this.state.noteLetterImg = this.state.noteLetter;
			this.state.noteScale = this.state.note_shown.charAt(this.state.note_shown.length - 1);
			this.state.accs = this.state.accidental;
			
			this.state.noteScaleImg = parseInt(this.state.noteScale)
			switch(this.state.key){
				case "C":if(this.state.noteLetter == 'F'){if(this.state.accs == 'b'){this.state.noteLetterImg = 'E';this.state.accs = '';}break;}
				case "G":if(this.state.noteLetter == 'C'){if(this.state.accs == 'b'){this.state.noteLetterImg = 'B';this.state.accs = '';this.state.noteScaleImg--;}break;}
				case "D":if(this.state.noteLetter == 'G'){if(this.state.accs == 'b'){this.state.noteLetterImg = 'F';this.state.accs = 's';}break;}
				case "A":if(this.state.noteLetter == 'D'){if(this.state.accs == 'b'){this.state.noteLetterImg = 'C';this.state.accs = 's';}break;}
				case "E":if(this.state.noteLetter == 'A'){if(this.state.accs == 'b'){this.state.noteLetterImg = 'G';this.state.accs = 's';}break;}
				case "B":if(this.state.noteLetter == 'E'){if(this.state.accs == 'b'){this.state.noteLetterImg = 'D';this.state.accs = 's';}else if(this.state.accs == '#'){this.state.noteLetterImg = 'F';this.state.accs = '';}break;}
				case "F#":if(this.state.noteLetter == 'B'){if(this.state.accs == 'b'){this.state.noteLetterImg = 'A';this.state.accs = 's';}else if(this.state.accs == '#'){this.state.noteLetterImg = 'C';this.state.accs = '';this.state.noteScaleImg++;}break;}
				case "C#":break;	
					
				case "F":if(this.state.noteLetter == 'E'){if(this.state.accs == 'b'){this.state.noteLetterImg = 'D';this.state.accs = 's';}else if(this.state.accs == '#'){this.state.noteLetterImg = 'F';this.state.accs = '';}break;}
				case "Bb":if(this.state.noteLetter == 'A' && this.state.accs == 'b'){this.state.noteLetterImg = 'G';this.state.accs = 's';break;}
				case "Eb":if(this.state.noteLetter == 'D' && this.state.accs == 'b'){this.state.noteLetterImg = 'C';this.state.accs = 's';break;}
				case "Ab":if(this.state.noteLetter == 'G' && this.state.accs == 'b'){this.state.noteLetterImg = 'F';this.state.accs = 's';break;}
				case "Db":if(this.state.noteLetter == 'C' && this.state.accs == 'b'){this.state.noteLetterImg = 'B';this.state.accs = '';this.state.noteScaleImg--;break;}
				case "Gb":if(this.state.noteLetter == 'F' && this.state.accs == 'b'){this.state.noteLetterImg = 'E';this.state.accs = '';break;}
						}
				switch(this.state.key){
				case "C#":if(this.state.noteLetter == 'B' && this.state.accs != 'n'){this.state.noteText+= '#';this.state.noteLetterImg = 'C';break;}
				case "F#":if(this.state.noteLetter == 'E' && this.state.accs != 'n'){this.state.noteText+= '#';this.state.noteLetterImg = 'F';break;}
				case "B":if(this.state.noteLetter == 'A' && this.state.accs != 'n'){this.state.noteText+= '#';this.state.noteLetterImg = 'As';break;}
				case "E":if(this.state.noteLetter == 'D' && this.state.accs != 'n'){this.state.noteText+= '#';this.state.noteLetterImg = 'Ds';break;}
				case "A":if(this.state.noteLetter == 'G' && this.state.accs != 'n'){this.state.noteText+= '#';this.state.noteLetterImg = 'Gs';break;}
				case "D":if(this.state.noteLetter == 'C' && this.state.accs != 'n'){this.state.noteText+= '#';this.state.noteLetterImg = 'Cs';break;}
				case "G":if(this.state.noteLetter == 'F' && this.state.accs != 'n'){this.state.noteText+= '#';this.state.noteLetterImg = 'Fs'}break;	
					
				case "Cb":if(this.state.noteLetter == 'F' && this.state.accs != 'n'){this.state.noteText += 'b';this.state.noteLetterImg = 'E';break;}
				case "Gb":if(this.state.noteLetter == 'C' && this.state.accs != 'n'){this.state.noteText += 'b';this.state.noteLetterImg = 'B';this.state.noteScaleImg--;break;}
				case "Db":if(this.state.noteLetter == 'G' && this.state.accs != 'n'){this.state.noteText += 'b';this.state.noteLetterImg = 'Fs';break;}
				case "Ab":if(this.state.noteLetter == 'D' && this.state.accs != 'n'){this.state.noteText += 'b';this.state.noteLetterImg = 'Cs';break;}
				case "Eb":if(this.state.noteLetter == 'A' && this.state.accs != 'n'){this.state.noteText += 'b';this.state.noteLetterImg = 'Gs';break;}
				case "Bb":if(this.state.noteLetter == 'E' && this.state.accs != 'n'){this.state.noteText += 'b';this.state.noteLetterImg = 'Ds';break;}
				case "F":if(this.state.noteLetter == 'B' && this.state.accs != 'n'){this.state.noteText += 'b';this.state.noteLetterImg = 'As';break;}
						}
				
						this.state.note_shown = this.state.noteText + this.state.noteScale;
			
			
			
			
			if(this.state.isChallenge == false){
				this.state.accs = this.state.accs == 'n'?'':this.state.accs;
				
				}
			
			
			
			var VF;
			
			
			VF = Vex.Flow;
			// Create an SVG renderer and attach it to the DIV element named "boo".
			var div = document.getElementById("boo");
			div.innerHTML = null;
			var renderer = new VF.Renderer(div, VF.Renderer.Backends.SVG);
			
			// Size our SVG:
			renderer.resize(150, 400);
			// And get a drawing context:
			var context = renderer.getContext().scale(2,2);
			var group = context.openGroup();
			var notes;
			if (this.state.accidental != "") {
				notes = [
				// A quarter-note C.
				new VF.StaveNote({
					clef : this.state.clef.toLowerCase() , octave_shift: this.state.octave_shift ,
					keys : [ this.state.noteLetter + this.state.accidental + "/" + this.state.noteScale ],
					duration : "q"
				}).addAccidental(0, new VF.Accidental(this.state.accidental)) ];
			} else {
				notes = [
				// A quarter-note C.
				new VF.StaveNote({
					clef : this.state.clef.toLowerCase(), octave_shift: this.state.octave_shift, 
					keys : [ this.state.noteLetter + "/" + this.state.noteScale ],
					duration : "q"
				}) ];
			}
			// Create a voice in 4/4 and add the notes from above
			var voice;
			voice = new VF.Voice({
				num_beats : 1,
				beat_value : 4
			});
			voice.addTickables(notes);
			
			// Format and justify the notes to 400 pixels.
			var formatter;
			formatter = new VF.Formatter().joinVoices([ voice ]).format(
					[ voice ], 400);
			
			
			//for grand staff
			if(this.state.clefOriginal=="TB"){
				var topStaff = new Vex.Flow.Stave(0, 17, 300);
				var bottomStaff = new Vex.Flow.Stave(0, 77, 300);
				topStaff.addClef('treble');
				bottomStaff.addClef('bass');
			
				topStaff.setContext(context).draw();
				  bottomStaff.setContext(context).draw();
				  if(x>8){
				  voice.draw(context,topStaff);
				  }else{
					  voice.draw(context,bottomStaff);  
					  
				  }
				  
				
				
			}else{
				var stave = new VF.Stave(-2, 47, 400);
			// Add a clef and time signature.
			stave.addClef(this.state.clef.toLowerCase());
			stave.setContext(context).draw();
			
			//Render voice
			voice.draw(context, stave);
			}
			}

			

    
}

export default withRouter(NoteDisplayComponent);

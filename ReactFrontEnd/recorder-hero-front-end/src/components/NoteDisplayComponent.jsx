import React, { Fragment, useState, useEffect, useRef, useDebugValue } from "react";
import ScriptTag from 'react-script-tag';


import Vex from "vexflow";
import { withRouter } from 'react-router-dom';
import { useDispatch, useSelector } from "react-redux";
import { generateAccidentals, endGame, generateAccidentalsget } from "../modules/accidentals";
<<<<<<< HEAD
import { MDBTooltip, MDBContainer, MDBBtn } from "mdbreact";
=======

>>>>>>> 5a83b81a0b3d281c43b7f8650fea5473532f87a0


const NoteDisplayComponent = (props) => {
	const dispatch = useDispatch();
	const useStateWithLabel = (initialValue, name) => {
		const [value, setValue] = useState(initialValue);
		
		useDebugValue(`${name}: ${value}`);
		return [value, setValue]; 
	  } ;
	  var sessionNotesVar;
	const numberOfAccidentals = useSelector((state) => state.accidentals);
	// const [sessionNotes, setSessionNotes] = useStateWithLabel([],"SessionNotes");
	const [course, setCourse] = useStateWithLabel(props.location.state.detail, "course");
	const [maxLevel, setMaxLevel] = useStateWithLabel(course.course.code.substring(course.course.code.length - 3) == "III" ? 36: 
	course.course.code.substring(course.course.code.length - 2) == "II" ? 58 : 60, "maxLevel");
	// var maxLevel;
	var notesAcc = new Array("C0","Cs0_Db0","D0","Ds0_Eb0","E0_Fb0","Es0_F0","Fs0_Gb0","G0","Gs0_Ab0","A0","As0_Bb0","Cb1_B0","C1_Bs0","Cs1_Db1","D1","Ds1_Eb1","E1_Fb1","Es1_F1","Fs1_Gb1","G1","Gs1_Ab1","A1","As1_Bb1","Cb2_B1","C2_Bs1","Cs2_Db2","D2","Ds2_Eb2","E2_Fb2","Es2_F2","Fs2_Gb2","G2","Gs2_Ab2","A2","As2_Bb2","Cb3_B2","C3_Bs2","Cs3_Db3","D3","Ds3_Eb3","E3_Fb3","Es3_F3","Fs3_Gb3","G3","Gs3_Ab3","A3","As3_Bb3","Cb4_B3","C4_Bs3","Cs4_Db4","D4","Ds4_Eb4","E4_Fb4","Es4_F4","Fs4_Gb4","G4","Gs4_Ab4","A4","As4_Bb4","Cb5_B4","C5_Bs4","Cs5_Db5","D5","Ds5_Eb5","E5_Fb5","Es5_F5","Fs5_Gb5","G5","Gs5_Ab5","A5","As5_Bb5","B5_Cb6","C6_Bs5","Cs6_Db6","D6","Ds6_Eb6","E6_Fb6","Es6_F6","Fs6_Gb6","G6","Gs6_Ab6","A6","As6_Bb6","Cb7_B6","C7_Bs6","Cs7_Db7","D7","Ds7_Eb7","E7_Fb7","Es7_F7","Fs7_Gb7","G7","Gs7_Ab7","A7","As7_Bb7","Cb8_B7","C8_Bs7","Cs8_Db8","D8","Ds8_Eb8","E8_Fb8","Es8_F8","Fs8_Gb8","G8","Gs8_Ab8","A8","As8_Bb8","B8");
	var noteLetterImg;
	var noteScaleImg;
	const [noteImage, setNoteImage] = useState();
	const feedbackElem = useRef(null);
	var accidental = "";
	var accidentalFrequency = course.level.accidentalSlider;
	var accidentals = course.level.accidentals;
	var accs = null;
	var analyzer = null;
	var arr = null;
	var level = course.level.level;
	var audioContext;
	const [audioContextState, setAudioContextState] = useStateWithLabel(audioContext, "audioContext");
	var buf = null;
	var buflen = 2048;
	var clef = course.course.clef;
	var date = null;
	const[endReached, setEndReached] = useState(false);
	var endClicked = false;
	var firstNote = true;
	var instrument_lower_limit_frequency = null;
	var instrument_upper_limit_frequency = null;
	const [isChallenge, setIsChallenge] = useStateWithLabel(course.challenge, "isChallenge");
	const [keyImageFile, setKeyImageFile] = useState("image/key_T_" + course.level.keym + ".PNG")
	var lowNoteIndex = 0;
	var mode = course.course.instrument;
	var modePrefix;
	var newNoteVariable = null;
	var no_sound_happened = true;
	var noSharps = useSelector((state) => state.accidentals.sharpCount);
	var noNaturals = useSelector((state) => state.accidentals.naturalCount);
	var time_array_start_dto = [];
	const noFlats = useSelector((state) => state.accidentals.flatCount);
	var noteValue = [];
	var note_shown = 'C5';
	const [note_shown_array, setNote_shown_array] = useStateWithLabel([],"note_shown_array");
	var noteLetter = '';
	const [note_input_array, setNote_input_array] = useStateWithLabel([],"note_input_array");
	var notes_done = course.challenge ? 30 : null;
	var sessionId = '';
	const [notesLeft, setNotesLeft] = useStateWithLabel(course.challenge ? 30 : null,"notesLeft");
	var notesLeftVar = notesLeft;
	var noteStrings = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"];
	var noteText = 'C';
	var noteScale = '5';
	var level_low_note = course.level.lowNote;
	var numberOfNotes = course.level.highNote + 1 - course.level.lowNote;
	var octave_shift = course.course.octaveShift;
	const [peeked_array, setPeeked_array] = useStateWithLabel([],"peeked_array");
	var previousNote = "";
	var time = null;
	const [time_array_start, setTime_array_start] = useStateWithLabel([],"time_array_start");
	const [time_array_end, setTime_array_end] = useStateWithLabel([],"time_array_end");
	var time_interval_between_readings = 200;
	var time_prev = 0;
	var rafID;
	var naturalBin = useSelector((state) => state.accidentals.naturalBin);
	var sharpBin = useSelector((state) => state.accidentals.sharpBin);
	var flatBin = useSelector((state) => state.accidentals.flatBin);
	var test = 0;
	const [testState, setTestState] = useState(0);

	const [key, setKey] = useState(course.level.keym);
	var originalClef = 4;
	var clefOriginal;

	





	const vex = () => {
		
		
		var i, j;
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
		var dd = course.course.lowNote
		lowNoteIndex = noteValue.indexOf(dd) + level_low_note
		buf = new Float32Array(buflen);

		if (mode == "SOPRANO_RECORDER") {
			instrument_upper_limit_frequency = 2200;
			instrument_lower_limit_frequency = 500;
			
			modePrefix = "SR_";
		} else if (mode == "ALTO_RECORDER") {
			instrument_upper_limit_frequency = 1460;
			instrument_lower_limit_frequency = 330;
			modePrefix = "AR_";
		} else if (mode == "TENOR_RECORDER") {
			instrument_upper_limit_frequency = 1100;
			instrument_lower_limit_frequency = 250;
			modePrefix = "TR_";
		}




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



		newNote();


		audioContext=new AudioContext();
		setAudioContextState(audioContext);
		var request = new XMLHttpRequest();

		request.responseType = "arraybuffer";
		request.onload = () => {
			audioContext.decodeAudioData(request.response, function (buffer) {
				theBuffer = buffer;
			});
		}
		// request.send();
		var detectorElem;




		let gotStream = (stream) => {
			// Create an AudioNode from the stream.
			mediaStreamSource = audioContext.createMediaStreamSource(stream);

			// Connect it to the destination.
			analyzer = audioContext.createAnalyser();
			analyzer.fftSize = 2048
			mediaStreamSource.connect(analyzer);

			updatePitch();
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


	}

	


	const autoCorrelate = (buf, sampleRate) => {
		// Implements the ACF2+ algorithm
		var SIZE = buf.length;
		var rms = 0;

		for (var i = 0; i < SIZE; i++) {
			var val = buf[i];
			rms += val * val;
		}
		rms = Math.sqrt(rms / SIZE);
		if (rms < 0.01) // not enough signal
			return -1;

		var r1 = 0, r2 = SIZE - 1, thres = 0.2;
		for (var i = 0; i < SIZE / 2; i++)
			if (Math.abs(buf[i]) < thres) { r1 = i; break; }
		for (var i = 1; i < SIZE / 2; i++)
			if (Math.abs(buf[SIZE - i]) < thres) { r2 = SIZE - i; break; }

		buf = buf.slice(r1, r2);
		SIZE = buf.length;

		var c = new Array(SIZE).fill(0);
		for (var i = 0; i < SIZE; i++)
			for (var j = 0; j < SIZE - i; j++)
				c[i] = c[i] + buf[j] * buf[j + i];

		var d = 0; while (c[d] > c[d + 1]) d++;
		var maxval = -1, maxpos = -1;
		for (var i = d; i < SIZE; i++) {
			if (c[i] > maxval) {
				maxval = c[i];
				maxpos = i;
			}
		}
		var T0 = maxpos;
		var a, b;
		var x1 = c[T0 - 1], x2 = c[T0], x3 = c[T0 + 1];
		a = (x1 + x3 - 2 * x2) / 2;
		b = (x3 - x1) / 2;
		if (a) T0 = T0 - b / (2 * a);

		return sampleRate / T0;
	}
	const updatePitch = () => {
		var a = sessionNotesVar;
		var date = new Date();
		time = date.getTime();


		if (time != null && endClicked == false) {
			var diffTime = time - time_prev
			if (diffTime >= time_interval_between_readings) {
				time_prev = time;
				var cycles = new Array;
				analyzer.getFloatTimeDomainData(buf);
				var ac = autoCorrelate(buf, audioContext.sampleRate);
				// TODO: Paint confidence meter on canvasElem here.






				if (ac == -1) {
					// detector.current.className = "vague";
					// pitchElem.current.innerText = "--";

					no_sound_happened = true;


				} else {
					var pitch;

					pitch = ac;
					//  pitchElem.innerText = Math.round( pitch ) ;
					var note = noteFromPitch(pitch);
					var scale_pitch = Math.trunc(note / 12) - 1;
					var current_note = noteStrings[note % 12] + scale_pitch;
					if (no_sound_happened) {
						if(!firstNote){
						setNote_shown_array((note_shown_array) => [...note_shown_array, note_shown.replace("#", "s")]);
						setNote_input_array((note_input_array) => [...note_input_array, current_note.replace("#", "s")]);
						}

						no_sound_happened = false;
						if (pitch < instrument_upper_limit_frequency && pitch > instrument_lower_limit_frequency) {
							date = new Date();
							if(!firstNote) setTime_array_end((time_array_end) => [...time_array_end, date.getTime()]);
							
							







							
							if (notesAcc.findIndex(x => x.includes(note_shown.replace('#','s'))) == notesAcc.findIndex(x => x.includes(current_note.replace('#','s')))) {
								if(!firstNote){
								increaseStat(numberOfAccidentals.listOfEntries.slice(0).reverse().indexOf(note_shown.replace('#','s')));
								}
								feedbackElem.current.classList.add("bg-success");

								setTimeout(() => {

									feedbackElem.current.classList.remove("bg-success");
								}, 400);

							} else {
								feedbackElem.current.classList.add("bg-danger");

								setTimeout(() => {

									feedbackElem.current.classList.remove("bg-danger");
								}, 400);
							}

							setPeeked_array((peeked_array) => [...peeked_array, false]);

							
							
							
						} else {
							feedbackElem.current.classList.add("bg-warning");

							setTimeout(() => {

								feedbackElem.current.classList.remove("bg-warning");
							}, 400);
							// var time_array_start_dto = time_array_start;
							time_array_start_dto.pop();
							
							date = new Date();
							setTime_array_start(
								 (time_array_start_dto) => [...time_array_start_dto]
								);

							newNote();
						}
						if (notesLeft !== null) {
								
							setNotesLeft(notesLeftVar--)
							if(notesLeftVar == -1){
								setEndReached(true);
							}
						}

						firstNote = false;
						newNote();
					}


				}
			}

		}
		if (notes_done == notes_done - notesLeft) {
			// GameApiClient.endGame(state).then(() => {

			// 	audioContext.close();
			// time_array_start.pop();
			// setTimeout(()=>{props.history.push({pathname:'/runsummary',state: { sessionId : sessionId}})},500);
			// }
			// ).catch(error => {console.log(error)});



		} else if (endClicked == true) { } else {
			if (!window.requestAnimationFrame)
				window.requestAnimationFrame = window.webkitRequestAnimationFrame;
			rafID = window.requestAnimationFrame(updatePitch);
		}
	}


	const noteFromPitch = (frequency) => {
		var noteNum = 12 * (Math.log(frequency / 440) / Math.log(2));
		return Math.round(noteNum) + 69;
	}


	const newNote = () => {

		if (accidentals == "ON") {
			var ak = Math.round((Math.random() * 100));
			if (ak <= accidentalFrequency) {

				do {

					var jsn = (Math.random() * 100);
					var kdi = Math.trunc(jsn);
					var kso = kdi % (noSharps + noNaturals + noFlats);

					if (kso >= (noFlats + noSharps)) {
						accidental = "n";
						do {
							note_shown = naturalBin[Math.trunc(Math.random() * 100) % naturalBin.length];
							note_shown = note_shown.replace("s","").replace("b","");
						} while (note_shown == previousNote && naturalBin.length > 1);
					} else if (kso >= (noFlats)) {
						accidental = "#";
						do {
							note_shown = sharpBin[Math.trunc(Math.random() * 100) % sharpBin.length];
						}
						while (note_shown == previousNote && sharpBin.length > 1);
					} else {

						accidental = "b";
						do {
							note_shown = flatBin[Math.trunc(Math.random() * 100) % flatBin.length];
						} while (note_shown == previousNote && flatBin.length > 1);
					}

				} while (note_shown == previousNote && (parseInt(noSharps) + parseInt(noNaturals) + parseInt(noFlats)) > 1);



			} else {
				accidental = "";
				if (naturalBin.includes(previousNote.charAt(0).concat(previousNote.charAt(previousNote.length - 1)))) {
					previousNote = previousNote.charAt(0).concat(previousNote.charAt(previousNote.length - 1));
				}
				do {
					var x;
					x = Math.trunc((((Math.random() * 100)) % numberOfNotes));
					note_shown = noteValue[x + lowNoteIndex];
				} while (note_shown == previousNote && numberOfNotes > 1);
			}

		} else {

			previousNote = previousNote.charAt(0).concat(previousNote.charAt(previousNote.length - 1));

			do {
				var x;
				x = Math.trunc((((Math.random() * 100)) % numberOfNotes));
				note_shown = noteValue[x + lowNoteIndex];
			} while (note_shown == previousNote && numberOfNotes > 1);
		}

		previousNote = note_shown;

		var noteLetter = note_shown.charAt(0);
		noteText = note_shown.substring(0, note_shown.length - 1);
		noteLetterImg = noteLetter;
		noteScale = note_shown.charAt(note_shown.length - 1);
		accs = accidental;

		noteScaleImg = parseInt(noteScale);
		switch (key) {
			case "C": if (noteLetter == 'F') { if (accs == 'b') { noteLetterImg = 'E'; accs = ''; } break; }
			case "G": if (noteLetter == 'C') { if (accs == 'b') { noteLetterImg = 'B'; accs = ''; noteScaleImg--; } break; }
			case "D": if (noteLetter == 'G') { if (accs == 'b') { noteLetterImg = 'F'; accs = 's'; } break; }
			case "A": if (noteLetter == 'D') { if (accs == 'b') { noteLetterImg = 'C'; accs = 's'; } break; }
			case "E": if (noteLetter == 'A') { if (accs == 'b') { noteLetterImg = 'G'; accs = 's'; } break; }
			case "B": if (noteLetter == 'E') { if (accs == 'b') { noteLetterImg = 'D'; accs = 's'; } else if (accs == '#') { noteLetterImg = 'F'; accs = ''; } break; }
			case "F#": if (noteLetter == 'B') { if (accs == 'b') { noteLetterImg = 'A'; accs = 's'; } else if (accs == '#') { noteLetterImg = 'C'; accs = ''; noteScaleImg++; } break; }
			case "C#": break;

			case "F": if (noteLetter == 'E') { if (accs == 'b') { noteLetterImg = 'D'; accs = 's'; } else if (accs == '#') { noteLetterImg = 'F'; accs = ''; } break; }
			case "Bb": if (noteLetter == 'A' && accs == 'b') { noteLetterImg = 'G'; accs = 's'; break; }
			case "Eb": if (noteLetter == 'D' && accs == 'b') { noteLetterImg = 'C'; accs = 's'; break; }
			case "Ab": if (noteLetter == 'G' && accs == 'b') { noteLetterImg = 'F'; accs = 's'; break; }
			case "Db": if (noteLetter == 'C' && accs == 'b') { noteLetterImg = 'B'; accs = ''; noteScaleImg--; break; }
			case "Gb": if (noteLetter == 'F' && accs == 'b') { noteLetterImg = 'E'; accs = ''; break; }
		}
		switch (key) {
			case "C#": if (noteLetter == 'B' && accs != 'n') { noteText += '#'; noteLetterImg = 'C'; break; }
			case "F#": if (noteLetter == 'E' && accs != 'n') { noteText += '#'; noteLetterImg = 'F'; break; }
			case "B": if (noteLetter == 'A' && accs != 'n') { noteText += '#'; noteLetterImg = 'As'; break; }
			case "E": if (noteLetter == 'D' && accs != 'n') { noteText += '#'; noteLetterImg = 'Ds'; break; }
			case "A": if (noteLetter == 'G' && accs != 'n') { noteText += '#'; noteLetterImg = 'Gs'; break; }
			case "D": if (noteLetter == 'C' && accs != 'n') { noteText += '#'; noteLetterImg = 'Cs'; break; }
			case "G": if (noteLetter == 'F' && accs != 'n') { noteText += '#'; noteLetterImg = 'Fs' } break;

			case "Cb": if (noteLetter == 'F' && accs != 'n') { noteText += 'b'; noteLetterImg = 'E'; break; }
			case "Gb": if (noteLetter == 'C' && accs != 'n') { noteText += 'b'; noteLetterImg = 'B'; noteScaleImg--; break; }
			case "Db": if (noteLetter == 'G' && accs != 'n') { noteText += 'b'; noteLetterImg = 'Fs'; break; }
			case "Ab": if (noteLetter == 'D' && accs != 'n') { noteText += 'b'; noteLetterImg = 'Cs'; break; }
			case "Eb": if (noteLetter == 'A' && accs != 'n') { noteText += 'b'; noteLetterImg = 'Gs'; break; }
			case "Bb": if (noteLetter == 'E' && accs != 'n') { noteText += 'b'; noteLetterImg = 'Ds'; break; }
			case "F": if (noteLetter == 'B' && accs != 'n') { noteText += 'b'; noteLetterImg = 'As'; break; }
		}

		note_shown = noteText + noteScale;
		setNoteImage(modePrefix + noteLetterImg + accs.replace("#","s").replace("n","") + noteScaleImg);



		if (isChallenge == false) {
			accs = accs == 'n' ? '' : accs;

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
		var context = renderer.getContext().scale(2, 2);
		var group = context.openGroup();
		var notes;
		if (accidental != "") {
			notes = [
				// A quarter-note C.
				new VF.StaveNote({
					clef: clef.toLowerCase(), octave_shift: octave_shift,
					keys: [noteLetter + accidental + "/" + noteScale],
					duration: "q"
				}).addAccidental(0, new VF.Accidental(accidental))];
		} else {
			
			notes = [
				// A quarter-note C.
				new VF.StaveNote({
					clef: clef.toLowerCase(), octave_shift: octave_shift,
					
					keys: [noteLetter + "/" + noteScale],
					duration: "q"
				})];
		}
		// Create a voice in 4/4 and add the notes from above
		var voice;
		voice = new VF.Voice({
			num_beats: 1,
			beat_value: 4
		});
		voice.addTickables(notes);

		// Format and justify the notes to 400 pixels.
		var formatter;
		formatter = new VF.Formatter().joinVoices([voice]).format(
			[voice], 400);


		//for grand staff
		if (clefOriginal == "TB") {
			var topStaff = new Vex.Flow.Stave(0, 17, 300);
			var bottomStaff = new Vex.Flow.Stave(0, 77, 300);
			topStaff.addClef('treble');
			bottomStaff.addClef('bass');

			topStaff.setContext(context).draw();
			bottomStaff.setContext(context).draw();
			if (x > 8) {
				voice.draw(context, topStaff);
			} else {
				voice.draw(context, bottomStaff);

			}



		} else {
			var stave = new VF.Stave(-2, 47, 400);
			// Add a clef and time signature.
			// stave.addKeySignature('E')
			stave.addClef(clef.toLowerCase());
			stave.setContext(context).draw();

			//Render voice
			voice.draw(context, stave);

			//set start time for this note
			var date = new Date();
			
			if(!firstNote){
				time_array_start_dto = [...time_array_start_dto, date.getTime()];
				setTime_array_start(time_array_start_dto);
			}
		}
	}

	const endClickedFunction = (auto) => {
		endClicked = true;
		var time_array_start_dto = [...time_array_start];
<<<<<<< HEAD
	var levelScoreToPass = course.level.points;
	var challengeId = course.level.challengeCode;
	var courseCode = course.course.code;
=======
		time_array_start_dto.pop();
>>>>>>> 5a83b81a0b3d281c43b7f8650fea5473532f87a0

		dispatch({
			type: 'CLEAR_ACCIDENTALS'
		  })
		dispatch(endGame(isChallenge, peeked_array, note_shown_array,
			note_input_array, time_array_start_dto, time_array_end, numberOfAccidentals.sessionId, level, levelScoreToPass, challengeId, courseCode, maxLevel))
		
			if(!auto) audioContextState.close();
		
		setTimeout(()=>{
			props.history.push({pathname:'/runsummary',state: { sessionId : numberOfAccidentals.sessionId, user_course: props.user_course}})
		},3000);
		


	}

	const increaseStat = (noteIndex) => {
		dispatch({type: "INCREASE_STAT", payload: noteIndex})
	}


	

	useEffect(() => {
		
		console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + numberOfAccidentals);
	if(numberOfAccidentals.listOfEntries != null)vex()
	console.log('i fire twice');
	}, [numberOfAccidentals])

	useEffect(() => {
		console.log('i fire once');
<<<<<<< HEAD
		dispatch(generateAccidentals(course));
=======
		dispatch(generateAccidentals(props.user_course));
>>>>>>> 5a83b81a0b3d281c43b7f8650fea5473532f87a0
	},[])

	useEffect(() => {if(endReached)endClickedFunction()},[endReached])

	





	return (
		<>
	<div className="col-4 ">
                        {course.challenge && <img src={"image/" + noteImage + ".PNG"}/>}
                    </div>
		<div className="col-4 ">

			<ScriptTag isHydrating={true} type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vexflow/3.0.9/vexflow-min.js" />
			{notes_done && <div>Notes Left: {notesLeft}</div>}
			 <div className="vexbox ">
				<div>
					<div id="boo" className="boo " ></div>
				</div>
			</div>
			<h3 id="answer1" className="text-center"></h3>

			
			<div className="feedback bg-primary " ref={feedbackElem}></div>
		






			<MDBContainer className="text-center p-5">
      <MDBTooltip placement="top">
        <MDBBtn color="primary">Key of {key}</MDBBtn>
        <div><img src={keyImageFile}/></div>
      </MDBTooltip>

    </MDBContainer>



		
			<MDBBtn gradient="aqua" onClick={() => endClickedFunction(false)}>End</MDBBtn>


		</div>
		</>
	);














}

export default withRouter(NoteDisplayComponent);

import React, { Fragment, useEffect, useState, Component } from 'react'
import MenuComponent from './MenuComponent';
import ScriptTag from 'react-script-tag';
import { getReport } from "../modules/report";
import CourseStartComponent from "./CourseStartComponent";
import  Highcharts from "highcharts";
import { useDispatch, useSelector, shallowEqual } from 'react-redux';
import { MDBContainer, MDBBtn, MDBModal, MDBModalBody, MDBModalHeader, MDBModalFooter , MDBJumbotron} from 'mdbreact';



const RunSummaryComponent = (props) => {

    const dispatch = useDispatch();
    const report = useSelector((state) => state.report, shallowEqual)
    const end_game = useSelector((state) => state.accidentals )
    const courses = useSelector((state) => state.courses )
    const [modal, setModal] =useState(end_game.levelup)
    // const [countKeySet, setCountKeySet]= useState( [])
    // const [mapMax, setMapMax]= useState( 0)
    // const [countValues, setCountValues]= useState( [])
    // const [correctValues, setCorrectValues]= useState( [])
    // const [timeMapMax, setTimeMapMax]= useState( 0)
    // const [timeKeySet, setTimeKeySet]= useState( [])
    // const [timeValues, setTimeValues]= useState( [])
    // const [timeTotal, setTimeTotal]= useState( 0)
    // const [overallNoteCorrect, setOverallNoteCorrect]= useState( 0)
    // const [overallNoteCount, setOverallNoteCount]= useState( 0)
       

   const toggle = () => {
        setModal(!modal)
        
      }

const reports = () => {
    if(report.correctValues != null){


    // var Highcharts;
    Highcharts.setOptions({
    
        colors: ['rgba(135,197,255,0.5)', 'rgba(135,197,255,1)']
    });
Highcharts.chart('container', {
    chart: {
        type: 'column'
    },
    title: {
        text: ''
    },
    subtitle: {
        text: ''
    },
    xAxis: {
        //page will not finish loading if this variable is empty or session has no notes
        categories: report.countKeySet,
        crosshair: true
    },
    yAxis: {
        min: 0,
        max:report.mapMax,
        title: {
            text: 'Number of Time Note Appeared'
        }
    },
    tooltip: {
        headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
        '<td style="padding:0"><b> {point.y:.f} </b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    plotOptions: {
        column: {           
          grouping: false
        }
    },
    series: [{
        name: 'Number of Appearances',
        data: report.countValues
    },{name: 'Correct',
        data: report.correctValues
}]});
    
//chart setting area
//
//
//chart2
Highcharts.setOptions({
    
        colors: ['rgba(135,197,255,1)']
    });
Highcharts.chart('container2', {
    chart: {
        type: 'column'
    },
    title: {
        text: ''
    },
    // subtitle: {
    //     text: 'Note Reaction Time'
    // },
    xAxis: {
        categories: report.timeKeySet,
        crosshair: true
    },
    yAxis: {
        min: 0,
        max:report.timeMapMax,
        title: {
            text: 'Response time for each note (milliseconds)'
        }
    },
    tooltip: {
        headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
        '<td style="padding:0"><b> {point.y:.0f} </b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    series: [{
        name: 'Average Reaction Time',
        data: report.timeValues
    }]
});
}

//chart2
//
//

}


        
        useEffect(() => {

            dispatch(getReport(props.location.state.sessionId));
          if(end_game.levelup){
            //   alert("level up");
          }
            
        },[])

        useEffect(() => {
            reports();
        },[report])
        
        // ReportApiClient.getPastNotes(this.props.location.state.sessionId).then(res => {
        //     console.log(res.data);
        //     this.setState({
        //        countKeySet: res.data.countKeySet,
        //        mapMax: res.data.mapMax,
        //        countValues: res.data.countValues,
        //        correctValues: res.data.correctValues,
        //        timeMapMax: res.data.timeMapMax,
        //        timeKeySet: res.data.timeKeySet,
        //        timeValues: res.data.timeValues,
        //        timeTotal: res.data.timeTotal,
        //        overallNoteCorrect: res.data.overallNoteCorrect,
        //        overallNoteCount: res.data.overallNoteCount
        //     })
        //     this.reports();
        // }
        //     ).catch(error => {console.log(error)})



        //generate reports
        
        
   
 
        
        return (
            <div className="display-column">
                 
                <MenuComponent />
                <ScriptTag isHydrating={true} type="text/javascript" src="https://code.highcharts.com/highcharts.js" />
                <ScriptTag isHydrating={true} type="text/javascript" src="https://code.highcharts.com/modules/exporting.js" />
               






                <div className="container">
                    <br/>
<<<<<<< HEAD
                <CourseStartComponent user_course={props.location.state.user_course}/>
                    <br/>
                    {end_game.challenge && <h2 className="center">To Pass: {courses[0].level.points}</h2>}
                    {end_game.challenge && <h2 className="center">Score: {Math.trunc(end_game.score)}</h2>}
=======
>>>>>>> 5a83b81a0b3d281c43b7f8650fea5473532f87a0
			<h2 align="center" class="where">Session Summary</h2>
          

			<div id="container"
				>
                    <img src="image/chartplaceholderdata.png" alt="..." className="" />
                     {/* <div class="lds-roller"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div> */}
                </div> 
		</div>
		<div className="container2">
			<h2 align="center ">Average Reaction Time</h2>

			<div id="container2"
				><img src="image/chartplaceholderdata.png" alt="..." className="" />
                     {/* <div class="lds-roller"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div> */}
                </div> 
<<<<<<< HEAD
		</div>
        
        
        
        
        


      {(end_game.levelup || end_game.courseComplete) &&      
<div id="modal">
<MDBContainer>
      <MDBBtn onClick={toggle}>Review Achievements</MDBBtn>
      <MDBModal isOpen={modal} toggle={toggle}>
        <MDBModalHeader toggle={toggle}>{end_game.levelup && "Level Up!"}{end_game.courseComplete && "Course Complete!"}</MDBModalHeader>
        <MDBModalBody>
        {end_game.levelup && "You are now in level " + end_game.level}
        {end_game.courseComplete && "You can now freely access any level in the course!"}
        </MDBModalBody>
        <MDBModalFooter>
          <MDBBtn color="secondary" onClick={toggle}>Close</MDBBtn>
          {/* <MDBBtn color="primary">Save changes</MDBBtn> */}
        </MDBModalFooter>
      </MDBModal>
    </MDBContainer>
    </div>}
        
        </div>
=======
		</div></div>
>>>>>>> 5a83b81a0b3d281c43b7f8650fea5473532f87a0
          
        );
    






    
    
    
    




}

export default RunSummaryComponent;

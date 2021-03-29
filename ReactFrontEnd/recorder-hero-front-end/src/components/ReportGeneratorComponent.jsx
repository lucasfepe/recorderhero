import * as React from "react";
import AuthenticationService from "../services/AuthenticationService";
import MenuComponent from './MenuComponent';
import SignUpService from '../services/SignUpService';
import LibraryApiClient from "../services/LibraryApiClient";
import { MDBContainer, MDBBtn, MDBRow, MDBCol } from 'mdbreact';
import ScriptTag from 'react-script-tag';
import  Highcharts from "highcharts";
import ReportApiClient from '../services/ReportApiClient';
import Select from 'react-select';
import { MDBIcon } from 'mdbreact'


class ReportGeneratorComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            session: "",
            
           sessions: [],
           countKeySet: [],
           mapMax: 0,
           countValues: [],
           correctValues: [],
           timeMapMax: 0,
           timeKeySet: [],
           timeValues: [],
           timeTotal: 0,
           option: [],
           overallNoteCorrect: 0,
           overallNoteCount: 0

        }
        
      
        this.componentDidMount = this.componentDidMount.bind(this);
        this.retrieveSession = this.retrieveSession.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount(){
        LibraryApiClient.getUserSessions(AuthenticationService.getLoggedInUserName()).then(res => {

            this.setState({
                sessions: res.data.content,
                options:  res.data.content
                .map(d => ({
                  "value" : d.id,
                  "label" : "Session "+d.id+": "+d.clef+" clef"
                }))
            })
        }
        ).catch(err => {console.log(err)});
       
        
       
        
       
    }

   retrieveSession(){

    ReportApiClient.getPastNotes(this.state.session ).then(res => {
        console.log(res.data);
        this.setState({
           countKeySet: res.data.countKeySet,
           mapMax: res.data.mapMax,
           countValues: res.data.countValues,
           correctValues: res.data.correctValues,
           timeMapMax: res.data.timeMapMax,
           timeKeySet: res.data.timeKeySet,
           timeValues: res.data.timeValues,
           timeTotal: res.data.timeTotal,
           overallNoteCorrect: res.data.overallNoteCorrect,
           overallNoteCount: res.data.overallNoteCount
        })
        this.reports();
    }
        ).catch(error => {console.log(error)})


    
}

handleChange(e){
    this.state.session = e.value;
   }

   

    render() {

        
         

        return (
            <div className="display-column">
                <MenuComponent />
                Sessions
                <MDBContainer size="sm">
                <MDBRow>
    <MDBCol size="4"></MDBCol>
    <MDBCol size="4" className="text-center"> 

    <Select options={this.state.options} onChange={this.handleChange.bind(this)}/>
         
        <MDBBtn gradient="aqua" onClick={this.retrieveSession}>Retrieve Session</MDBBtn>
        </MDBCol>
    <MDBCol size="4"></MDBCol>
  </MDBRow>

               
</MDBContainer> 
<ScriptTag isHydrating={true} type="text/javascript" src="https://code.highcharts.com/highcharts.js" />
                <ScriptTag isHydrating={true} type="text/javascript" src="https://code.highcharts.com/modules/exporting.js" />
                <div className="container">
			<h2 align="center">Session Summary</h2>

			<div id="container"
				>
                   
                </div>
		</div>
		<div className="container2">
			<h2 align="center">Reaction Time</h2>

			<div id="container2"
				>
                    
                </div>
		</div>
            </div>
        );
    }



    reports(){


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
            categories: this.state.countKeySet,
            crosshair: true
        },
        yAxis: {
            min: 0,
            max:this.state.mapMax,
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
            data: this.state.countValues
        },{name: 'Correct',
        	data: this.state.correctValues
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
            categories: this.state.timeKeySet,
            crosshair: true
        },
        yAxis: {
            min: 0,
            max:this.state.timeMapMax,
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
            name: 'Reaction Time',
            data: this.state.timeValues
        }]
    });
  	
  
  //chart2
  //
  //
  
    }



}

export default ReportGeneratorComponent;

import React, { Fragment, useEffect, useState } from 'react'
import MenuComponent from './MenuComponent';
import ScriptTag from 'react-script-tag';
import { getReport } from "../modules/report";

import  Highcharts from "highcharts";
import { useDispatch, useSelector, shallowEqual } from 'react-redux';



const RunSummaryComponent = (props) => {

    const dispatch = useDispatch();
    const report = useSelector((state) => state.report, shallowEqual)
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
        name: 'Reaction Time',
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
			<h2 align="center" class="where">Session Summary</h2>
          

			<div id="container"
				>
                    <img src="image/chartplaceholderdata.png" alt="..." className="" />
                     {/* <div class="lds-roller"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div> */}
                </div> 
		</div>
		<div className="container2">
			<h2 align="center ">Reaction Time</h2>

			<div id="container2"
				><img src="image/chartplaceholderdata.png" alt="..." className="" />
                     {/* <div class="lds-roller"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div> */}
                </div> 
		</div></div>
          
        );
    






    
    
    
    




}

export default RunSummaryComponent;

import React, {  useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { allSessions  } from "../modules/sessions";
import UserService from "../ServicesNew/UserService";
import MenuComponent from './MenuComponent';
import { MDBBtn } from "mdbreact";
import { getReport } from "../modules/report";
import  Highcharts from "highcharts";


const ReportGeneratorComponent = () => {

    const dispatch = useDispatch();
    var date = new Date();

    const { sessions } = useSelector((state) => state)
    const report = useSelector((state) => state.report)
    const [value, setValue] = useState();
   
    const onChange = event => setValue(event.target.value);

   const generateReport = () => {
    dispatch(getReport(value));
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

        dispatch(allSessions(UserService.getUsername()));
        
    
    
       },[])
   
        return (
            <div className="display-column">
                <MenuComponent />
                <br/><br/><br/><br/>
                <select value={value} onChange={onChange} className="browser-default custom-select select-small">
                    
                <option>Choose your option</option>
                    {sessions.map(c => <option value={c.id} key={c.id}>{new Date(c.startTime).toString()}</option>)}
          
        </select>
        <br/>
        <MDBBtn value="practice" gradient="aqua" onClick={generateReport}>Generate Report</MDBBtn>
        <br/>
        <div className="container">
			<h2 align="center">Session Summary</h2>
           

			<div id="container"
				>
                    <img src="image/chartplaceholder.png" alt="..." className="img-thumbnail img-square" />
		</div>
        </div>
		<div className="container2">
			<h2 align="center">Average Reaction Time</h2>

			<div id="container2"
				><img src="image/chartplaceholder.png" alt="..." className="img-thumbnail img-square" />
                  
		</div></div>
            </div>
        );
    
}

export default ReportGeneratorComponent;

import * as React from "react";
import AuthenticationService from "../services/AuthenticationService";
import MenuComponent from './MenuComponent';
import { Link, withRouter } from 'react-router-dom'
import NonFixedNavbarExample from './MenuComponent'

class HomeComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
           
        }
        
      
        this.componentDidMount = this.componentDidMount.bind(this);
    }

    componentDidMount(){
        this.setState({
            logedinuser: AuthenticationService.getLoggedInUserName(),
			logedinroles: AuthenticationService.getLoggedInUserRoles()
    })


        
       
    }

   

   

    render() {
        return (
            <div className="display-column">
                <MenuComponent />
				
              Home
              <div>Logged in User</div>
                <div>{this.state.logedinuser}</div>
                <div>{this.state.logedinroles}</div>
              


                

     <h1 className="text-center mt-5 display-1"><strong>Welcome to Note Training App!</strong></h1>
	 <p className=" text-center lead">Learn musical notes and compete for the highest score!</p> 
	 
	 
	 <div className="jumbotron">
	 <h3 className="text-center"></h3>
	 <div className="d-flex justify-content-center">
	 <Link className="" to="/courses">Courses</Link>
     
	 </div>
	 </div>
     <br/><br/>
	 <div className="container px-lg-5 bg-light border">
		
		 <div className="row mx-lg-n5 ">
			
		 <div className="col-lg py-3 px-md-5 px-sm-4  ">
			<h3 className="display-4 text-center my-3">Brief Music Background</h3>
			<hr/>
			<dl>
				<dt>
					Staff
				 </dt>
				 <dd>
					A set of five parallel lines where notes are written
					<div className="row w-50  mx-auto d-block">
						<img src="images/Staff.png" className="img-thumbnail "/>
					</div>
				 </dd>
				 <dt>
					Notes
				 </dt>
				 <dd>
					There are seven notes: C, D, E, F, G, A, and B. 
					
				 </dd>
				 <dt>
					Scale
				 </dt>
				 <dd>
					C is the 1st note in a scale and the lowest, B is the 7th note and higher than C.
					Each scale has each of the notes. In this app you might see as many as 7 scales. A note 'C' from the fourth scale is lower than a 'C' from the fifth scale and is written as 'C4'. 
					
					
				 </dd>
				
				 <dt>
					 Clef
				 </dt>
				 <dd>
					Clefs determine which note the spaces and lines on the staff represent. A space is one note higher than the line bellow it and a line is one note higher than the space bellow it. 
				 </dd>
				 <ul >
					 <li>
				 <dt>
					Treble Clef
					
				</dt>
				<dd>
				   Sets G on the second line
				   <div className="row w-50  mx-auto d-block">
					<img src="images/trebleG.jpg" className="img-thumbnail "/></div>
				</dd></li>
			<li>	<dt>
					Bass Clef
				</dt>
				<dd>
				   Sets F on the fourth line
				   <div className="row w-50  mx-auto d-block">
					<img src="images/bassF.jpg" className="img-thumbnail "/></div>
				</dd></li>
			<li>	<dt>
					Alto Clef
				</dt>
				<dd>
				   Sets C on the third line
				   <div className="row w-50  mx-auto d-block">
					<img src="images/altoC.jpg" className="img-thumbnail "/></div>
				</dd></li>
			<li>	<dt>
					Tenor Clef
				</dt>
				<dd>
				   Sets C on the fourth line
				   <div className="row w-50  mx-auto d-block">
					<img src="images/tenorC.jpg" className="img-thumbnail "/></div>
				</dd></li>
			</ul>
			</dl>
		 </div> <div className="col-lg py-3 px-md-5 px-sm-4 bg-light ">
			<h3 className="display-4 text-center my-3">Instructions
			</h3>
			<hr/>
			
			<p>A fresh user has access to three notes on the Treble Clef: F4, G5, A4. The '4' indicates these are part of the fourth scale.</p>
			<p>As you complete <a href="/newrun">New Runs</a>, if you got at least 5 correct notes, you will receive a new note.</p>
			<p>Keep getting achievements to unlock new notes, clefs and challenges.</p>
			<p>So far, you can unlock 12 notes on the Treble and Bass clef and 3 challenges. The last challenge is way</p> <div  className=" text-hi text-1  text-center">awesome</div>
			
			<p>When you unlock a challenge it will appear in the <em><mark>Challenges</mark></em> button in New Run page.</p>
			<p>Taking a challenge will post your highest score in the <a href="/rankings">Challenge Rankings</a> page.</p>
			<dl>
				<dt>
					Run Summary
				</dt>
				<dd>
					View results for most recent run
				</dd>
				<dt>
					Report Generator
				</dt>
				<dd>
					Create reports with data from any previous run
				</dd>
				<dt>
					Report
				</dt>
				<dd>
					View most recent report
				</dd>
				<dt>
					Overall Stats
				</dt>
				<dd>
					See summary statistics for all the runs
				</dd>
				<dt>
					Achievements
				</dt>
				<dd>
					See all your acquired achievements!
					Achievements can only be earned in runs in which more than half the notes were correct.
				</dd>
				 <ul >
					 <li>
				 <dt>
					<img className="star" src="images/star.svg"/>
					
				</dt>
				<dd>
				  Get a note correct at least 5 times with an average speed of less than 5 seconds in a run with at least 3 notes.
				</dd></li>
			<li>	<dt>
					<img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/>
				</dt>
				<dd>
				 Get a note correct at least 5 times with an average speed of less than 3 seconds in a run with at least 5 notes.
				</dd></li>
			<li>	<dt>
					<img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/>
				</dt>
				<dd>
				  Get a note correct at least 5 times with an average speed of less than 2 seconds in a run with at least 7 notes.
				</dd></li>
			<li>	<dt>
					<img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/>
				</dt>
				<dd>
				  Get a note correct at least 5 times with an average speed of less than 1 second in a run with at least 15 notes.
				</dd></li>
			<li>	<dt>
					<img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/><img className="star" src="images/star.svg"/>
				</dt>
				<dd>
				 Get a note correct at least 5 times with an average speed of less than &frac12; a second in a run with at least 25 notes.
				</dd></li>
			</ul>
				
			</dl>
		 </div>
		</div>
	 </div>





            </div>
        );
    }
}

export default HomeComponent;

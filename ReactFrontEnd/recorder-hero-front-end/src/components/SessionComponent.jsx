import React, { Fragment } from "react";
import AuthenticationService from "../services/AuthenticationService";
import MenuComponent from './MenuComponent';
import NoteDisplayComponent from './NoteDisplayComponent';
import NotesComponent from './NotesComponent';
import { MDBBtn } from "mdbreact";


class SessionComponent extends React.Component {

    constructor(props) {
        super(props);
        this.end = React.createRef();
        this.state = {
           
           course: this.props.location.state.detail
        }
    }

    componentDidMount(){
        this.setState({
           
    })
    }

    

    render() {
        return (
            <div className="display-column">
                Session


                <div id="hidden" >
                    <div className="text-center">Notes Left:</div>
                    <div className="text-center bg-primary p-3 text-light" id="numberNotes"></div>
                </div>
                <br/><br/>
                    <div className="container-fluid "><div className="row"><div className="col-4 ">
                        <div>img</div>
                    </div>
                    <NoteDisplayComponent user_course={this.state.course} />
                   
                    <NotesComponent notes={this.state.notes}/>
                    
        </div></div>


                
            </div>
        );
    }
}

export default SessionComponent;

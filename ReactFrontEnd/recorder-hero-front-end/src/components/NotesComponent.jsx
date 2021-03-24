import React, { Fragment } from "react";
import AuthenticationService from "../services/AuthenticationService";
import MenuComponent from './MenuComponent';
import { MDBBtn } from "mdbreact";


class NotesComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
           challenge: false
        }
        
    }

    componentDidMount(){
        this.setState({
           
    })
    }

    

    render() {
        return (
            
                    
                    <div className="col-4  d-flex justify-content-center  align-content-center flex-wrap">
                       

                    {this.state.challenge == false && <div>hi</div>
                        
        
                    }
        
                </div>


               
        );
    }
}


export default NotesComponent;

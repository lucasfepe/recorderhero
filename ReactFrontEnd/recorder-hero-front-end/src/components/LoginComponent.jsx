import React, { Component, useState } from 'react'
import AuthenticationService from '../services/AuthenticationService';
import SignUpService from '../services/SignUpService.js';
import { Tabs } from 'react-bootstrap';
import { Tab } from 'react-bootstrap';


class LoginComponent extends Component {

    constructor(props) {
        super(props)
       
        this.state = {
            roles: [],
            username: '',
            password: '',
            passwordRepeat: '',
            hasLoginFailed: false,
            showSuccessMessage: false,
            nameError: '',
            passError: '',
            signupError: '',
            key: 'login',
            signupMessage: '',
            logedinuser: '',
            logedinpass: '',
            logedinroles: ''
        }

        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
        this.signUpClicked = this.signUpClicked.bind(this)
        this.handleSelect = this.handleSelect.bind(this)
        this.componentDidMount = this.componentDidMount.bind(this)
    }

    componentDidMount(){
        this.setState({
            logedinuser: AuthenticationService.getLoggedInUserName(),
    })}

    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    loginClicked() {
        // in28minutes,dummy
        // if(this.state.username==='in28minutes' && this.state.password==='dummy'){
        //     AuthenticationService.registerSuccessfulLogin(this.state.username,this.state.password)
        //     this.props.history.push(`/courses`)
        //     //this.setState({showSuccessMessage:true})
        //     //this.setState({hasLoginFailed:false})
        // }
        // else {
        //     this.setState({showSuccessMessage:false})
        //     this.setState({hasLoginFailed:true})
        // }

    //    AuthenticationService
    //         .executeBasicAuthenticationService(this.state.username, this.state.password)
    //         .then((res) => {
                
                
    //             AuthenticationService.registerSuccessfulLogin(this.state.username, this.state.password, res.data.roles);
                
                
    //             this.props.history.push(`/home`)
    //         }).catch(() => {
    //             this.setState({ showSuccessMessage: false })
    //             this.setState({ hasLoginFailed: true })
    //         })

    AuthenticationService
    .executeJwtAuthenticationService(this.state.username, this.state.password)
    .then((response) => {
        AuthenticationService.registerSuccessfulLoginForJwt(this.state.username, response.data.token)
        this.props.history.push(`/home`)
    }).catch(() => {
        this.setState({ showSuccessMessage: false })
        this.setState({ hasLoginFailed: true })
    })

    }

    signUpClicked(){
        //call a service to call an api call to insert a user into the database
        if(this.state.password !== this.state.passwordRepeat){
            this.setState({passError: 'Passwords must match'})
        } else {
            this.setState({passError: ''});
            this.setState({signupError: ''});
        SignUpService.executeSignUp(this.state.username,this.state.password).then(() => {
            this.setState({key: "login"})
            this.setState({signupMessage: "Sign Up Successful! Please login."})
        }
        ).catch(() => {this.setState({ signupError: "Can't create new user, username unavailable"})});
            
        }
    }

    handleSelect(key) {
        console.log('selected' + key);
        this.setState({ key: key });
      }
    
    
    render() {
        return (
            <div>
                <div>Hi</div>
                <div>{this.state.logedinuser}</div>
                <div>Hi</div>
               
                <div>{this.state.logedinroles}</div>
                <div className="verticalCenter">
                <div className="formContainer">
                <h1 >Welcome to Recorder Hero!</h1>
             <Tabs  activeKey={this.state.key}
             onSelect={this.handleSelect}>
  <Tab eventKey="login" title="Login">
  
		<h4 className="py-2 text-center">Enter credentials:</h4>
        {this.state.signupMessage && <div className="alert alert-success">{this.state.signupMessage}</div>}
        {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
		<div className="form-group ">
			<label htmlFor="low-note">User Name:</label>
			
			<input type="text" name="username" className="text form-control" placeholder="username" value={this.state.username} onChange={this.handleChange} />
			</div><div className="form-group ">
			<label htmlFor="high-note">Password:</label>
			<input type="password" name="password"  className="text form-control" placeholder="password" value={this.state.password} onChange={this.handleChange} />
		
		<div className="d-flex justify-content-around  align-items-center ">
		
		<button  type="button" className="home btn btn-danger my-3" onClick={this.loginClicked}>Login</button>
			</div>
		</div>
        
  </Tab>
  <Tab eventKey="signup" title="Sign Up">
  <h4 className="py-2 text-center">Enter credentials:</h4>
        {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
		<div className="form-group ">
            
			<label htmlFor="low-note">User Name:</label>
			
			<input type="text" name="username" className="text form-control" placeholder="username" value={this.state.username} onChange={this.handleChange} />
			</div><div className="form-group ">
			<label htmlFor="high-note">Password:</label>
            
			<input type="password" name="password"  className="text form-control" placeholder="password" value={this.state.password} onChange={this.handleChange} />
            <label htmlFor="high-note">Re-enter Password:</label>
			<input type="password" name="passwordRepeat"  className="text form-control" placeholder="Re-enter password" value={this.state.passwordRepeat} onChange={this.handleChange} />
		
		<div className="d-flex justify-content-around  align-items-center ">
		
			<button type="button"  className="home btn btn-danger my-3"  onClick={this.signUpClicked}>Sign Up</button>
			</div>
            {this.state.nameError && <div className="alert alert-warning">{this.state.nameError}</div>}
            {this.state.passError && <div className="alert alert-warning">{this.state.passError}</div>}
            {this.state.signupError && <div className="alert alert-warning">{this.state.signupError}</div>}
          
		</div>
  </Tab>
 
</Tabs>
                
		



</div>


        </div>
            </div>
        )
    }
}

export default LoginComponent
import React, {  useState, useCallback  } from 'react'

import SignUpService from '../services/SignUpService.js';
import { Tabs } from 'react-bootstrap';
import { Tab } from 'react-bootstrap';
import UserService from '../ServicesNew/UserService';
import { useHistory } from "react-router-dom";

const LoginComponent = () => {
    let history = useHistory();
    const [username, setUsername] = useState();
    const [firstName, setFirstName] = useState();
    const [lastName, setLastName] = useState();
    const [password, setPassword] = useState();
    const [passwordRepeat, setPasswordRepeat] = useState();
    const [signupError, setSignupError] = useState();
    const [key, setKey] =  useState();
    
    const [signupMessage, setSignupMessage] = useState();
   
    

    const signUpClicked = useCallback(() => {
        setSignupError('')

        if(password !== passwordRepeat){
            setSignupError('Passwords must match');
        } else if(username == null){
            setSignupError('Email can not be empty')
        } else if(password == null){
            setSignupError('Password can not be empty')
        }else if(firstName == null){
            setSignupError('First name can not be empty')
        }else if(lastName == null){
            setSignupError('Last name can not be empty')
        }
        else {
            setSignupError('')
        SignUpService.executeSignUp(username,password, firstName, lastName).then(() => {
            setKey("login")
            setSignupMessage("Sign Up Successful! Please login.")
        }
        ).catch(() => {setSignupError("Can't create new user, username unavailable")});

    }},[username,password, passwordRepeat, firstName, lastName]);
    
   

    const handleSelect = useCallback(() => {
        console.log('selected' + key);
        setKey(key);
      },[key]);
    
    
        return (
            <div className="login">
                
                {UserService.hasRole(['ClientAdmin']) && <div>Client Admin Role present</div>}
                {UserService.hasRole(['developer']) && <div>developer Role present</div>}
                <div className="bottom-background"></div>
                <h1 id="welcome">Welcome to Recorder Hero!</h1>
              <div className="verticalCenter">
                <div className="formContainer border">
                    
               
                
             <Tabs  activeKey={key}
             onSelect={handleSelect}>
  <Tab eventKey="login" title="Returning User/Guest">
  
      {signupMessage && <div className="alert alert-success position-relative">{signupMessage}</div>}
		
		<button  type="button" className="homebtn btn login btn-danger my-3" onClick={() => history.push("/home")}>Click to Enter</button>
		
  </Tab>
  <Tab eventKey="signup" title="Sign Up Here!">
      
  
        
		<div className="form-group ">
            
			<label htmlFor="low-note">Email:</label>
			<input type="text" name="username" className="text form-control" placeholder="username" value={username} onChange={e => setUsername(e.target.value)} />
			</div><div className="form-group ">
			<label htmlFor="high-note">Password:</label>
            
			<input type="password" name="password"  className="text form-control" placeholder="password" value={password} onChange={e => setPassword(e.target.value)} /></div>
            <div className="form-group ">
            <label htmlFor="high-note">Re-enter Password:</label>
			<input type="password" name="passwordRepeat"  className="text form-control" placeholder="Re-enter password" value={passwordRepeat} onChange={e => setPasswordRepeat(e.target.value)} />
            </div>
            <div className="form-group "><label htmlFor="high-note">First Name:</label>
            
			<input type="text" name="firstName"  className="text form-control" placeholder="first name" value={firstName} onChange={e => setFirstName(e.target.value)} />
            </div>
            <div className="form-group "><label htmlFor="high-note">Last Name:</label>
            
			<input type="text" name="lastName"  className="text form-control" placeholder="last name" value={lastName} onChange={e => setLastName(e.target.value)} />
		</div><div className="d-flex justify-content-around  align-items-center ">
		

			<button type="button"  className=" btn btn-danger my-3"  onClick={signUpClicked}>Sign Up</button>

			</div>
            
            {signupError && <div className="alert alert-warning">{signupError}</div>}
          
		
  </Tab>
 
</Tabs>
                
		


</div></div></div>



       
        )
    }


export default LoginComponent
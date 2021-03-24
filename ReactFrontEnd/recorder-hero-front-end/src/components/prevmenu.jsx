import React, { Component } from 'react'
import { Link, withRouter } from 'react-router-dom'
import AuthenticationService from '../services/AuthenticationService';
import { MDBDropdown, MDBDropdownToggle, MDBDropdownMenu, MDBDropdownItem } from "mdbreact";

class MenuComponent extends Component {

    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();

        return (
            <header>
               <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
		<Link className="navbar-brand " to="/home">RecorderHeroLogo</Link>
		<button className="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span className="navbar-toggler-icon"></span>
		</button>
		<div className="collapse navbar-collapse " id="navbarNavAltMarkup">
			<div className="navbar-nav">
               
                 
				 <MDBDropdown>
      <MDBDropdownToggle caret color="primary">
      Play
      </MDBDropdownToggle>
      <MDBDropdownMenu basic>
        <MDBDropdownItem><Link className="" to="/courses">Courses</Link></MDBDropdownItem>
        <MDBDropdownItem><Link className="" to="/customrun">Custom Run</Link></MDBDropdownItem>
      
        <MDBDropdownItem  />
        
      </MDBDropdownMenu>
    </MDBDropdown>
    <MDBDropdown>
      <MDBDropdownToggle caret color="primary">
      My Stats
      </MDBDropdownToggle>
      <MDBDropdownMenu basic>
        <MDBDropdownItem><Link className="" to="/achievements">Achievements</Link></MDBDropdownItem>
        <MDBDropdownItem><Link className="" to="/stats">Stats Summary</Link></MDBDropdownItem>
        <MDBDropdownItem><Link className="" to="/rewards">Rewards</Link></MDBDropdownItem>
        <MDBDropdownItem divider />
        <MDBDropdownItem><Link className="" to="/report">Report Generator</Link></MDBDropdownItem>
      </MDBDropdownMenu>
    </MDBDropdown>
    <MDBDropdown>
      <MDBDropdownToggle caret color="primary">
      Rankings
      </MDBDropdownToggle>
      <MDBDropdownMenu basic>
        <MDBDropdownItem><Link className="" to="/rankings">Achievements</Link></MDBDropdownItem>
        <MDBDropdownItem><Link className="" to="/challengerankings">Challenges</Link></MDBDropdownItem>
        
      </MDBDropdownMenu>
    </MDBDropdown>
                       
                  
                    <Link className="nav-item nav-link" to="/shop">Shop</Link>
						{!isUserLoggedIn && <Link className="nav-link" to="/login">Login</Link>}
                    {isUserLoggedIn && <Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link>}
					
					
			</div>
		</div>
	</nav>
            </header>
        )
    }
}

export default withRouter(MenuComponent)
import React from 'react';
import { MDBNavbar, MDBNavbarBrand, MDBNavbarNav, MDBNavbarToggler, MDBCollapse, MDBNavItem, MDBNavLink, MDBIcon, MDBContainer } from 'mdbreact';
import { BrowserRouter as Router } from 'react-router-dom';
import { Link, withRouter } from 'react-router-dom';
import { MDBDropdown, MDBBtn, MDBDropdownToggle, MDBDropdownMenu, MDBDropdownItem } from "mdbreact";
import AuthenticationService from '../services/AuthenticationService';

class MenuComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      collapse: false,
      
    };
    this.onClick = this.onClick.bind(this);
  }

  

  onClick() {
    this.setState({
      collapse: !this.state.collapse,
    });
  }

  render() {
    const container = { height: 1300 }
    return (
      <div>
        
          <header>
            <MDBNavbar color="default-color" dark expand="md">
              <MDBNavbarBrand href="/">
                <strong>RecorderHeroLogo</strong>
              </MDBNavbarBrand>
              <MDBNavbarToggler onClick={this.onClick} />
              <MDBCollapse isOpen={this.state.collapse} navbar>
                <MDBNavbarNav left>
                  <MDBNavItem >
                    
                    <MDBDropdown>
      <MDBDropdownToggle caret color="default">
      Play
      </MDBDropdownToggle>
      <MDBDropdownMenu basic>
        <MDBDropdownItem><MDBNavLink className="blue-grey-text" to="/courses">Courses</MDBNavLink></MDBDropdownItem>
        <MDBDropdownItem><MDBNavLink className="blue-grey-text" to="/customrun">Custom Run</MDBNavLink></MDBDropdownItem>
      
        <MDBDropdownItem  />
        
      </MDBDropdownMenu>
    </MDBDropdown>
                  </MDBNavItem>
                 
                    <MDBNavItem>
                  <MDBDropdown>
      <MDBDropdownToggle caret color="default">
      My Stats
      </MDBDropdownToggle>
      <MDBDropdownMenu basic>
        <MDBDropdownItem><MDBNavLink className="blue-grey-text" to="/achievements">Achievements</MDBNavLink></MDBDropdownItem>
        <MDBDropdownItem><MDBNavLink className="blue-grey-text" to="/stats">Stats Summary</MDBNavLink></MDBDropdownItem>
        <MDBDropdownItem><MDBNavLink className="blue-grey-text" to="/rewards">Rewards</MDBNavLink></MDBDropdownItem>
        <MDBDropdownItem divider />
        <MDBDropdownItem><MDBNavLink className="blue-grey-text" to="/report">Report Generator</MDBNavLink></MDBDropdownItem>
      </MDBDropdownMenu>
    </MDBDropdown>
                  </MDBNavItem>
                  <MDBNavItem>
                  <MDBDropdown>
      <MDBDropdownToggle caret color="default">
      Rankings
      </MDBDropdownToggle>
      <MDBDropdownMenu basic>
        <MDBDropdownItem><MDBNavLink className="blue-grey-text" to="/rankings">Achievements</MDBNavLink></MDBDropdownItem>
        <MDBDropdownItem><MDBNavLink className="blue-grey-text" to="/challengerankings">Challenges</MDBNavLink></MDBDropdownItem>
        
      </MDBDropdownMenu>
    </MDBDropdown>
                  </MDBNavItem>
                  <MDBNavItem>
      <MDBDropdownToggle  color="default">
      <Link className="text-white" to="/shop">Shop</Link>
      </MDBDropdownToggle>
      
                  </MDBNavItem>
                  {AuthenticationService.getLoggedInUserRoles().includes("ADMIN") &&
                  <MDBNavItem>
      <MDBDropdownToggle  color="default">
      <Link className="text-white" to="/admin">ADMIN</Link>
      </MDBDropdownToggle>
      
                  </MDBNavItem>}
                  <MDBNavItem>
      <MDBDropdownToggle  color="default">
      <Link className="text-white" to="/logout" onClick={AuthenticationService.logout}>Logout</Link>
      </MDBDropdownToggle>
      
                  </MDBNavItem>
                </MDBNavbarNav>
                <MDBNavbarNav right>
                  <MDBNavItem>
                    <MDBNavLink to="#"><MDBIcon fab icon="facebook-f" /></MDBNavLink>
                  </MDBNavItem>
                  <MDBNavItem>
                    <MDBNavLink to="#"><MDBIcon fab icon="twitter" /></MDBNavLink>
                  </MDBNavItem>
                  <MDBNavItem>
                    <MDBNavLink to="#"><MDBIcon fab icon="instagram" /></MDBNavLink>
                  </MDBNavItem>
                </MDBNavbarNav>
              </MDBCollapse>
            </MDBNavbar>
          </header>
        
       
      </div>
    );
  }
}

export default withRouter(MenuComponent);
package com.appsdeveloperblog.keycloak;

import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String userId;
    private List<Role> roles;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}
	public void setId(String userId) {
		this.userId = userId;
	}
	public List<Role> getRoles() {return roles;}
	public void setRoles(List<Role> roles) {this.roles = roles;}
}

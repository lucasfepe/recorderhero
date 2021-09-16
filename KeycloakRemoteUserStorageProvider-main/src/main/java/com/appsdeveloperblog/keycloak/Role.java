package com.appsdeveloperblog.keycloak;

import java.io.Serializable;

public class Role implements Serializable {


    public Role() {}

	public Role(long id, String role) {
		this.id = id;
		this.role = role;
	}

    private long id;


    private String role;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

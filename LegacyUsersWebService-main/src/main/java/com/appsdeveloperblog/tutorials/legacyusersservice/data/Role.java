package com.appsdeveloperblog.tutorials.legacyusersservice.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 5313493413859894403L;

    public Role() {}


	@Id
    @GeneratedValue
    private long id;


	@Column(nullable = false, length = 50)
    private String role;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	public Role(long id, String role) {
		this.id = id;
		this.role = role;
	}
}

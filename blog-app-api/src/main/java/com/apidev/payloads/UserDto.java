package com.apidev.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.apidev.model.Role;

public class UserDto {
	
	private int id;
	
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	@NotEmpty
	@Size(min=8, message = "Password must be minimum of 8 chars.")
	private String password;
	
	@Email(message = "Enter correct email format!")
	private String email;
	
	@NotEmpty(message = "Description cannot be empty.")
	private String about;
	
	private Set<Role> roles = new HashSet<>();
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
}

package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum Role { USER, ADMIN }

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(unique = true)
	@Size(min = 3, message = "Your username must be at least 3 characters long.")
	@NotBlank(message = "Your username cannot be blank.")
	private String username;
	
	@NotBlank(message = "Your password must not be blank.")
	@Size(min = 4, message = "Your password must be at least 4 characters long.")
	@Column(nullable = false )
	private String password;
	
	@NotBlank(message = "You must enter your name.")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews;

	public User() {
		this("N/A", "N/A", "N/A", Role.USER);
	}
	public User(String username, String password, String name, Role role) {
		this(-1, username, password, name, role);
	}
	public User(Integer userId, String username, String password, String name, Role role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}

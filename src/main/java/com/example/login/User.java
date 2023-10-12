package com.example.login;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	public String username;
	@Column(nullable = false)
	public String password;
	@Transient
	public String repeatPassword;
	@Column(nullable = false)
	public int benutzeralter;

	@Column(nullable = false)
	public String email;
	
	
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getBenutzeralter() {
		return benutzeralter;
	}

	public void setBenutzeralter(int benutzeralter) {
		this.benutzeralter = benutzeralter;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public long getId() {
		return id;
	}
}

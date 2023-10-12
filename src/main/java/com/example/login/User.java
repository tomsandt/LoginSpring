package com.example.login;

import jakarta.persistence.*;

import java.time.LocalDate;

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
	public LocalDate geburtdatum;

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

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public LocalDate getGeburtdatum() {
		return geburtdatum;
	}

	public void setGeburtdatum(LocalDate geburtdatum) {
		this.geburtdatum = geburtdatum;
	}

	public long getId() {
		return id;
	}
}

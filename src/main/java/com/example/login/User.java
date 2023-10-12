package com.example.login;

import jakarta.persistence.*;

@Entity
public class User {
	// We don't need a "synthetic" primary key here.
	// Instead we can just use username as primary key
	// => Please refactor this: remove id completely, set @Id username
	// We'll have a call to discuss the reasoning for this.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// very good: unique=true. Even better: use @Id instead, see above :)
	@Column(nullable = false, unique = true)
	public String username;
	
	@Column(nullable = false)
	public String password;

	// This should not be stored as a business property of the user.
	// Very good that you have declared it @Transient. Even better: delete it :)
	// Ok, problem is: the Spring MVC magic that mapps HTML input fields needs this property.
	// Solution: having a transfer object UserTO.
	// This would also allow us to make the business user immutable.
	// => probably to much for this simple exapmple but we'll have a call to discuss the approach.
	@Transient
	public String repeatPassword;

	// Code should be 100% english language
	// => use Idea renaming feature to rename this to age.
	// (or dob [date of birth] in the future)
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
}

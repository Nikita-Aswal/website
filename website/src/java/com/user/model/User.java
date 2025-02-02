package com.user.model;

public class User {
	
	
	 private int user_Id;
	 private String username;
	 private String password;
	 private String email;
	 
	 
	 
	
	 public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int user_Id, String username, String password, String email) {
		super();
		this.user_Id = user_Id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [user_Id=" + user_Id + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}
	 
	
	
	 
	 

}

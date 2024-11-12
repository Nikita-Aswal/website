package com.admin.model;

public class Admin {
	
	
	 private int admin_Id;
	 private String username;
	 private String password;
	 private String email;
	 
	 
	 
	
	 public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int admin_Id, String username, String password, String email) {
		super();
		this.admin_Id = admin_Id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getAdmin_Id() {
		return admin_Id;
	}

	public void setAdmin_Id(int admin_Id) {
		this.admin_Id = admin_Id;
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
		return "User [admin_Id=" + admin_Id + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}
	 
	
	
	 
	 

}

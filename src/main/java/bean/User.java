package bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	int userId;
	String username;
	String password;
	String firstName;
	String lastName;
	String email;
	String status;
	String role;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"User [userId=%s, username=%s, password=%s, firstName=%s, lastName=%s, email=%s, role=%s, status=%s]", userId,
				username, password, firstName, lastName, email, role, status);
	}
	
}
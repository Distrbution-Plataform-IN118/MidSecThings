package model;

import java.util.HashMap;

import security.custom.Privilegy;
import security.custom.RoleType;

public class User {
	private String logon;
	private String password;
	private RoleType role;
	private String token;
	private HashMap<String, Privilegy> privilegy;
	public String getLogon() {
		return logon;
	}
	public void setLogon(String logon) {
		this.logon = logon;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public HashMap<String, Privilegy> getPrivilegy() {
		return privilegy;
	}
	public void setPrivilegy(HashMap<String, Privilegy> privilegy) {
		this.privilegy = privilegy;
	}
	
	

}

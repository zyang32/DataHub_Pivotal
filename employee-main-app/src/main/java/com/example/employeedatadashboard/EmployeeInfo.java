package com.example.employeedatadashboard;

public class EmployeeInfo {
	
	private long id;
	private String name;
	private String role;
	private String port;
	private long manager;
	
	protected EmployeeInfo() {

	}
	
	public EmployeeInfo(long id, String name, String role, long manager ,String port) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.manager = manager;
		this.port = port;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public long getManager() {
		return manager;
	}

	public void setManager(long manager) {
		this.manager = manager;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	

	
	
}

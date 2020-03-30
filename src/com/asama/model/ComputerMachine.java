package com.asama.model;

public class ComputerMachine {
	private String code;
	private String position;
	private boolean status;
	
	public ComputerMachine() {
		super();
	}
	public ComputerMachine(String code, String position, boolean status) {
		super();
		this.code = code;
		this.position = position;
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}	
	
	
}

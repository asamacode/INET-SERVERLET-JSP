package com.asama.model;

import java.sql.Date;
import java.sql.Time;

public class UsedMachine {

	private String customerId;
	private String machineCode;
	private Date startDate;
	private Time startTime;
	private int useTime;
	
	public UsedMachine() {
		super();
	}
	public UsedMachine(String customerId, String machineCode, Date startDate, Time startTime, int useTime) {
		super();
		this.customerId = customerId;
		this.machineCode = machineCode;
		this.startDate = startDate;
		this.startTime = startTime;
		this.useTime = useTime;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public int getUseTime() {
		return useTime;
	}
	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}
	
	
}

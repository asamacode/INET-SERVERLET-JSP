package com.asama.model;

import java.sql.Date;
import java.sql.Time;

public class UsedService {
	private String customerId;
	private String serviceCode;
	private Date useDate;
	private Time useTime;
	private int quantity;
	
	public UsedService() {
		super();
	}
	public UsedService(String customerId, String serviceCode, Date useDate, Time useTime, int quantity) {
		super();
		this.customerId = customerId;
		this.serviceCode = serviceCode;
		this.useDate = useDate;
		this.useTime = useTime;
		this.quantity = quantity;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public Time getUseTime() {
		return useTime;
	}
	public void setUseTime(Time useTime) {
		this.useTime = useTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

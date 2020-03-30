package com.asama.model;

public class Service {
	private String code;
	private String name;
	private String unit;
	private float price;
	
	public Service(String code, String name, String unit, float price) {
		super();
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
	}
	public Service() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}

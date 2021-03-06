package com.myretail.price.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Price")
public class PriceData {
	@Id
	private int priceid;
	private int value;
	private String currency;
	private int pid;
	
	public PriceData() {
	
	}
	public PriceData(int priceid, int value, String currency, int pid) {
		this.priceid = priceid;
		this.value = value;
		this.currency = currency;
		this.pid = pid;
	}
	public int getPriceid() {
		return priceid;
	}
	public void setPriceid(int priceid) {
		this.priceid = priceid;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
}

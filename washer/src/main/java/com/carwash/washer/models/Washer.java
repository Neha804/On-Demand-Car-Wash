package com.carwash.washer.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Washer {
	@Id
	@Field
	private String washerEmailId;
	@Field
	private String washerName;
	@Field
	private String location;
	@Field
	private String description;
	@Field
	private long phoneNo;
	@Field
	private String password;
	
	public Washer(String washerEmailId, String washerName, String location, String description, long phoneNo,
			String password) {
		super();
		this.washerEmailId = washerEmailId;
		this.washerName = washerName;
		this.location = location;
		this.description = description;
		this.phoneNo = phoneNo;
		this.password = password;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getWasherEmailId() {
		return washerEmailId;
	}
	public void setWasherEmailId(String washerEmailId) {
		this.washerEmailId = washerEmailId;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getWasherName() {
		return washerName;
	}



	public void setWasherName(String washerName) {
		this.washerName = washerName;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("User[WasherName='%s', Location='%s', emailId='%s', Description='%s', Phone No.='%lu']",washerName,location,washerEmailId,description,phoneNo);
	}
	
	
	
}

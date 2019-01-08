package com.preauth.shared.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member {
	private String Product;
	private int Id;
	private String SubSsn;
	private String FirstName;
	private String LastName;
	private String Dob;
	private String uri;
	
	public int getId() {
		return Id;
	}
	public void setId(int memberId) {
		this.Id = memberId;
	}
	public String getSubSsn() {
		return SubSsn;
	}
	public void setSubSsn(String memberSubSsn) {
		this.SubSsn = memberSubSsn;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String memberfirstName) {
		this.FirstName = memberfirstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String memberLastName) {
		this.LastName = memberLastName;
	}
	public String getDob() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		try {
			Date date = dt.parse(Dob);
			SimpleDateFormat formatdt = new SimpleDateFormat("MM-dd-yyyy");
			return formatdt.format(date);
		} catch (ParseException e) {
			return Dob;
		} 		
	}
	public void setDob(String dob) {
		Dob = dob;
	}
	public String getProduct() {
		return Product;
	}
	public void setProduct(String product) {
		Product = product;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}	
}

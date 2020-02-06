package com.odooCN.bean;

public class Invoice {

	
	int id;
	String vendor_display_name;
	
	
	public Invoice(String vendor_display_name, int id) {
		
		this.vendor_display_name = vendor_display_name;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVendor_display_name() {
		return vendor_display_name;
	}
	public void setVendor_display_name(String vendor_display_name) {
		this.vendor_display_name = vendor_display_name;
	}
	
	
}

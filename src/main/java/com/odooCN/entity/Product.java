package com.odooCN.entity;

public class Product {
	String name;
	int invoice_id;
	int product_id;
	int price_unit;
	public Product(String name, int invoice_id, int product_id, int price_unit) {
		this.name = name;
		this.invoice_id = invoice_id;
		this.product_id = product_id;
		this.price_unit = price_unit;
	}
	
	public Product() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getPrice_unit() {
		return price_unit;
	}
	public void setPrice_unit(int price_unit) {
		this.price_unit = price_unit;
	}
	
	
	
}

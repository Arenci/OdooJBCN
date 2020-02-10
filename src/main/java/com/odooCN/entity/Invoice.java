package com.odooCN.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Invoice {
	int invoiceId;
	String vendorDisplayName;
	String date_invoice;
	String date_due;
	float amount_untaxed;
	float amount_tax;
	float amount_total;
	
	public Invoice(int invoiceId, String vendorDisplayName, String date_invoice, String date_due,
			float amount_untaxed, float amount_tax, float amount_total) {
		this.invoiceId = invoiceId;
		this.vendorDisplayName = vendorDisplayName;
		this.date_invoice = date_invoice;
		this.date_due = date_due;
		this.amount_untaxed = amount_untaxed;
		this.amount_tax = amount_tax;
		this.amount_total = amount_total;
	}

	
	public Invoice() {
		super();
	}


	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getVendorDisplayName() {
		return vendorDisplayName;
	}

	public void setVendorDisplayName(String vendorDisplayName) {
		this.vendorDisplayName = vendorDisplayName;
	}

	public String getDate_invoice() {
		return date_invoice;
	}

	public void setDate_invoice(String date_invoice) {
		this.date_invoice = date_invoice;
	}

	public String getDate_due() {
		return date_due;
	}

	public void setDate_due(String date_due) {
		this.date_due = date_due;
	}

	public float getAmount_untaxed() {
		return amount_untaxed;
	}

	public void setAmount_untaxed(float amount_untaxed) {
		this.amount_untaxed = amount_untaxed;
	}

	public float getAmount_tax() {
		return amount_tax;
	}

	public void setAmount_tax(float amount_tax) {
		this.amount_tax = amount_tax;
	}

	public float getAmount_total() {
		return amount_total;
	}

	public void setAmount_total(float amount_total) {
		this.amount_total = amount_total;
	}
	
	
	
	
	
}

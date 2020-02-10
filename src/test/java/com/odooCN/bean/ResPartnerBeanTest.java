package com.odooCN.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;



class ResPartnerBeanTest {

//	@Test
//	void test() {
//		ResPartnerBean partner = new ResPartnerBean();
//		int expected = -1;
//		int actual = partner.authenticate("terrible@gmail.com", "1234");
//		assertEquals(expected, actual);		
//	}

	
	@Test
	void test1() throws MalformedURLException {
		InvoiceBean partner = new InvoiceBean();
		Invoice expected = new Invoice("Azure Interior",1);
		Object actual = partner.getInvoices(2);
		assertNotEquals(actual, expected);
	}
}

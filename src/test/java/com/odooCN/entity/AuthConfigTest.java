package com.odooCN.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.odooCN.bean.ClientBean;
import com.odooCN.bean.InvoiceBean;
import com.odooCN.bean.ProductBean;

class AuthConfigTest {

	String username = "carlosha98@gmail.com";
	String password = "1234";
	@Test
	void authTest() {
		AuthConfig auth = new AuthConfig();
		int expected = 2;
		int actual = auth.authenticate(username, password);
		assertEquals(expected, actual); 
	}
	
	
	
	@Test
	void deleteProductTest() {
		AuthConfig auth = new AuthConfig();
		auth.authenticate(username, password);
		ProductBean a = new ProductBean();
		String expected = "{\"status\":\"ok\"}";
		String actual = a.deleteProductFromInvoice(2, 5);
		assertEquals(expected, actual);
	}
	
	@Test
	void deleteInvoiceTest() {
		AuthConfig auth = new AuthConfig();
		auth.authenticate(username, password);
		InvoiceBean a = new InvoiceBean();
		String expected = "{\"status\":\"ok\"}";
		String actual = a.deleteInvoice(2, 8);
		assertEquals(expected, actual);
	}

}

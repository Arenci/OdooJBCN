package com.odooCN.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.odooCN.entity.ResPartner;

class ResPartnerBeanTest {

	@Test
	void test() {
		ResPartnerBean partner = new ResPartnerBean();
		int expected = 2;
		int actual = partner.authenticate("carlosha98@gmail.com", "1234");
		assertEquals(expected, actual);
		
	}

}

package com.seec.insurance.plm.customerinquiry.dataaccess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import com.seec.insurance.plm.customerinquiry.model.CustomerInquiryResponse;

public class CustomerInquiryDATest {

	CustomerInquiryDA ciDA;

	@Before
	public void setUp() {
		ciDA = new CustomerInquiryDA();
	}

	@Test
	public void test() {
		assertNotNull(ciDA.getCoustomer("PAR000001"));
		assertNotNull(ciDA.getCustomerAddressDetails("PAR000001"));
		assertNotNull(ciDA.getCustomerDetails("PAR000001"));
		assertNotNull(ciDA.getCustomerEmailDetails("PAR000001"));
		assertNotNull(ciDA.getCustomerPhoneDetails("PAR000001"));
	}

	@After
	public void tearDown() {
		//TO-DO
	}
}

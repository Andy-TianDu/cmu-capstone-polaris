package com.seec.insurance.plm.customersearch.dataaccess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import com.seec.insurance.plm.customersearch.model.CustomerSearchRequest;

public class CustomerSearchDATest {

	CustomerSearchDA csDA;
	CustomerSearchRequest csReq;

	@Before
	public void setUp() {
		csDA = new CustomerSearchDA();
		csReq = new CustomerSearchRequest();
	}

	@Test
	public void test() {
		assertNotNull(csDA.searchCustomer(csReq));
		assertNotNull(csDA.searchCustomerbyCityAndZip("Pittsburgh", "15213"));
		assertNotNull(csDA.searchCustomerbyFieldName("EMAIL", "test@gmail.com"));
		assertNotNull(csDA.searchCustomerbyFirstAndLastName("firstName",
				"lastName"));
	}

	@After
	public void tearDown() {
	}

}

package com.seec.insurance.common.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResponseMessageTest {
	ResponseMessage rm;

	@Before
	public void setUp() {
		rm = new ResponseMessage();
		rm.setConfirmationId("test_id");
		rm.setResultStatus("SUCCESS");
		rm.setResultMessage("Error Message");
		rm.setResultCode("Error Code");
		rm.setResultType("SUCCESS_MESSAGE");
		rm.setTotalRecordsFound(10);
	}

	@Test
	public void test() {
		rm.setTotalRecordsFound(12);
		assertEquals(rm.getTotalRecordsFound(), 12, 0);
		assertEquals(rm.getResultMessage(), "Error Message");
		assertEquals(rm.getResultCode(), "Error Code");
		assertEquals(rm.getResultType(), "SUCCESS_MESSAGE");
		assertEquals(rm.getResultStatus(), "SUCCESS");
	}

	@After
	public void tearDown() {
		
	}
}

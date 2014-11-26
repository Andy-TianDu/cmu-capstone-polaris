package edu.cmu.capstone.polaris.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.capstone.polaris.search.SortCondition;
import edu.cmu.capstone.polaris.search.SortType;

public class PolarisAPIParserTest {

	@Test
	public void testPolarisAPIParser() {
		PolarisAPIParser parser = PolarisAPIParser.getParser();
		String testInput = "address,email,phone,all,none";
		String[] testOutput = parser.parseCustomerInquiryParameter(testInput);
		//
		assertEquals(testOutput[0], "address");
		assertEquals(testOutput[1], "email");
		assertEquals(testOutput[2], "phone");
		assertEquals(testOutput[3], "all");
		assertEquals(testOutput[4], "none");
	}

	@Test
	public void testGetParser() {
		PolarisAPIParser parser = PolarisAPIParser.getParser();
		assertTrue(parser instanceof PolarisAPIParser);
	}

	@Test
	public void testParseCustomerInquiryParameter() {
		PolarisAPIParser parser = PolarisAPIParser.getParser();
		String testInput = "address,email,phone,all,none";
		String[] testOutput = parser.parseCustomerInquiryParameter(testInput);
		//
		assertEquals(testOutput[0], "address");
		assertEquals(testOutput[1], "email");
		assertEquals(testOutput[2], "phone");
		assertEquals(testOutput[3], "all");
		assertEquals(testOutput[4], "none");
	}

	@Test
	public void testParseSortConditionFromParameter() {
		PolarisAPIParser parser = PolarisAPIParser.getParser();
		String testInput = "address.desc_rank";
		SortCondition testOutput = parser
				.parseSortConditionFromParameter(testInput);
//		assertEquals(testOutput.getSortOnField(), "address");
		assertEquals(testOutput.getOrder(), SortType.DESC);
	}

	@Test
	public void testParserCustomerSearchFullnameParameter() {
		PolarisAPIParser parser = PolarisAPIParser.getParser();
		String testInput = "Tian Du";
		String[] fullName = parser
				.parseCustomerSearchFullnameParameter(testInput);
		assertEquals(fullName[0], "Tian");
		assertEquals(fullName[1], "Du");
	}

}

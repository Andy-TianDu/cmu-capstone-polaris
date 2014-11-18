package edu.cmu.capstone.polaris.util;

import java.util.HashSet;
import java.util.Set;

import edu.cmu.capstone.polaris.endpoint.CustomerEndpoint;
import edu.cmu.capstone.polaris.error.InquiryFieldNotExistException;
import edu.cmu.capstone.polaris.request.CustomerSearchRequest;


public class PolarisAPIParser implements Parser {
	
	private static PolarisAPIParser parser = null;
	protected PolarisAPIParser(){
		params = new HashSet<String>();
		params.add(CustomerEndpoint.PARAM_INFO_ADDRESS);
		params.add(CustomerEndpoint.PARAM_INFO_EMAIL);
		params.add(CustomerEndpoint.PARAM_INFO_PHONE);
		params.add(CustomerEndpoint.PARAM_INFO_ALL);
		params.add(CustomerEndpoint.PARAM_INFO_NONE);
	}
	public static PolarisAPIParser getParser(){
		if(parser == null){
			parser = new PolarisAPIParser();
		}
		return parser;
	}

	private static Set<String> params;

	@Override
	public String[] parseCustomerInquiryParameter(
			String paramInfo) throws InquiryFieldNotExistException {
		String[] tokens = paramInfo.trim().split(",");
		
		for (int i = 0; i < tokens.length;i++) {
			String s = tokens[i];
			String trimedlowercase = s.trim().toLowerCase();
			if (!params.contains(trimedlowercase)) {
				throw new InquiryFieldNotExistException(s);
			}
			tokens[i] = trimedlowercase;
		}

		return tokens;
	}

	@Override
	public CustomerSearchRequest parseSearchParameter(String paramSearch) {
		
		return null;
	}

	// @Test
	// public void testPolarisAPIParser(){
	// PolarisAPIParser test = new PolarisAPIParser();
	// String input = "address,phone";
	// String output = test.parseInquiryParameter(input);
	// assertEquals(address[0], test.parseInquiryParameter(input)[1]);
	// }
	public static void main(String[] args){
		System.out.println(new PolarisAPIParser().parseCustomerInquiryParameter("none")[0]);
	}
}

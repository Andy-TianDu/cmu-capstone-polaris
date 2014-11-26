package edu.cmu.capstone.polaris.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.seec.insurance.plm.customerinquiry.model.CustomerInquiryResponse;

import edu.cmu.capstone.polaris.endpoint.CustomerEndpoint;

/**
 * CMU's implementation of JsonFilter interface.
 * @author tony
 *
 */
public class PolarisAPIJsonFilter implements JsonFilter{
	
	public static String FILTER_ATTRIBUTE_ADDRESS = "addressList";
	public static String FILTER_ATTRIBUTE_PHONE = "phoneList";
	public static String FILTER_ATTRIBUTE_EMAILADDRESS = "emailList";
	
	private static PolarisAPIJsonFilter filter = null;
	
	protected PolarisAPIJsonFilter(){}
	
	public static PolarisAPIJsonFilter getInstance(){
		if(filter == null){
			return new PolarisAPIJsonFilter();
		}
		return filter;
	}

	@Override
	public String customerInquiryResponseToJSON(
			CustomerInquiryResponse customerInquiryResponse, String[] requiredFiled) {
		System.out.println(requiredFiled[0]);
		Set<String> filteredProperties = new HashSet<String>();
		filteredProperties.add(FILTER_ATTRIBUTE_ADDRESS);
		filteredProperties.add(FILTER_ATTRIBUTE_PHONE);
		filteredProperties.add(FILTER_ATTRIBUTE_EMAILADDRESS);
		
		//one field only, for now.
		//TODO: support combination inquiry.
		
		if(!requiredFiled[0].equals(CustomerEndpoint.PARAM_INFO_NONE)){
			if(requiredFiled[0].equals(CustomerEndpoint.PARAM_INFO_PHONE)){
				filteredProperties.remove(FILTER_ATTRIBUTE_PHONE);
			}
			else if(requiredFiled[0].equals(CustomerEndpoint.PARAM_INFO_EMAIL)){
				filteredProperties.remove(FILTER_ATTRIBUTE_EMAILADDRESS);
			}
			else if(requiredFiled[0].equals(CustomerEndpoint.PARAM_INFO_ADDRESS)){
				filteredProperties.remove(FILTER_ATTRIBUTE_ADDRESS);
			}
		}
		System.out.println(filteredProperties);
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().addFilter("inquiryFilter",
				SimpleBeanPropertyFilter.serializeAllExcept(filteredProperties));
		mapper.setFilters(filters);
		String jsonResponse = null;
		try {
			jsonResponse = mapper.writeValueAsString(customerInquiryResponse);
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return jsonResponse;
	}
	
}

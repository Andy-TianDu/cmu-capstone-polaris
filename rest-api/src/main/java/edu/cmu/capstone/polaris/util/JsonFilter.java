package edu.cmu.capstone.polaris.util;

import com.seec.insurance.plm.customerinquiry.model.CustomerInquiryResponse;

/**
 * This interface defines the JSON Mapping utilities for the API.
 * @author tony
 *
 */
public interface JsonFilter {
	/**
	 * Map the CustomerInquiryResponse to JSON message. 
	 * @param customerInquiryResponse - inquiry response
	 * @return - the JSON string that only carries neccessary information
	 */
	public String customerInquiryResponseToJSON(CustomerInquiryResponse customerInquiryResponse, String[] requiredFields);
	

}

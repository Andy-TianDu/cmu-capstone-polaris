package edu.cmu.capstone.polaris.factory;

import com.seec.insurance.plm.customerinquiry.model.CustomerInquiryResponse;
import com.seec.insurance.plm.customersearch.model.CustomerSearchResponse;

import edu.cmu.capstone.polaris.entity.CMUCustomerCreateResponse;
import edu.cmu.capstone.polaris.entity.CMUCustomerUpdateResponse;
import edu.cmu.capstone.polaris.request.CMUCustomerCreateRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerInquiryRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerSearchRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerUpdateRequest;

/**
 * This interface defines the behavior of the factory, which takes a request object and response a response object.
 * @author tony
 *
 */
public interface ResponseFactory {
	
	/**
	 * Build CustomerInquiryResponse object from CMUCustomerInquiryRequest object.
	 * @param request - the request from endpoint
	 * @return inquiry response object
	 */
	public CustomerInquiryResponse getCustomerInquiryResponse(CMUCustomerInquiryRequest request);
	
	/**
	 * Build CustomerSearchResponse object from CMUCustomerSearchRequest and the shortcut type.
	 * @param request - the request from endpoint
	 * @param shortcut - the type of shortcut
	 * @return search response object
	 */
	public CustomerSearchResponse getCustomerSearchResponseWithShortcut(CMUCustomerSearchRequest request, CustomerSearchShortcutType shortcut);
	
	/**
	 * Build CustomerUpdateResponse object from CustomerUpdateRequest.
	 * @param request - the request from endpoint
	 * @return update response object with the updated fields.
	 */
	public CMUCustomerUpdateResponse getCustomerUpdateResponse(CMUCustomerUpdateRequest request);
	
	/**
	 * Build CustomerCreateResponse object from CustomerCreateRequest
	 * @param request - the request from endpoint
	 * @return create response object with the information that the user has specified.
	 */
	public CMUCustomerCreateResponse getCustomerCreateResponse(CMUCustomerCreateRequest request);
}

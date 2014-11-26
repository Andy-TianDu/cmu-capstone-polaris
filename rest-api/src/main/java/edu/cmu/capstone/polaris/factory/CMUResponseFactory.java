package edu.cmu.capstone.polaris.factory;

import com.seec.insurance.plm.customerinquiry.dataaccess.CustomerInquiryDA;
import com.seec.insurance.plm.customerinquiry.model.CustomerInquiryResponse;
import com.seec.insurance.plm.customersearch.dataaccess.CustomerSearchDA;
import com.seec.insurance.plm.customersearch.model.CustomerSearchResponse;

import edu.cmu.capstone.polaris.entity.CMUCustomerCreateResponse;
import edu.cmu.capstone.polaris.entity.CMUCustomerUpdateResponse;
import edu.cmu.capstone.polaris.request.CMUCustomerCreateRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerInquiryRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerSearchRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerUpdateRequest;

/**
 * CMU's Implementation of ResponseFactory class.
 * @author tony
 *
 */
public class CMUResponseFactory implements ResponseFactory{
	
	private static CMUResponseFactory factory = null;
	protected CMUResponseFactory(){}
	
	public static CMUResponseFactory getInstance(){
		if(factory == null){
			return new CMUResponseFactory();
		}
		return factory;
	}
	
	@Override
	public CustomerInquiryResponse getCustomerInquiryResponse(
			CMUCustomerInquiryRequest request) {
		CustomerInquiryDA inquiryDataAccess = new CustomerInquiryDA();
		CustomerInquiryResponse response = null;
		if(request.requestHasAddress()){
			response = inquiryDataAccess.getCustomerAddressDetails(request.getId());
		}
		else if(request.requestHasEmail()){
			response = inquiryDataAccess.getCustomerEmailDetails(request.getId());
		}
		else if(request.requestHasPhone()){
			response = inquiryDataAccess.getCustomerPhoneDetails(request.getId());
		}
		else{
			response = inquiryDataAccess.getCustomerDetails(request.getId());
		}
		return response;
	}

	@Override
	public CustomerSearchResponse getCustomerSearchResponseWithShortcut(
			CMUCustomerSearchRequest request,
			CustomerSearchShortcutType shortcut) {
		CustomerSearchDA searchDA = new CustomerSearchDA();
		switch(shortcut){
			case ZIPCODE:
				return searchDA.searchCustomerbyCityAndZip(null, request.getZip());
			case CITY:
				return searchDA.searchCustomerbyCityAndZip(request.getCity(), null);
			case FIRSTNAME:
				return searchDA.searchCustomerbyFirstAndLastName(request.getFirstName(), null);
			case LASTNAME:
				return searchDA.searchCustomerbyFieldName("lastname", request.lastName);
			case FULLNAME:
				return searchDA.searchCustomerbyFirstAndLastName(request.getFirstName(), request.getLastName());
		default:
			break;
		}
		return null;
	}

	@Override
	public CMUCustomerUpdateResponse getCustomerUpdateResponse(
			CMUCustomerUpdateRequest request) {
		// TODO Auto-generated method stub
		// 1. get key and not-null attributes to update;
		// 2. find if the request contains the contact key
		// to determine if to the request also updates the contact information.
		return new CMUCustomerUpdateResponse();
	}

	@Override
	public CMUCustomerCreateResponse getCustomerCreateResponse(
			CMUCustomerCreateRequest request) {
		// TODO Auto-generated method stub
		// determine if the information is duplicated
		// create the data item accordingly, or reject the operation.
		return new CMUCustomerCreateResponse();
	}

}

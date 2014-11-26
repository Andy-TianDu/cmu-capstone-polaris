package edu.cmu.capstone.polaris.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seec.insurance.plm.customerinquiry.model.Address;
import com.seec.insurance.plm.customerinquiry.model.EmailAddress;
import com.seec.insurance.plm.customerinquiry.model.Phone;

//ignore the undefined fields in the coming json request.
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMUCustomerUpdateRequest {
	private String partyId;
	private String firstName;
	
	private String addressKeyContent;
	private Address address;
	private String phoneKey;
	private Phone phone;
	private String emailAddressKey;
	private EmailAddress emailAddress;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddressKeyContent() {
		return addressKeyContent;
	}
	public void setAddressKeyContent(String addressKeyContent) {
		this.addressKeyContent = addressKeyContent;
	}
	public String getEmailAddressKey() {
		return emailAddressKey;
	}
	public void setEmailAddressKey(String emailAddressKey) {
		this.emailAddressKey = emailAddressKey;
	}
	public String getPhoneKey() {
		return phoneKey;
	}
	public void setPhoneKey(String phoneKey) {
		this.phoneKey = phoneKey;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public EmailAddress getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
}

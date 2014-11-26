package edu.cmu.capstone.polaris.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CMUCustomerCreateRequest {
	private String partyId;
	
	private String aRequiredField;

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getaRequiredField() {
		return aRequiredField;
	}

	public void setaRequiredField(String aRequiredField) {
		this.aRequiredField = aRequiredField;
	}
}

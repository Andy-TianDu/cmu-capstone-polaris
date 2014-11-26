package edu.cmu.capstone.polaris.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CMUCustomerCreateResponse {
	private String partyId = "testCreateID";
	private String somefiled = "some other part";
	
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getSomefiled() {
		return somefiled;
	}
	public void setSomefiled(String somefiled) {
		this.somefiled = somefiled;
	}

}

package com.seec.insurance.plm.customersearch.model;

import java.util.List;

import com.seec.insurance.common.model.ResponseMessage;

public class CustomerSearchResponse extends ResponseMessage{
	
	private List<CustomerSearchResult> searchList = null;
	
	/**
	 * @return the searchList
	 */
	public List<CustomerSearchResult> getSearchList() {
		return searchList;
	}
	/**
	 * @param searchList the searchList to set
	 */
	public void setSearchList(List<CustomerSearchResult> searchList) {
		this.searchList = searchList;
	}
	
}

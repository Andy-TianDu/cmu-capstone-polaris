package edu.cmu.capstone.polaris.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



//@use JsonInclude(Include.NON_NULL) to skip null 
// attributes when mapping to JSON.
@JsonInclude(Include.NON_NULL)
public class CMUCustomerUpdateResponse {
	
	private String testField = "test";
	private String nullField = null;
	
	public String getTestField() {
		return testField;
	}
	public void setTestField(String testField) {
		this.testField = testField;
	}
	public String getNullField() {
		return nullField;
	}
	public void setNullField(String nullField) {
		this.nullField = nullField;
	}
}

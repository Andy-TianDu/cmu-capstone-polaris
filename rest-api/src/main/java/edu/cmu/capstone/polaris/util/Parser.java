package edu.cmu.capstone.polaris.util;

import edu.cmu.capstone.polaris.search.SortCondition;


/**
 * This interface defines all the parsing utility in the API.
 * @author tony
 *
 */
public interface Parser {
	/**
	 * Parse the inquiry parameter into an array of string specifying which fields are required.
	 * @param paramContactInfo - parameter get from the url parameter
	 * @return string array that contains all the required fileds.
	 */
	public String[] parseCustomerInquiryParameter(String paramContactInfo);
	
	/**
	 * Parse the sort condition from the parameter
	 * @param paramSort - the sort parameter in the url parameter
	 * @return a sorting condition object
	 */
	public SortCondition parseSortConditionFromParameter(String paramSort);
	
	/**
	 * Parse the customer search "fullname" shortcut, to an array containing firstname and lastname.
	 * @param paramFullname - the fullname url parameter from endpoint.
	 * @return a string array with firstname as the first element, and last name as the second element.
	 */
	public String[] parseCustomerSearchFullnameParameter(String paramFullname);
}

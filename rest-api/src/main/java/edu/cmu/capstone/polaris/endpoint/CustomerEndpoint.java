package edu.cmu.capstone.polaris.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.seec.insurance.plm.customerinquiry.model.CustomerInquiryResponse;
import com.seec.insurance.plm.customersearch.model.CustomerSearchResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import edu.cmu.capstone.polaris.entity.CMUCustomerCreateResponse;
import edu.cmu.capstone.polaris.entity.CMUCustomerUpdateResponse;
import edu.cmu.capstone.polaris.entity.GeneralInfoCreateResponse;
import edu.cmu.capstone.polaris.entity.GeneralInfoSearchResponse;
import edu.cmu.capstone.polaris.factory.CMUResponseFactory;
import edu.cmu.capstone.polaris.factory.CustomerSearchShortcutType;
import edu.cmu.capstone.polaris.request.CMUCustomerCreateRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerInquiryRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerSearchRequest;
import edu.cmu.capstone.polaris.request.CMUCustomerUpdateRequest;
import edu.cmu.capstone.polaris.util.PolarisAPIJsonFilter;
import edu.cmu.capstone.polaris.util.PolarisAPIParser;

@Path("/customers")
@Api(value = "/customers", description = "Inquiry, Search, Create and Update customer information", produces = "application/json")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerEndpoint {

	public static String PARAM_INFO_NONE = "none";
	public static String PARAM_INFO_PHONE = "phone";
	public static String PARAM_INFO_EMAIL = "email";
	public static String PARAM_INFO_ADDRESS = "address";
	public static String PARAM_INFO_ALL = "all";
	public static String PARAM_SORT_ACS = "acs_rank";
	public static String PARAM_SORT_DESC = "desc_rank";

	/**
	 * With the current data access interface, we are not able to response
	 * combination of contact information. Only the first value of parameter
	 * info is evaluated.
	 * 
	 * @param userId
	 * @param fields
	 * @return
	 */
	@GET
	@Path("/{id}")
	@ApiOperation(value = "customer inquiry", notes = "Get complete cutomer information with required contact infomation", response = CustomerInquiryResponse.class, responseContainer = "")
	@Produces(MediaType.APPLICATION_JSON)
	public String customerInquiry(@PathParam("id") String userId,
			@QueryParam("info") @DefaultValue("none") String fields) {

		String[] params = null;
		try {
			params = PolarisAPIParser.getParser()
					.parseCustomerInquiryParameter(fields);
		} catch (Exception e) {
			// TODO: handle the exception when user input a unrecognizable field
		}

		// for now, we assume the parsing result will be only one parameter
		CMUCustomerInquiryRequest cir = new CMUCustomerInquiryRequest();
		cir.setId(userId);

		addFieldToRequest(cir, params);

		CustomerInquiryResponse response = CMUResponseFactory.getInstance()
				.getCustomerInquiryResponse(cir);

		String jsonResponse = null;

		jsonResponse = PolarisAPIJsonFilter.getInstance()
				.customerInquiryResponseToJSON(response, params);

		return jsonResponse;
	}

	private void addFieldToRequest(CMUCustomerInquiryRequest request,
			String[] params) {
		if (params[0].equals(PARAM_INFO_ADDRESS)) {
			request.requireAddress();
			return;
		} else if (params[0].equals(PARAM_INFO_EMAIL)) {
			request.requireEmail();
			return;
		} else if (params[0].equals(PARAM_INFO_PHONE)) {
			request.requestHasPhone();
			return;
		} else if (params[0].equals(PARAM_INFO_ALL)) {
			request.requireEmail();
			request.requirePhone();
			request.requireAddress();
			return;
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update information for a customer", notes = "update information for a customer."
			+ " To do that, you need to pass a JSON message with the fields you want to update."
			+ "If you need to update contact information, you have to include the key of the contact information attribute.")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CMUCustomerUpdateResponse update(
			@ApiParam(value = "ID of the customer to update", required = true) @PathParam("id") String id,
			CMUCustomerUpdateRequest request) {
		request.setPartyId(id);
		CMUCustomerUpdateResponse response = CMUResponseFactory.getInstance()
				.getCustomerUpdateResponse(request);
		return response;
	}

	@POST
	@ApiOperation(value = "Create a new customer", notes = "Create a new customer using the information in the JSON message")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CMUCustomerCreateResponse create(CMUCustomerCreateRequest request) {
		CMUCustomerCreateResponse response = CMUResponseFactory.getInstance()
				.getCustomerCreateResponse(request);
		return response;
	}

	@GET
	@ApiOperation(value = "search customer", notes = "Search - search customer information (To be implemented)")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchBy(
			@ApiParam(value = "specific search requirement", required = true) @QueryParam("search") String searchString,
			@ApiParam(value = "sorting ascending or descending", required = false) @QueryParam("sort") String order,
			@ApiParam(value = "pagination offset", required = false) @QueryParam("offset") @DefaultValue("0") int offest,
			@ApiParam(value = "pagination limit", required = false) @QueryParam("limit") @DefaultValue("10") int limit) {
		return null;
	}

	@GET
	@Path("/search_by_zipcode")
	@ApiOperation(value = "search customer infomation by zipcode")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerSearchResponse searchByZipcode(
			@ApiParam(value = "the zipcode to search with", required = true) @QueryParam("search") String searchString,
			@ApiParam(value = "sorting ascending or descending", required = false) @QueryParam("sort") String order,
			@ApiParam(value = "pagination offset", required = false) @QueryParam("offset") @DefaultValue("0") int offest,
			@ApiParam(value = "pagination limit", required = false) @QueryParam("limit") @DefaultValue("10") int limit) {

		CMUCustomerSearchRequest request = new CMUCustomerSearchRequest();
		request.setZip(searchString);
		request.setLimit(limit);
		request.setOffset(offest);
		request.setSortCondition(PolarisAPIParser.getParser()
				.parseSortConditionFromParameter(order));

		// TODO: catch exceptions
		CustomerSearchResponse response = CMUResponseFactory.getInstance()
				.getCustomerSearchResponseWithShortcut(request,
						CustomerSearchShortcutType.ZIPCODE);

		return response;
	}

	@GET
	@Path("/search_by_city")
	@ApiOperation(value = "search customer infomation by city")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerSearchResponse searchByCity(
			@ApiParam(value = "the city to search with", required = true) @QueryParam("search") String searchString,
			@ApiParam(value = "sorting ascending or descending", required = false) @QueryParam("sort") String order,
			@ApiParam(value = "pagination offset", required = false) @QueryParam("offset") @DefaultValue("0") int offest,
			@ApiParam(value = "pagination limit", required = false) @QueryParam("limit") @DefaultValue("10") int limit) {
		CMUCustomerSearchRequest request = new CMUCustomerSearchRequest();
		request.setCity(searchString);
		request.setLimit(limit);
		request.setOffset(offest);
		request.setSortCondition(PolarisAPIParser.getParser()
				.parseSortConditionFromParameter(order));

		// TODO: catch exceptions
		CustomerSearchResponse response = CMUResponseFactory.getInstance()
				.getCustomerSearchResponseWithShortcut(request,
						CustomerSearchShortcutType.CITY);

		return response;
	}

	@GET
	@Path("/search_by_firstname")
	@ApiOperation(value = "search customer infomation by firstname")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerSearchResponse searchByFirstname(
			@ApiParam(value = "the firstname to search with", required = true) @QueryParam("search") String searchString,
			@ApiParam(value = "sorting ascending or descending", required = false) @QueryParam("sort") String order,
			@ApiParam(value = "pagination offset", required = false) @QueryParam("offset") @DefaultValue("0") int offest,
			@ApiParam(value = "pagination limit", required = false) @QueryParam("limit") @DefaultValue("10") int limit) {

		CMUCustomerSearchRequest request = new CMUCustomerSearchRequest();
		request.setFirstName(searchString);
		request.setLimit(limit);
		request.setOffset(offest);
		request.setSortCondition(PolarisAPIParser.getParser()
				.parseSortConditionFromParameter(order));

		// TODO: catch exceptions
		CustomerSearchResponse response = CMUResponseFactory.getInstance()
				.getCustomerSearchResponseWithShortcut(request,
						CustomerSearchShortcutType.FIRSTNAME);

		return response;
	}

	@GET
	@Path("/search_by_lastname")
	@ApiOperation(value = "search customer infomation by lastname")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerSearchResponse searchByLastname(
			@ApiParam(value = "the lastname to search with", required = true) @QueryParam("search") String searchString,
			@ApiParam(value = "sorting ascending or descending", required = false) @QueryParam("sort") String order,
			@ApiParam(value = "pagination offset", required = false) @QueryParam("offset") @DefaultValue("0") int offest,
			@ApiParam(value = "pagination limit", required = false) @QueryParam("limit") @DefaultValue("10") int limit) {

		CMUCustomerSearchRequest request = new CMUCustomerSearchRequest();
		request.setCity(searchString);
		request.setLimit(limit);
		request.setOffset(offest);
		request.setSortCondition(PolarisAPIParser.getParser()
				.parseSortConditionFromParameter(order));

		// TODO: catch exceptions
		CustomerSearchResponse response = CMUResponseFactory.getInstance()
				.getCustomerSearchResponseWithShortcut(request,
						CustomerSearchShortcutType.LASTNAME);

		return response;
	}

	@GET
	@Path("/search_by_fullname")
	@ApiOperation(value = "search customer infomation by fullname")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerSearchResponse searchByFullname(
			@ApiParam(value = "the fullname to search with", required = true) @QueryParam("search") String searchString,
			@ApiParam(value = "sorting ascending or descending", required = false) @QueryParam("sort") String order,
			@ApiParam(value = "pagination offset", required = false) @QueryParam("offset") @DefaultValue("0") int offest,
			@ApiParam(value = "pagination limit", required = false) @QueryParam("limit") @DefaultValue("10") int limit) {

		CMUCustomerSearchRequest request = new CMUCustomerSearchRequest();
		String[] names = PolarisAPIParser.getParser()
				.parseCustomerSearchFullnameParameter(searchString);
		request.setFirstName(names[0]);
		request.setLastName(names[1]);
		request.setLimit(limit);
		request.setOffset(offest);
		request.setSortCondition(PolarisAPIParser.getParser()
				.parseSortConditionFromParameter(order));

		// TODO: catch exceptions
		CustomerSearchResponse response = CMUResponseFactory.getInstance()
				.getCustomerSearchResponseWithShortcut(request,
						CustomerSearchShortcutType.FULLNAME);

		return response;
	}

}

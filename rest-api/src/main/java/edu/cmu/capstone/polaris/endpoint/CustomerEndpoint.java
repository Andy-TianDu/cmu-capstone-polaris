package edu.cmu.capstone.polaris.endpoint;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import edu.cmu.capstone.polaris.entity.Address;
import edu.cmu.capstone.polaris.entity.GeneralInfoCreateResponse;
import edu.cmu.capstone.polaris.entity.GeneralInfoInquiryResponse;
import edu.cmu.capstone.polaris.entity.GeneralInfoSearchResponse;
import edu.cmu.capstone.polaris.entity.GeneralInfoUpdateResponse;
import edu.cmu.capstone.polaris.entity.Phone;

@Path("/customers")
@Api(value = "/customers", description = "Inquiry, Search, Create and Update customer information")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerEndpoint {

	private static GeneralInfoInquiryResponse inquiryTest;
	private static GeneralInfoUpdateResponse updateTest;
	private static GeneralInfoCreateResponse createTest;
	private static GeneralInfoSearchResponse searchTest;

	static {
		inquiryTest = new GeneralInfoInquiryResponse();
		Phone p = new Phone();
		p.setDialNumber("123");
		inquiryTest.setPhoneList(new Phone[] { p });
		inquiryTest.setResultCode("123");
		Address add = new Address();
		add.setAddressCity("Pittsburgh");
		inquiryTest.setAddressList(new Address[] { add });
	}

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Get all information for this customer", notes = "Get all the information for a particular customer", response = GeneralInfoInquiryResponse.class, responseContainer = "")
	@Produces(MediaType.APPLICATION_JSON)
	public GeneralInfoInquiryResponse getAll(
			@PathParam("id") String id,
			@ApiParam(value = "info", required = false) @QueryParam("info") @DefaultValue("all") String inquiryParam) {
		return inquiryTest;
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update information for this customer", notes = "update information for a customer", response = GeneralInfoUpdateResponse.class, responseContainer = "")
	@Produces(MediaType.APPLICATION_JSON)
	public GeneralInfoUpdateResponse updateAll(@PathParam("id") String id) {

		return updateTest;
	}

	@POST
	@ApiOperation(value = "Create a new customer", notes = "Create a new customer using the information in the JSON message", response = GeneralInfoCreateResponse.class, responseContainer = "")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public GeneralInfoCreateResponse create() {
		return createTest;	
	}

	@GET
	@Path("/")
	@ApiOperation(value = "/customers", notes = "Search - search customer information", response = GeneralInfoSearchResponse.class, responseContainer = "")
	@Produces(MediaType.APPLICATION_JSON)
	public GeneralInfoSearchResponse searchBy(
			@ApiParam(value = "specific search requirement", required = true) @QueryParam("search") String searchString,
			@ApiParam(value = "sorting ascending or descending", required = false) @QueryParam("sort") @DefaultValue("asc_rank") String order,
			@ApiParam(value = "pagination offset", required = false) @QueryParam("offset") @DefaultValue("0") int offest,
			@ApiParam(value = "pagination limit", required = false) @QueryParam("limit") @DefaultValue("10") int limit) {
		return searchTest;
	}

}

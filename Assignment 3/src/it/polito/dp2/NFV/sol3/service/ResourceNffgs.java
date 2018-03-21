package it.polito.dp2.NFV.sol3.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.NFV.sol3.service.nfvdeployer.NewRnffg;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RNffg;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RNffgs;

@Api(hidden = true, tags = { NfvDeployer.nffgsUri })
@ApiModel(description = "A resource representing a Network Function Virtualization")
@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
public class ResourceNffgs {

	private NfvDeployer nfvDeployer = null;
	private Map<String, ResourceNffg> subResources = new HashMap<>();

	// initialize the nffgs
	public ResourceNffgs() {
		nfvDeployer = NfvDeployer.getInstance();
	}
	
	@GET
	@ApiOperation(value = "Get the all nffgs deployed")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = RNffgs.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public RNffgs getNffgs(@QueryParam("deployTime") String date) {
		
		// get back all nffgs no filter by date
		if (date == null || date.equals("")){
			return nfvDeployer.getNffgs(null);
		}
		
		// filter by date
		try {
			Date startingDate = new SimpleDateFormat("yyyyMMdd").parse(date);
			return nfvDeployer.getNffgs(startingDate);
			
		} 
		catch (ParseException e) {
			
			throw new BadRequestException("Date not correct, the correct format is 'yyMMdd'");
		}
	}

	@POST
	@ApiOperation(value = "Deploy a new nffg")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = RNffg.class),
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 422, message = "Unprocessable Entity"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public RNffg deployNffg(NewRnffg nffg) {
		
		if (nffg == null){
			throw new BadRequestException("RNffg not defined");
		}
		if (nffg.getName() == null){
			throw new My422Exception("Nffg name absent");
		}
		
		// try deployment
		try {
			
			return nfvDeployer.tryNffgDeployment(nffg);
			
		} 
		catch (MyAlreadyLoadedException e) {
			
			throw new My409Exception(e);
			
		} catch (UnknownEntityException e) {
			
			throw new My422Exception(e);
		} 
		catch (AllocationException e) {
			
			throw new My409Exception(e);
		} 
		catch (MyNotValidExeption e) {
			
			throw new My422Exception(e);
		} 
		catch (DatatypeConfigurationException e) {
			
			throw new InternalServerErrorException(e);
		} 
		catch (ServiceException e) {
			
			throw new InternalServerErrorException(e);
		}

	}

	@Path("{nffgName}")
	public ResourceNffg getNffg(@PathParam("nffgName") String name) {
		return subResources.getOrDefault(name, new ResourceNffg(name));
	}
		

}

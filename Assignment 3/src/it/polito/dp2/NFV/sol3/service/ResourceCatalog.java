package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.NFV.sol3.service.nfvdeployer.RVnfts;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RVnft;

@Api(hidden = true, tags = {NfvDeployer.catalogUri})
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class ResourceCatalog {

	private NfvDeployer nfvDeployer = null;
	
	// init the catalog --> represented from RVnfts i normalize the name from Assignment 1
	public ResourceCatalog () {
		nfvDeployer = NfvDeployer.getInstance();
	}
	@GET
    @ApiOperation(value = "Get the catalog of vnfts")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RVnfts.class),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RVnfts getCatalog() {
		
		return nfvDeployer.getCatalog();
	}
	
	@GET
	@Path("{vnfName}")
	@ApiOperation(value = "Get a vnft")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RVnft.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RVnft getVnf(@PathParam("vnfName") String vnfName) {
		
		try {
			return nfvDeployer.getVnftByName(vnfName);
		} 
		catch (UnknownEntityException e) {
			
			throw new NotFoundException(e);
		}
	}

}

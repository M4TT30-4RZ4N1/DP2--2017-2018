package it.polito.dp2.NFV.sol3.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.*;

@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Api(hidden = true, tags = {NfvDeployer.hostsUri})
@ApiModel(description = "All hosts of nfv-system")
public class ResourceHosts {
	
	private NfvDeployer nfvDeployer = null;
	private Map<String, ResourceHost> subResources = new HashMap<>();
	
	// init the hosts resource
	public ResourceHosts() {
		nfvDeployer = NfvDeployer.getInstance();
	}
	
	@GET
    @ApiOperation(value = "Get hosts")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RHosts.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RHosts getHosts() {
		
		return nfvDeployer.getHosts();
	}
	
	@Path("{hostName}")
	public ResourceHost getHost(@PathParam("hostName") String name) {
		
		return subResources.getOrDefault(name, new ResourceHost(name));
	}

}

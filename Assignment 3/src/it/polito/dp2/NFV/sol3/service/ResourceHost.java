package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.NFV.sol3.service.nfvdeployer.RConnections;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RHost;

@Api(hidden = true, tags = {NfvDeployer.hostsUri})
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class ResourceHost {

	private NfvDeployer nfvDeployer = null;
	private String hostName;
	
	public ResourceHost(String host) {
		hostName = host;
		nfvDeployer = NfvDeployer.getInstance();
	}
	
	@GET
	@ApiOperation(value = "Get host")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RHost.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RHost getHost() {
	
		try {
			return nfvDeployer.getHostByName(hostName);
		} 
		catch (UnknownEntityException e) {
			
			throw new NotFoundException(e);
		}
	}
	
	
	@GET
	@Path(NfvDeployer.connectionsUri)
	@ApiOperation(value = "Get connections starting from the host")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RConnections.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RConnections getConnections() {
		
		try {
			return nfvDeployer.getConnections(hostName);
			
		} 
		catch (UnknownEntityException e) {
			
			throw new NotFoundException(e);
		}
	}

}

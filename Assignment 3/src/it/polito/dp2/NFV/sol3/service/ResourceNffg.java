package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.NFV.sol3.service.nfvdeployer.RNffg;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RNffgs;

@Api(hidden = true, tags = {NfvDeployer.nffgsUri})
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class ResourceNffg {
	
	private String nffgName;
	private ResourceNodes subNodes;
	private ResourceLinks subLinks;
	private NfvDeployer nfvDeployer = null;
	
	
	public ResourceNffg(String name) {
		
		nffgName = name;
		subNodes = new ResourceNodes(name);
		subLinks = new ResourceLinks(name, null);
		
		nfvDeployer = NfvDeployer.getInstance();
	}
		
	@GET
	@ApiOperation(value = "Get nffg")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RNffg.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RNffg getNffg() {
		
		try {
			return nfvDeployer.getNffgByName(nffgName);
		}
		catch (UnknownEntityException e) {
			throw new NotFoundException(e);
		}
	}
	
	@DELETE
	@ApiOperation(value = "Undeploy an nffg")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = RNffgs.class),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void deleteNffg() {
		throw new ServerErrorException(501);
	}

	
	@Path(NfvDeployer.nodesUri)
	public ResourceNodes getNodes() {
		return subNodes;
	}	
	
	@Path(NfvDeployer.linksUri)
	public ResourceLinks getLinks() {
		return subLinks;
	}

}

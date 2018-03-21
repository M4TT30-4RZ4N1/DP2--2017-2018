package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.NFV.sol3.service.nfvdeployer.RLink;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RLinks;

@Api(hidden = true, tags = {NfvDeployer.nffgsUri, NfvDeployer.nodesUri})
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class ResourceLinks {

	private String nffg;
	private String node;
	private NfvDeployer nfvDeployer = null;
	
	public ResourceLinks(String nffgName, String nodeName) {
		
		node = nodeName;
		nffg = nffgName;
		nfvDeployer = NfvDeployer.getInstance();
	}
	
	@GET
    @ApiOperation(value = "Get all links")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RLinks.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RLinks getLinks() {
		
		try {
			return nfvDeployer.getLinks(nffg, node);
		} 
		catch (UnknownEntityException e) {
			throw new NotFoundException(e);
		}
	}
	
	@POST
    @ApiOperation(value = "Add a new link")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RLink.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RLink postLink(RLink link) {
		
		// the link can be addes but we need to manage the override case
		// and check for duplicates
		
		
		// check the format
		if (link == null){
			throw new BadRequestException("RLink not provided");
		}
		if (link.getName() == null){
			throw new My422Exception("Link name not provided");
		}
		if (link.getDst() == null) {
			throw new My422Exception("Dst not provided");
		}
		if (link.getSrc() == null){
			throw new My422Exception("Src not provided");
		}
		
		if (!nfvDeployer.checkDeployment(nffg)){
			throw new My409Exception("Nffg not exist");
		}
		
		int maxL = 0;
		float minT = 0;
		
		
		if(link.getMaxL() != null){
			maxL = link.getMaxL().intValue();
		}
		
		if (link.getMinT() != null){
			minT = link.getMinT();
		}
		
		// try to add the link
		try {
			return nfvDeployer.newLink(nffg, link.getName(), link.getSrc(), link.getDst(), maxL, minT, false);
		} 
		catch (MyAlreadyLoadedException e) {
			
			throw new My409Exception(e);
			
		} catch (UnknownEntityException e) {
			
			throw new My409Exception(e);
			
		} catch (ServiceException e) {
			
			throw new InternalServerErrorException(e);
			
		} catch (MyNotValidExeption e) {
			
			throw new My422Exception(e);
		}
	}
	

	@GET
    @Path("{linkName}")
    @ApiOperation(value = "Get link")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RLink.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RLink getLink(@PathParam("linkName") String name) {
		// get link
		try {
			return nfvDeployer.getLink(nffg, name);
		} catch (UnknownEntityException e) {
			throw new NotFoundException(e);
		}
	}
	
	
	@DELETE
	@Path("{linkName}")
    @ApiOperation(value = "Delete link")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK"),
    		@ApiResponse(code = 409, message = "Conflict"),
    		@ApiResponse(code = 422, message = "Unprocessable Entity"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RLink deleteLink(@PathParam("linkName") String id) {
		// NOT IMPLEMENTED
		throw new ServerErrorException(501);
	}
	
	@PUT
	@Path("{linkName}")
    @ApiOperation(value = "Add or override link")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RLink.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RLink putLink(RLink link, @PathParam("linkName") String id) {
		
		
		if (link == null) {
			throw new BadRequestException("RLink not provided");
		}
		if (link.getName() == null){
			throw new My422Exception("Link name not provided");
		}
		if (!link.getName().equals(id)){
			throw new My422Exception("Missmatch with identifier");
		}
		if (link.getDst() == null){
			throw new My422Exception("Dst node not provided");
		}
		if (link.getSrc() == null){
			throw new My422Exception("Src node not provided");
		}
		
		if (!nfvDeployer.checkDeployment(nffg)) {
			throw new My409Exception("The Nffg does not exist");
		}
		
		int maxL = 0;
		float minT = 0;
		
		if(link.getMaxL() != null){
			maxL = link.getMaxL().intValue();
		}
		
		if(link.getMinT() != null){
			minT = link.getMinT();
		}
		
		try {
			
			return nfvDeployer.newLink(nffg, link.getName(), link.getSrc(), link.getDst(), maxL, minT, true);
		}
		catch (MyAlreadyLoadedException e) {
			
			throw new InternalServerErrorException(e);
			
		} catch (UnknownEntityException e) {
			
			throw new My422Exception(e);
			
		} catch (ServiceException e) {
			
			throw new InternalServerErrorException(e);
			
		} catch (MyNotValidExeption e) {
			
			throw new My422Exception(e);
		}
	}
	
}

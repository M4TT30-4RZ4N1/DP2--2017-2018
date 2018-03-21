package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.NFV.sol3.service.nfvdeployer.RHosts;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RNode;

@Api(hidden = true, tags = {NfvDeployer.nodesUri})
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class ResourceNode {

	private String nffgName;
	private String nodeName;
	private NfvDeployer nfvDeployer = null;
	private ResourceLinks subLinks;
	
	public ResourceNode(String nffg, String node) {
		
		nodeName = node;
		nffgName = nffg;
		// hyperlink
		subLinks = new ResourceLinks(nffg, nodeName);
		
		nfvDeployer = NfvDeployer.getInstance();
	}
	
	@GET
	@ApiOperation(value = "Get node")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RNode.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RNode getNode() {
		
		try {
			
			return nfvDeployer.getNode(nffgName, nodeName);
		}
		catch (UnknownEntityException e) {
			
			throw new NotFoundException(e);
		}
	}
	
	
	@DELETE
	@ApiOperation(value = "Delete a node from the nffg")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK"),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 422, message = "Unprocessable Entity"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public void deleteNode() {
		
		throw new ServerErrorException(501);
	}
	
	
	@PUT
	@ApiOperation(value = "Create node inside the nffg")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RNode.class),
    		@ApiResponse(code = 409, message = "Conflict"),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 422, message = "Unprocessable Entity"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RNode putNode(RNode node) {
		
		if (node == null){
			throw new BadRequestException("RNode not provided");
		}
		if (!nfvDeployer.checkDeployment(nffgName)){
			throw new My409Exception("Nffg not deployed");
		}
		if (node.getRVnft() == null){
			throw new My422Exception("VNF type not valid");
		}
		if (node.getName() == null){
			throw new My422Exception("Node name not defined");
		}
		
		String host_tmp = null;
		if(node.getRHost() != null){
			node.getRHost().getName();
		}
		
		try {
			nfvDeployer.newNode(nffgName, node.getName(), node.getRVnft().getName(), true);
		} 
		catch (MyAlreadyLoadedException e) {
			throw new My409Exception(e);
		} 
		catch (UnknownEntityException e) {
			throw new My422Exception(e);
		} 
		catch (ServiceException e) {
			
			// problem? rollback
			try {
				nfvDeployer.rollbackOnNode(nffgName, node.getName());
			} 
			catch (UnknownEntityException e1) {
				throw new InternalServerErrorException(e1);
			} 
			catch (ServiceException e1) {
				throw new InternalServerErrorException(e1);
			}
			throw new InternalServerErrorException(e);
			
			
		} catch (MyNotValidExeption e) {
			
			throw new My422Exception(e);
		}
		
		// try allocation
		try {
			nfvDeployer.allocateNode(node.getName(), host_tmp);

		} 
		catch (AllocationException e) {
			
			// problem ? rollback
			try {
				nfvDeployer.rollbackOnNode(nffgName, node.getName());
			} 
			catch (UnknownEntityException e1) {
				throw new InternalServerErrorException(e1);
			} 
			catch (ServiceException e1) {
				throw new InternalServerErrorException(e1);
			}
			throw new My409Exception(e);
			
		} 
		catch (UnknownEntityException e) {
			
			//problem? rollback
			try {
				nfvDeployer.rollbackOnNode(nffgName, node.getName());
				
			} catch (UnknownEntityException e1) {
				
				throw new InternalServerErrorException(e1);
				
			} catch (ServiceException e1) {
				
				throw new InternalServerErrorException(e1);
			}
			
			
			throw new My422Exception(e);
		}
		
		// finally try to get the xml equivalent class of node to fill the response
		try {
			return nfvDeployer.getNode(nffgName, node.getName());
			
		} 
		catch (UnknownEntityException e) {
			
			throw new InternalServerErrorException(e);
		}
	}
	
	@GET
	@Path(NfvDeployer.reachableHostsUri)
    @ApiOperation(value = "Get reachable hosts of node")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RHosts.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RHosts getReachableHosts() {
		
		// from this point is not possible to access to the hosts as bubelements 
		// but i provide an hyperlink of the host resource
		try {
			return nfvDeployer.getReachableHosts(nffgName, nodeName);
		} 
		catch (UnknownEntityException e) {
			throw new NotFoundException(e);
		}
		catch (ServiceException e) {
			throw new InternalServerErrorException(e);
		}
	}
	
	@Path(NfvDeployer.linksUri)
	public ResourceLinks getLinks() {
		return subLinks;
	}


}
